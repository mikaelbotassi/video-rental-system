<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Classe
                    <button type="button" class="btn-close" @click="closeModal"></button>
                </modal-header>
                <modal-body class="d-flex align-items-center flex-wrap gap-3">
                    <div class="form-floating mb-3 col-sm-12">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="entity.nome" placeholder="nome" required>
                        <label for="nome" class="form-label">Nome</label>
                    </div>
                    <div class="form-floating mb-3 col-xl-5">
                        <input type="text" name="valor" id="valor" class="form-control" v-model="entity.valor" placeholder="R$ 0,00" required>
                        <label for="valor" class="form-label">Valor</label>
                    </div>
                    <div class="form-floating mb-3 col-xl-5">
                        <input type="number" name="devolucao" id="devolucao" class="form-control" v-model="entity.prazoDevolucao" placeholder="Digite um prazo" required>
                        <label for="nome" class="form-label">Prazo de Devolução</label>
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
import { defineComponent, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import Classe from '@/models/classe';
export default defineComponent({
    name:'ClasseForm',
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
    emits:['saved', 'close'],
    setup(props,{emit}) {

        const { erros, save, getById, entity, resetEntity } = useRequests(Classe);

        const isOpen = ref(false);

        const formSave = async () => {
            await save(entity.value);
            emit('saved');
        }

        const closeModal = () =>{
            resetEntity();
            emit('close');
        }

        watch(()=>props.editedId, (newVal) => {
            if(newVal != 0) getById(newVal);
        });

        return {
            entity,
            erros,
            formSave,
            isOpen,
            closeModal
        }     
    },
})
</script>