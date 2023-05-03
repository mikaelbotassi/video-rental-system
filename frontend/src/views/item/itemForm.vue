<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Item
                    <button type="button" class="btn-close" @click="closeModal"></button>
                </modal-header>
                <modal-body>
                    <div class="form-floating mb-3">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="entity.numeroSerie" placeholder="N° de Série" required>
                        <label for="nome" class="form-label">Número de Série</label>
                    </div>
                    <div>
                        <label for="selectTipo">Selecione um tipo</label>
                        <select class="col-md-5 col-xl-4 form-select mb-3" aria-label="Selecione um tipo" id="selectTipo" v-model="entity.tipoItem">
                            <option disabled selected>Selecione um tipo</option>
                            <option value="bluray">BlueRay</option>
                            <option value="DVD">DVD</option>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="datetime-local" name="aquisicao" id="aquisicao" class="form-control" v-model="entity.dataAquisicao" placeholder="xx/xx/xxxx" required>
                        <label for="nome" class="form-label">Data de Aquisição</label>
                    </div>
                    <div class="col-md-5 col-xl-3 mb-3">
                        <label for="selectClasse" class="form-label">Selecione um título</label>
                        <SelectTitulo @changeSelect="addTitulo" :value="entity.titulo?.id"/>
                    </div>
                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="closeModal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>

</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import Item from '@/models/item';
import SelectTitulo from '../../components/selectTitulo/selectTituloComponent.vue';
import Titulo from '@/models/titulo';
export default defineComponent({
    name:'ItemForm',
    props:{
        open:{
            type:Boolean,
            required:true
        },
        editedId:{
            type:Number,
            default:0
        }
    },
    components:{ SelectTitulo },
    emits:['saved', 'close'],
    setup(props,{emit}) {

        const { erros, save, getById, entity, resetEntity } = useRequests(Item);

        const { getAll, entities } = useRequests(Titulo);

        const isOpen = ref(false);

        const formSave = async () => {
            await save(entity.value);
            emit('saved');
        }

        const addTitulo = (titulo:any) => {
            entity.value.titulo = titulo;
        }

        const closeModal = () =>{
            resetEntity();
            emit('close');
        }

        watch(()=>props.editedId, (newVal) => {
            if(newVal != 0) getById(newVal);
        });

        onMounted(getAll);

        return {
            entity,
            erros,
            formSave,
            entities,
            addTitulo,
            isOpen,
            closeModal
        }     
    },
})
</script>