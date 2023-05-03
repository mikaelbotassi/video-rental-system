<template>
    <select class="form-select" aria-label="Selecione uma classificação" @change="onChange" id="selectClasse">
        <option disabled selected>Selecione uma classificação</option>
        <option v-for="(classe, i) in entities" :key="i" :value="classe.id">{{classe.nome}}</option>
    </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import Classe from '@/models/classe';

export default defineComponent({
    name:'ClasseSelect',
    emits: ["changeSelect"],
    setup(props, {emit}) {

        const { entities, getAll } = useRequests(Classe);
        const isOpen = ref(false);
        const selecionado = ref(0);

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const onChange = () => {
            const select = document.getElementById("selectClasse");
            selecionado.value = select.options[select.selectedIndex].value;
            emit("changeSelect", {
                id:selecionado.value,
            });
        }

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, onChange}
    },
})
</script>
