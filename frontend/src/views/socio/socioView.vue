<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Sócios</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th>Nome</th>
                    <th>N° Inscrição</th>
                    <th>Data Nascimento</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(entity, i) in entities" :key="i" :id="entity.id" :table="'diretor'" @delete="deleteElement(entity.id)" @edit="showFormEdit(entity.id)">
                        <td>{{entity.nome}}</td>
                        <td>{{entity.numInscricao}}</td>
                        <td>{{formatDate(entity.date)}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <SocioForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import SocioForm from './socioForm.vue';
import Swal from 'sweetalert2';
import Socio from "../../models/socio";

export default defineComponent({
    name:'SocioView',
    components: { SocioForm },
    setup() {

        const { entities, getAll, destroy } = useRequests(Socio);
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

        const formatDate = (date:Date) => {
            return new Date(date).toLocaleDateString("pt-BR");
        };

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
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
            formatDate,
            cancelChange
        }
    },
})
</script>
