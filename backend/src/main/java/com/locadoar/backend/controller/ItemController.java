package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Item;
import com.locadoar.backend.domain.Item_;
import com.locadoar.backend.domain.Locacao;
import com.locadoar.backend.domain.Locacao_;
import com.locadoar.backend.persistence.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

@RestController
@RequestMapping("api/item")
public class ItemController extends BaseController<Item, IItemRepository> {

    @Autowired
    public ItemController(IItemRepository repository, EntityManager entityManager) {
        super(Item.class, repository);
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;

    //Listar somente itens que não estão locados
    @Override
    protected List<Item> list() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);

        Subquery subquery = query.subquery(Locacao.class);
        Root<Locacao> subroot = subquery.from(Locacao.class);

        subquery.select(subroot.get(Locacao_.item).get(Item_.id));

        subquery.where(
                builder.equal(subroot.get(Locacao_.item).get(Item_.id), root.get(Item_.id)),
                builder.isNull(subroot.get(Locacao_.dtDevolucaoEfetiva))
        );


        query.where(
                builder.in(root.get(Item_.id)).value(subquery.getSelection()).not()
        );

        return entityManager.createQuery(query).getResultList();
    }
}
