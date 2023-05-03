<template>
    <select class="form-select" aria-label="Selecione um sócio" @change="onChange" id="selectSocio">
        <option disabled selected>Selecione um Sócio</option>
        <option v-for="(socio, i) in entities" :key="i" :value="socio.id">{{socio.nome}}</option>
    </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref} from 'vue'
import useRequests from '@/composables/requests';
import Socio from '@/models/socio';

export default defineComponent({
    name:'SocioSelect',
    emits: ["changeSelect"],
    setup(props, {emit}) {

        const { entities, getAll } = useRequests(Socio);
        const isOpen = ref(false);
        const selecionado = ref(0);

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const onChange = () => {
            const select = document.getElementById("selectSocio");
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
