<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Adicionar Atores
                    <button type="button" class="btn-close" @click="$emit('close')"></button>
                </modal-header>
                <modal-body>
                    <h3 class="text-dark">Lista de Atores</h3>
                    <my-table>
                        <my-thead>
                            <th>Nome</th>
                        </my-thead>
                        <tbody>
                            <tr v-for="(ator, i) in entities" :key="i" :id="ator.id" :table="'ator'" >
                                <td>{{ator.nome}}</td>
                                <td class="col-sm-3 text-center">
                                    <a href="javascript:void(0);" class="btn btn-success py-1 px-2" @click="adicionarAtor(ator)" :id="'addButton-'+ator.id">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
                                        </svg>
                                    </a>
                                    <a href="javascript:void(0);" class="btn btn-danger py-1 px-2 d-none" @click="removerAtor(ator)" :id="'rmvButton-'+ator.id">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                            <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                                        </svg>
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </my-table>
                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="$emit('close')">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>

</template>

<script lang="ts">
import {defineComponent, ref, watch, inject } from 'vue';

import useRequests from '@/composables/requests';
import Ator from '@/models/ator';
export default defineComponent({
    name:'AtorModalComponent',
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

        const { erros, entities, getAll } = useRequests(Ator);

        const openModalAtor = ref(false);

        const atores = ref([]);

        const formSave = async () => {
            emit('saved', atores.value);
        }

        const selectedActors =  inject("selectedActors", {});

        const adicionarAtor = (ator:any) => {
            atores.value.push(ator);
            const addButton = document.getElementById('addButton-'+ator.id);
            addButton.classList.add('d-none');
            const rmvButton = document.getElementById('rmvButton-'+ator.id);
            rmvButton.classList.remove('d-none');
        }

        const removerAtor = (ator:any) => {
            const index = atores.value.indexOf(ator);
            if(index > -1) atores.value.splice(index, 1);
            const addButton = document.getElementById('addButton-'+ator.id);
            const rmvButton = document.getElementById('rmvButton-'+ator.id);
            addButton.classList.remove('d-none');
            rmvButton.classList.add('d-none');
        }

        watch(() => props.open, (newVal) => {
            if(newVal) getAll();
        });

        watch(() => selectedActors?.value, (newVal) => {
            console.log("🚀 ~ file: atorModalComponent.vue ~ line 101 ~ watch ~  selectedActors.valu",  selectedActors.value)
        });

        return {
            erros,
            formSave,
            entities,
            getAll,
            adicionarAtor,
            removerAtor,
            openModalAtor
        }     
    },
})
</script>

<style scoped>
    td{
        padding: .75rem;
    }
</style>
