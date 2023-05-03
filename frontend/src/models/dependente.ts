import Cliente from "./cliente";
import Socio from "./socio";

export default class Dependente extends Cliente{

    socio: Socio;

    constructor() {
        super("/dependente/");
        this.socio = new Socio();
    }

}