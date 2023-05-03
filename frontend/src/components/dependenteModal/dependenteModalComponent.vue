<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Adicionar Dependentes
                    <button type="button" class="btn-close" @click="$emit('close')"></button>
                </modal-header>
                <modal-body>
                    <h3 class="text-dark">Lista de Clientes</h3>
                    <my-table>
                        <my-thead>
                            <th>Nome</th>
                        </my-thead>
                        <tbody>
                            <tr v-for="(cliente, i) in entities" :key="i" :id="cliente.id" :table="'ator'" >
                                <td>{{cliente.nome}}</td>
                                <td class="col-sm-3 text-center">
                                    <a href="javascript:void(0);" class="btn btn-success py-1 px-2" @click="adicionarCliente(cliente)" :id="'addButton-'+cliente.id">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
                                        </svg>
                                    </a>
                                    <a href="javascript:void(0);" class="btn btn-danger py-1 px-2 d-none" @click="removerCliente(cliente)" :id="'rmvButton-'+cliente.id">
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
import {defineComponent, ref, watch, inject, onMounted } from 'vue';

import useRequests from '@/composables/requests';
import Cliente from '@/models/cliente';
export default defineComponent({
    name:'DependenteModalComponent',
    props:{
        open:{
            type:Boolean,
            required:true
        }
    },
    emits:['saved', 'close'],
    setup(props,{emit}) {

        const { erros, entities, getAll } = useRequests(Cliente);

        const openModalDependente = ref(false);

        const clientes = ref([]);

        const selectedDependents = inject("selectedDependents", []);

        const formSave = async () => {
            emit('saved', clientes.value);
        }

        const selectedClients =  inject("selectedClients", {});

        const adicionarCliente = (cliente:any) => {
            clientes.value.push(cliente);
            const addButton = document.getElementById('addButton-'+cliente.id);
            addButton?.classList.add('d-none');
            const rmvButton = document.getElementById('rmvButton-'+cliente.id);
            rmvButton?.classList.remove('d-none');
        }

        const removerCliente = (cliente:any) => {
            const index = clientes.value.indexOf(cliente);
            if(index > -1) clientes.value.splice(index, 1);
            const addButton = document.getElementById('addButton-'+cliente.id);
            const rmvButton = document.getElementById('rmvButton-'+cliente.id);
            addButton?.classList.remove('d-none');
            rmvButton?.classList.add('d-none');
        }

        watch(() => props.open, (newVal) => {
            if(newVal){
                console.log("🚀 ~ file: dependenteModalComponent.vue ~ line 67 ~ setup ~ selectedDependents", selectedDependents)
                getAll();
            }
        });

        watch(() => selectedClients?.value, (newVal) => {
            console.log("🚀 ~ file: atorModalComponent.vue ~ line 101 ~ watch ~  selectedActors.valu",  selectedClients.value)
        });

        return {
            erros,
            formSave,
            entities,
            getAll,
            adicionarCliente,
            removerCliente,
            openModalDependente
        }     
    },
})
</script>

<style scoped>
    td{
        padding: .75rem;
    }
</style>
