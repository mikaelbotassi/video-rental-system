import Flex from "./flex";
import Titulo from "./titulo";

export default class Item extends Flex{
    numeroSerie: number;
    tipoItem:string;
    dataAquisicao: Date;
    titulo:Titulo

    constructor() {
        super("/item/");
        this.numeroSerie = 0,
        this.tipoItem = "",
        this.dataAquisicao = new Date();
        this.titulo = new Titulo();
    }

}