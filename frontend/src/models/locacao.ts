import Item from "./item";
import Cliente from "./cliente";
import Flex from "./flex";

export default class Locacao extends Flex{
    dtLocacao: Date;
    dtDevolucaoPrevista: Date;
    dtDevolucaoEfetiva:Date;
    valorCobrado:number;
    multaCobrada: number;
    cliente: Cliente;
    item: Item;

    constructor() {
        super("/locacao/");
        this.dtLocacao = new Date();
        this.dtDevolucaoPrevista = new Date();
        this.dtDevolucaoEfetiva = new Date();
        this.valorCobrado = 0.0;
        this.multaCobrada = 0.0;
        this.cliente = new Cliente();
        this.item = new Item();
    }

}