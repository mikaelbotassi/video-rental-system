<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Atores</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th>Nome</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(ator, i) in entities" :key="i" :id="ator.id" :table="'diretor'" @delete="deleteElement(ator.id)" @edit="showFormEdit(ator.id)">
                        <td>{{ator.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <AtorForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import Ator from '@/models/ator';
import AtorForm from './AtorForm.vue';
import Swal from 'sweetalert2';

export default defineComponent({
    name:'AtorView',
    components: { AtorForm },
    setup() {

        const { entities, getAll, destroy } = useRequests(Ator);
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
            Swal.fire({
                icon: 'success',
                title: 'Ator excluído com sucesso!',
                showConfirmButton: false,
                timer: 1500
            })
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
