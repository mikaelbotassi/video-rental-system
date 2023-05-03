<template>
  <select class="form-select" aria-label="Selecione uma classificação" @change="onChange" id="selectClasse">
      <option disabled selected>Selecione um título</option>
      <option v-for="(titulo, i) in entities" :key="i" :value="titulo.id">{{titulo.nome}}</option>
  </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import Titulo from '@/models/titulo';

export default defineComponent({
  name:'SelectTitulo',
  emits: ["changeSelect"],
  setup(props, {emit}) {

      const { entities, getAll } = useRequests(Titulo);
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
