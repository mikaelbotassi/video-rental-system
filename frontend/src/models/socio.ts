import Cliente from "./cliente";

export default class Socio extends Cliente{
    cpf: string;
    endereco: string;
    telefone: string;
    constructor() {
        super("/socio/");
        this.cpf = "";
        this.endereco = "";
        this.telefone = "";
    }

}