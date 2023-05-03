<script lang="ts">
import { defineComponent } from 'vue'
import hero from './heroComponent.vue';
import catalog from './catalogComponent.vue';

export default defineComponent({
    name:'HomeView',
    components:{hero, catalog}
})
</script>

<template>
    <hero />
    <catalog />
</template>

<style>
    #hero,
    #hero img { height: 100% }

    #hero img { 
    width: 100%;
    object-fit: cover }

    @media(max-width:767.98px){
        #hero h1,
        #hero h2 { text-shadow: 1px 1px 0 rgba(255,255,255,1)}

    }
</style>