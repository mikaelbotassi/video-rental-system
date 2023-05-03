<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name:'card'
})
</script>


<template>
    <div class="mi-card ratio my-ratio shadow">
        <span></span>
        <header class="mi-card-img">
            <slot name="header"></slot>
        </header>
        <div class="mi-card-content">
            <slot name="content"></slot>
        </div>
    </div>
</template>

<style scoped>

    span{
        display: block;
        position: absolute;
        width: 100%;
        height: 100%;
        background: rgb(75,220,191);
        background: linear-gradient(339deg, rgba(75,220,191,1) 0%, rgba(175,20,25,0) 100%);
        z-index: 1;
    }
    .mi-card-content{
        z-index:2;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 100%;
        padding: 1rem;
        display: flex;
        align-items: flex-end;
    }

    .mi-card-img{
        width: 100%;
        height: 100%;
    }

    .mi-card{
        padding: 1rem;
        --flex-aspect-ratio:150%;
        border: .5rem solid #fff;
    }

</style>