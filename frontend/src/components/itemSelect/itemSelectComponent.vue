<template>
    <select class="form-select" aria-label="Selecione um item" @change="onChange" id="selectItem">
        <option disabled selected>Selecione um Item</option>
        <option v-for="(item, i) in entities" :key="i" :value="item.id">{{item.titulo?.nome}}</option>
    </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref} from 'vue'
import useRequests from '@/composables/requests';
import Item from '@/models/item';

export default defineComponent({
    name:'ItemSelect',
    emits: ["changeSelect"],
    setup(props, {emit}) {

        const { entities, getAll } = useRequests(Item);
        const isOpen = ref(false);
        const selecionado = ref(0);

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const onChange = () => {
            const select = document.getElementById("selectItem");
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
