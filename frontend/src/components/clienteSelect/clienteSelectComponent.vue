<template>
    <select class="form-select" aria-label="Selecione um cliente" @change="onChange" id="selectClient">
        <option disabled selected>Selecione um Cliente</option>
        <option v-for="(cliente, i) in entities" :key="i" :value="cliente.id">{{cliente.nome}}</option>
    </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref} from 'vue'
import useRequests from '@/composables/requests';
import Cliente from '@/models/cliente';

export default defineComponent({
    name:'ClientSelect',
    emits: ["changeSelect"],
    setup(props, {emit}) {

        const { entities, getAll } = useRequests(Cliente);
        const isOpen = ref(false);
        const selecionado = ref(0);

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const onChange = () => {
            const select = document.getElementById("selectClient");
            selecionado.value = select?.options[select.selectedIndex].value;
            emit("changeSelect", {
                id:selecionado.value,
            });
        }

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, onChange}
    },
})
</script>
