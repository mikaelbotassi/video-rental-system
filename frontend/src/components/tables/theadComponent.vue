<template>
    <thead class="border-bottom text-primary p-3 border-primary border-2">
        <slot></slot>
        <th class="col-2 col-sm-2 text-center">A&ccedil;&otilde;es</th>
    </thead>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'MyThead',
    setup() {
        return {}
    },
})
</script>

<style scoped>
    th{
        padding: 1.5rem;
    }
</style>
