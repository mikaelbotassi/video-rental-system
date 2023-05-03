<template>
    <select class="form-select" aria-label="Selecione um diretor" @change="onChange" id="selectDiretor">
        <option disabled selected>Selecione um diretor</option>
        <option v-for="(diretor, i) in entities" :key="i" :value="diretor.id">{{diretor.nome}}</option>
    </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, watch } from 'vue'
import useRequests from '@/composables/requests';
import Diretor from '@/models/diretor';

export default defineComponent({
    name:'DiretorSelect',
    emits: ["changeSelect"],
    setup(props, {emit}) {

        const { entities, getAll } = useRequests(Diretor);
        const isOpen = ref(false);
        const selecionado = ref(0);

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const onChange = () => {
            const select = document.getElementById("selectDiretor");
            selecionado.value = select.options[select.selectedIndex].value;
            emit("changeSelect", {
                id:selecionado.value,
            });
        }

        // watch(() => props., (newVal) => {
        //     getById(newVal);
        // });

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, onChange}
    },
})
</script>
