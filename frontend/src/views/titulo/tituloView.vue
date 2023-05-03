<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Títulos</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Nome</th>
                    <th class="col-sm-2">Ano</th>
                    <th class="col-sm-4">Diretor</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(titulo, i) in entities" :key="i" :id="titulo.id" :table="'classe'" @delete="deleteElement(titulo.id)" @edit="showFormEdit(titulo.id)">
                        <td>{{titulo.nome}}</td>
                        <td>{{titulo.ano}}</td>
                        <td>{{titulo.diretor?.nome || ''}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <TituloForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import TituloForm from './tituloForm.vue';
import useRequests from '@/composables/requests';
import Titulo from '@/models/titulo';

export default defineComponent({
    name:'TituloView',
    components: { TituloForm },
    setup() {

        const { entities, getAll, destroy, resetEntity } = useRequests(Titulo);
        const isOpen = ref(false);
        const editedId = ref(0);

        function showFormEdit(id:any){
            editedId.value = id;
            isOpen.value = true;
        }

        const deleteElement = async (id:any) => {
            if(!window.confirm("Tem certeza que deseja excluir o ator?")) return;
            await destroy(id);
            await getAll();
        }

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
        }

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, deleteElement, editedId, showFormEdit, cancelChange}
    },
})
</script>
