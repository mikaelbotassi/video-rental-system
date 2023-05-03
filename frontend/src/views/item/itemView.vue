<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Items</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Titulo</th>
                    <th class="col-sm-4">N° de Série</th>
                    <th class="col-sm-2">Tipo</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(item, i) in entities" :key="i" :id="item.id" :table="'item'" @delete="deleteElement(item.id)" @edit="showFormEdit(item.id)">
                        <td>{{item.titulo.nome}}</td>
                        <td>{{item.numeroSerie}}</td>
                        <td>{{item.tipoItem}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <ItemForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import Item from '@/models/item';
import ItemForm from "./itemForm.vue";

export default defineComponent({
    name:'ItemView',
    components: { ItemForm },
    setup() {

        const { entities, getAll, destroy } = useRequests(Item);
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

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
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
