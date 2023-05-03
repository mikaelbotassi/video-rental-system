package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Classificacao;
import com.locadoar.backend.domain.Cliente_;
import com.locadoar.backend.domain.Item;
import com.locadoar.backend.domain.Item_;
import com.locadoar.backend.domain.Locacao;
import com.locadoar.backend.domain.Locacao_;
import com.locadoar.backend.persistence.repository.IItemRepository;
import com.locadoar.backend.persistence.repository.ILocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/locacao")
public class LocacaoController extends BaseController<Locacao, ILocacaoRepository> {

    @Autowired
    public LocacaoController(ILocacaoRepository repository, EntityManager entityManager, IItemRepository itemRepository) {
        super(Locacao.class, repository);
        this.entityManager = entityManager;
        this.itemRepository = itemRepository;
    }

    private EntityManager entityManager;
    private IItemRepository itemRepository;

    @Transactional
    @PostMapping("efetuarLocacao")
    public Locacao efetuarLocacao(@RequestBody Locacao locacao) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Locacao.class);

            Root<Locacao> root = query.from(Locacao.class);
            query.where(
                    builder.equal(root.get(Locacao_.cliente).get(Cliente_.id), locacao.getCliente().getId()),
                    builder.isNull(root.get(Locacao_.dtDevolucaoEfetiva))
            );

            List<Locacao> locacaos = entityManager.createQuery(query).getResultList();

            if (!locacaos.isEmpty()) {
                throw new RuntimeException("O cliente possui locações que ainda não foram entregues!");
            }

            this.calcularCamposAutomáticos(locacao);

            if(locacao.getDtLocacao().compareTo(locacao.getDtDevolucaoPrevista()) > 0) {
                throw new RuntimeException("A data de locação não pode ser anterior a data prevista de devolução!");
            }

            return repository.save(locacao);
        }catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    @GetMapping("getByNumSerie")
    public Locacao getByNumSerie(@RequestParam String numSerie) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Locacao.class);
        Root<Locacao> root = query.from(Locacao.class);

        query.where(
                builder.equal(root.get(Locacao_.item).get(Item_.numeroSerie), numSerie),
                builder.isNull(root.get(Locacao_.dtDevolucaoEfetiva))
        );

        return (Locacao) entityManager.createQuery(query).getSingleResult();
    }

    @Transactional
    @PostMapping("devolver")
    public Locacao devolver(@RequestBody Locacao locacao) {
        try {
            Locacao dbLocacao = repository.findById(locacao.getId()).get();
            if(dbLocacao.getDtDevolucaoEfetiva() != null) {
                throw new RuntimeException("Esta devolução já foi realizada.");
            }
            dbLocacao.setDtDevolucaoEfetiva(LocalDateTime.now());
            return repository.save(dbLocacao);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }



    private void calcularCamposAutomáticos(Locacao locacao) {
        Optional<Item> item = itemRepository.findById(locacao.getItem().getId());
        Classificacao classeItem = item.get().getTitulo().getClassificacao();
        LocalDateTime data = LocalDateTime.now().plusDays(classeItem.getPrazoDevolucao().longValue());
        locacao.setDtLocacao(LocalDateTime.now());
        locacao.setDtDevolucaoPrevista(locacao.getDtDevolucaoPrevista() == null ? data : locacao.getDtDevolucaoPrevista());
        locacao.setValorCobrado(locacao.getValorCobrado() == null ? classeItem.getValor() : locacao.getValorCobrado());
        this.verificarDisponibilidadeItem(locacao);
    }

    private void verificarDisponibilidadeItem(Locacao locacao) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Locacao.class);
        Root<Locacao> root = query.from(Locacao.class);

        query.where(
                builder.equal(root.get(Locacao_.item).get(Item_.id), locacao.getItem().getId()),
                builder.or(
                        builder.between(root.get(Locacao_.dtLocacao), locacao.getDtLocacao(), locacao.getDtDevolucaoPrevista()),
                        builder.between(root.get(Locacao_.dtDevolucaoPrevista), locacao.getDtLocacao(), locacao.getDtDevolucaoPrevista())
                ),
                builder.isNotNull(root.get(Locacao_.dtDevolucaoEfetiva))
        );

        List<Locacao> locacoes = entityManager.createQuery(query).getResultList();

        if(!locacoes.isEmpty()) {
            throw new RuntimeException("Já existe uma locação para este item neste intevalo.");
        }
    }
}
