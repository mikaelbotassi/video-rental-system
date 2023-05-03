import type Ator from "./ator";
import Classe from "./classe";
import Diretor from "./diretor";
import Flex from "./flex";

export default class Titulo extends Flex{
    nome: string;
    ano: number;
    sinopse:string;
    categoria:string;
    atores:Array<Ator>;
    diretor:Diretor;
    classificacao:Classe;

    constructor() {
        super("/titulo/");
        this.nome = "";
        this.ano = 0;
        this.sinopse = "";
        this.categoria = "";
        this.atores = new Array<Ator>();
        this.diretor = new Diretor();
        this.classificacao = new Classe();
    }

}