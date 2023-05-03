import Flex from "./flex";

export default class Cliente extends Flex{
    numInscricao: number;
    nome:string;
    date: Date;
    sexo:string;
    ativo:boolean

    constructor(path = "/cliente/") {
        super(path);
        this.numInscricao = 0,
        this.nome = "",
        this.date = new Date();
        this.sexo = "";
        this.ativo = true;
    }

}