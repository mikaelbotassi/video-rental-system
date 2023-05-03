<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Ator
                    <button type="button" class="btn-close" @click="closeModal"></button>
                </modal-header>
                <modal-body>
                    <div class="form-floating mb-3">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="entity.nome" placeholder="nome" required>
                        <label for="nome" class="form-label">Nome</label>
                    </div>
                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="closeModal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>

</template>

<script lang="ts">
import { computed, defineComponent, ref, toRef, toRefs, watch } from 'vue'
import type { AxiosResponse } from 'axios';
import useRequests from '@/composables/requests';
import Ator from '@/models/ator';
import Swal from 'sweetalert2';

export default defineComponent({
    name:'DiretorForm',
    props:{
        open:{
            type:Boolean,
            required:true
        },
        editedId:{
            type:Number,
            default:0
        }
    },
    emits:['saved', 'close'],
    setup(props,{emit}) {

        const { erros, save, getById, entity, resetEntity } = useRequests(Ator);

        const isOpen = ref(false);

        const formSave = async () => {
            await save(entity.value);
            Swal.fire({
                icon: 'success',
                title: 'Ator adicionado com sucesso!',
                showConfirmButton: false,
                timer: 1500
            })
            emit('saved');
        }

        const closeModal = () =>{
            resetEntity();
            emit('close');
        }

        watch(()=>props.editedId, (newVal) => {
            if(newVal != 0) getById(newVal);
        });

        return {
            entity,
            erros,
            formSave,
            isOpen,
            closeModal
        }   
    },
})
</script>

<style>
    .my-modal{
        position:fixed;
        top:0;
        left:0;
        height: 100%;
        width: 100%;
        background-color: rgba(0,0,0,0.5);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 1000;
    }

    .my-modal-inner{
        background-color: #fff;
        box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
        padding: 2rem;
    }

    .my-modal-footer{
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 1rem;
    }

    .my-modal-header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 1rem;
    }

    .my-modal-body{
        margin-bottom: 1rem;
    }

    .v-enter-active, .v-leave-active {
        transition: opacity 0.3s ease;
    }
    .v-enter-from, .v-leave-to {
        opacity: 0;
    }
</style>
