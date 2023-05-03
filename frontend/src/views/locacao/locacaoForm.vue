<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Locação
                    <button type="button" class="btn-close" @click="closeModal"></button>
                </modal-header>
                <modal-body class="d-flex align-items-center flex-wrap gap-3">

                    <div class="form-floating mb-3 col-xl-5">
                        <input type="datetime-local" name="dtLocacao" id="dtLocacao" class="form-control" v-model="entity.dtLocacao" required>
                        <label for="nome" class="form-label">Locação</label>
                    </div>

                    <div class="form-floating mb-3 col-xl-5">
                        <input type="datetime-local" name="dtDevolucaoPrevista" id="dtDevolucaoPrevista" class="form-control" v-model="entity.dtDevolucaoPrevista" required>
                        <label for="nome" class="form-label">Previsão de Devolução</label>
                    </div>

                    <div class="form-floating mb-3 col-xl-5">
                        <input type="datetime-local" name="dtDevolucaoEfetiva" id="dtDevolucaoEfetiva" class="form-control" v-model="entity.dtDevolucaoEfetiva" placeholder="xx/xx/xxxx">
                        <label for="nome" class="form-label">Devolução</label>
                    </div>

                    <div class="form-floating mb-3 col-xl-5">
                        <input type="text" name="valorCobrado" id="valorCobrado" class="form-control" v-model="entity.valorCobrado" placeholder="xx/xx/xxxx" required>
                        <label for="nome" class="form-label">Valor Cobrado</label>
                    </div>
                    
                    <div class="form-floating mb-3 col-xl-5">
                        <input type="text" name="multaCobrada" id="multaCobrada" class="form-control" v-model="entity.multaCobrada" placeholder="xx/xx/xxxx" required>
                        <label for="nome" class="form-label">Multa Cobrada</label>
                    </div>

                    <div class="col-md-5 col-xl-5 mb-3">
                        <label for="selectItem" class="form-label">Selecione um item</label>
                        <ItemSelect @changeSelect="addItem" :value="entity.item?.id"/>
                    </div>

                    <div class="col-md-5 col-xl-5 mb-3">
                        <label for="selectClient" class="form-label">Selecione um cliente</label>
                        <ClientSelect @changeSelect="addCliente" :value="entity.cliente?.id" />
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
import {defineComponent, provide, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import Swal from 'sweetalert2';
import Locacao from '@/models/locacao';
import ItemSelect from '../../components/itemSelect/itemSelectComponent.vue'
import ClientSelect from '../../components/clienteSelect/clienteSelectComponent.vue'
export default defineComponent({
    name: "LocacaoForm",
    props: {
        open: {
            type: Boolean,
            required: true
        },
        editedId: {
            type: Number,
            default: 0
        }
    },
    components: { ItemSelect, ClientSelect },
    emits: ["saved", "close"],
    setup(props, { emit }) {

        const { erros, save, getById, entity, resetEntity } = useRequests(Locacao);

        const formSave = async () => {
            await save(entity.value);
            Swal.fire({
                icon: 'success',
                title: 'Titulo adicionado com sucesso!',
                showConfirmButton: false,
                timer: 1500
            })
            emit("saved");
        };

        const formatDate = (date:Date) => {
            return new Date(date);
        };

        const closeModal = () =>{
            resetEntity();
            emit('close');
        }

        const addItem = (item:any) => {
            entity.value.item = item;
        }

        const addCliente = (cliente:any) => {
            entity.value.cliente = cliente;
        }

        watch(() => props.editedId, (newVal) => {
            if(newVal != 0) getById(newVal);
        });

        return {
            entity,
            erros,
            formSave,
            addItem,
            addCliente,
            closeModal,
            formatDate
        };
    },
})
</script>
