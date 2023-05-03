<template>
    <table class="table col-sm-12 text-dark">
        <slot></slot>
    </table>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'MyTable',
    setup() {
        return {}
    },
})
</script>
