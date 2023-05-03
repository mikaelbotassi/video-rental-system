<template>
    <tr>
        <slot></slot>
        <td>
            <action-dropdown>
                <dropdown-item @click="emit('delete')">
                    <trash-icon/>
                    Excluir
                </dropdown-item>
                <dropdown-item @click="emit('edit')">
                    <edit-icon />
                    Editar
                </dropdown-item>
            </action-dropdown>
        </td>
    </tr>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import api from '@/services/api';
import type { AxiosError, AxiosResponse } from 'axios';

export default defineComponent({
    name:'MyRow',
    emits:['delete', 'edit'],
    setup(props, {emit}) {
        return {emit}
    },
})
</script>

<style scoped>
    td{
        padding:.75rem;
    }
</style>