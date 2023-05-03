<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Classes</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Nome</th>
                    <th class="col-sm-2">Valor</th>
                    <th class="col-sm-4">Devolução</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(classe, i) in entities" :key="i" :id="classe.id" :table="'classe'" @delete="deleteElement(classe.id)" @edit="showFormEdit(classe.id)">
                        <td>{{classe.nome}}</td>
                        <td>R${{classe.valor}}</td>
                        <td>{{classe.prazoDevolucao}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <ClasseForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import ClasseForm from './classeForm.vue';
import useRequests from '@/composables/requests';
import Classe from '@/models/classe';

export default defineComponent({
    name:'ClasseView',
    components: { ClasseForm },
    setup() {

        const { entities, getAll, destroy } = useRequests(Classe);
        const isOpen = ref(false);
        const editedId = ref(0);

        function showFormEdit(id:any){
            editedId.value = id;
            isOpen.value = true;
        }

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
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

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, deleteElement, editedId, showFormEdit, cancelChange}
    },
})
</script>
