import {ref} from 'vue'
import api from "@/services/api";
import Classe from '@/models/classe';
import type Flex from '@/models/flex';

export default function useRequests<T extends Flex>(type: { new(): T ;}){

    const entities = ref([]);
    const entity = ref(new type());
    const erros = ref('');
    
    const path = entity.value.path;

    const getAll = async () => {
        const endpoint = path === "/cliente/" || path === "/socio/" || path === "/dependente/" ? "listarAtivos" : "list";
        const response = await api.get(path + endpoint);
        entities.value = response.data;
    }

    const getById = async (id:any) => {
        const response = await api.get(path + id );
        entity.value = response.data;
    }

    const destroy = async (id:any) => {
        const response = await api.delete(path + "delete?id=" + id);
    }

    const setAtributo = (nome:string, valor:any) => {
        entity.value.nome = valor;
    }

    const resetEntity = () => {
        entity.value = new type() }

    const save = async (data:any) => {
        erros.value = '';
        try{
            await api.post(path + "save", data);
            entity.value = new Classe();
        } catch(e:any){
            if(e.response.status === 422){
                for(const key in e.response.data.errors){
                    erros.value += e.response.data.errors[key][0] + ' ';
                }
            }
        }
    }

    return {
        entities,
        entity,
        erros,
        getAll,
        getById,
        destroy,
        setAtributo,
        resetEntity,
        save
    }

}