import Flex from "./flex";

export default class Classe extends Flex{
    nome: string;
    valor:number;
    prazoDevolucao: number;

    constructor() {
        super("/classificacao/");
        this.nome = "",
        this.valor = 0,
        this.prazoDevolucao = 0;
    }

}