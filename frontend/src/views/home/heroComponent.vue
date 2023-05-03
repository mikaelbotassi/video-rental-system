<template>
    <main id="hero" class="p-0">
        <div id="carouselHero" class="carousel slide carousel-fade" data-bs-ride="true" data-aos="fade-down">
            <div class="carousel-inner ratio-21x9">
                <div class="carousel-item active">
                    <picture>
                        <source srcset="@/assets/img/hero.png" media="(min-width: 993px)">
                        <source srcset="@/assets/img/bg-medicina-preventiva-xs.jpg" media="(max-width: 992px)">
                        <img src="@/assets/img/hero.png" alt="..." class="position-relative start-50 translate-middle-x">
                    </picture>
                </div>
            </div>
        </div>
    </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name:'hero'
})
</script>


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