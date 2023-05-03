<script lang="ts">
import { defineComponent, reactive } from 'vue'
import card from '@/components/cards/cardComponent.vue';

export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name:'catalog',
    components:{card},
    setup(){
        
        const films = reactive([
            {
                name:'A menina que roubava livros',
                img: 'https://cinegarimpo.com.br/wp/content/uploads/2014/05/cinegarimpo_21053777_20131029202242322.jpg'
            },
            {
                name:'A Cabana',
                img: 'https://lojasaraiva.vteximg.com.br/arquivos/ids/12109083/1006637057.jpg?v=637142248087230000'
            },
            {
                name:'Inferno',
                img:'https://br.web.img2.acsta.net/pictures/16/08/24/21/35/065978.jpg'
            },
            {
                name:'O Hobbit',
                img:'https://3.bp.blogspot.com/-Xk26CEGNSr8/UsbcAl8KM5I/AAAAAAAAPuU/un80T1-R3ns/s1600/O_hobbit.jpg'
            },
            {
                name:'A garota no trem',
                img:'https://images-na.ssl-images-amazon.com/images/I/A1veR5tElLL.jpg'
            }
        ]);

        return {films}

    }
})
</script>

<template>
    <section class="pt-5  bg-gradient-primary">
        <div class="container">
            <header class="position-relative text-white mb-3">
                <h1 class="display-1 opacity-25 fw-bold">FILMES</h1>
                <h4 class="position-absolute top-50 fw-bold">APROVEITE O MOMENTO</h4>
            </header>
            <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3 g-5">
                <div class="col" v-for="(film, i) in films" :key="i">
                    <card>
                        <template #header>
                            <img :src="film.img" :alt="film.name">
                        </template>
                        <template #content>
                            <h2 class="text-white fw-bold flex-text-underline" style="text-shadow: 4px 4px 25px rgba(23,69,90,0.6)">
                                {{film.name}}
                            </h2>
                        </template>
                    </card>
                </div>
            </div>
        </div>
    </section>
</template>

<style scoped>
    h4{
        transform: translateY(-11px);
    }
</style>
