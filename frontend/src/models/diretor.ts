import Flex from "./flex";

export default class Diretor extends Flex{
    nome: string

    constructor() {
        super("/diretor/");
        this.nome = ""
    }

}