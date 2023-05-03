<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Diretor
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
import { defineComponent, ref, watch, inject, computed } from 'vue'
import useRequests from '@/composables/requests';
import Diretor from '@/models/diretor';
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

        const { erros, save, getById, entity, resetEntity } = useRequests(Diretor);

        const isOpen = ref(false);

        const formSave = async () => {
            await save(entity.value);
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
