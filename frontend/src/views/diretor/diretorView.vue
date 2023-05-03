<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Diretores</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th>Nome</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(diretor, i) in entities" :key="i" :id="diretor.id" @delete="deleteElement(diretor.id)" @edit="showFormEdit(diretor.id)">
                        <td>{{diretor.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <DiretorForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId" />
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, provide, computed } from 'vue'
import DiretorForm from './diretorForm.vue';
import useRequests from '@/composables/requests';
import Diretor from '@/models/diretor';

export default defineComponent({
    name:'DiretorView',
    components: {DiretorForm},
    setup() {

        const { entities, getAll, destroy } = useRequests(Diretor);
        const isOpen = ref(false);

        const editedId = ref(0);
        const updateId = (newId:number) => {
            editedId.value = newId;
        };

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
        }

        function showFormEdit(id:any){
            updateId(id);
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

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, deleteElement, editedId, showFormEdit, cancelChange}
    },
})
</script>
