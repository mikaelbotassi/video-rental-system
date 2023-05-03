<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Locações</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Título</th>
                    <th class="col-sm-2">Cliente</th>
                    <th class="col-sm-4">Data</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(locacao, i) in entities" :key="i" :id="locacao.id" :table="'classe'" @delete="deleteElement(locacao.id)" @edit="showFormEdit(locacao.id)">
                        <td>{{locacao.item?.titulo?.nome}}</td>
                        <td>{{locacao.cliente?.nome}}</td>
                        <td>{{formatDate(locacao.dtLocacao)}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <LocacaoForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, computed } from 'vue'
import LocacaoForm from './locacaoForm.vue';
import useRequests from '@/composables/requests';
import Locacao from '@/models/locacao';

export default defineComponent({
    name:'LocacaoView',
    components: { LocacaoForm },
    setup() {

        const { entities, getAll, destroy, resetEntity } = useRequests(Locacao);
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

        const formatDate = (date:Date) => {
            return new Date(date).toLocaleString("pt-BR");
        };

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
        }

        onMounted(getAll);

        return {
            isOpen,
            entities,
            getAll,
            refreshList,
            deleteElement,
            editedId,
            showFormEdit,
            cancelChange,
            formatDate
        }
    },
})
</script>
