<template>
    <div class="my-modal-body">
        <slot></slot>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'MyModalBody',
    setup() {
        return {}
    },
})
</script>

<style>
    .my-modal-body{
        margin-bottom: 1rem;
    }
</style>
