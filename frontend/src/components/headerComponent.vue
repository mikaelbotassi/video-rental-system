<template>
    <header class="position-relative shadow-sm top-0 w-100 py-1" style="z-index:4">


    <nav class="navbar navbar-expand-lg flex-nowrap">

    <div class="container-xxl">

    <router-link class="navbar-brand" to="/">
        <img src="../assets/img/brand.png" alt="Página inicial" data-aos="fade-down"/>
    </router-link>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Alternar navegação">
        <span class="navbar-toggler-icon"></span>
        <span class="navbar-toggler-icon"></span>
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="menu">

        <div class="navbar-nav ms-auto align-items-md-center gap-lg-2 gap-xxl-4" data-aos="fade-in">

        <a href="javascript:;" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="menu" class="p-5 fs-1 text-white position-absolute top-0 end-0 d-lg-none">&times;</a>
        
        <router-link to="/ator" id="ator-link" class="nav-link">Ator</router-link>
        <router-link to="/classes" id="classe-link" class="nav-link">Classes</router-link>
        <router-link to="/diretor" id="diretor-link" class="nav-link">Diretores</router-link>
        <router-link to="/titulos" id="titulos-link" class="nav-link">Títulos</router-link>
        <router-link to="/itens" id="itens-link" class="nav-link">Itens</router-link>
        <app-dropdown :title="'Clientes'">
            <dropdown-item>
                <router-link to="/socios" id="socios-link" class="nav-link">Sócios</router-link>
            </dropdown-item>
            <dropdown-item>
                <router-link to="/dependentes" id="dependentes-link" class="nav-link">Dependentes</router-link>
            </dropdown-item>
        </app-dropdown>
        <router-link to="/locacoes" id="locacoes-link" class="nav-link">Locações</router-link>

        </div>

    </div>

    </div>

    </nav>
        

    </header>

</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRouter, useRoute } from 'vue-router'
export default defineComponent({
    name:"AppHeader",
    setup() {

        const router = useRouter()
        const route = useRoute();

        return {}
    },
})
</script>

<style>
    #menu .nav-link { 
    transition: 300ms all;
    opacity: .7;
    color: var(--flex-dark)}

    #menu .nav-link:hover,
    #menu .nav-link.active { opacity: 1 }

    .navbar-toggler { 
    border: 0;
    width: 48px;
    height: 48px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    font-size: 100%;
    padding: .75rem .5rem }

    .navbar-toggler .navbar-toggler-icon { 
    width: 100%;
    display: block;
    height: 4px;
    border-radius: 4px;
    background: var(--flex-primary) !important }

    @media(max-width:1199.98px){
        header .navbar-brand img {
            max-width: 50vw;
            max-height: calc(2.5rem + 1.75vw) }
    }

    @media(max-width:991.98px){
    
        header [class*="container"] { padding: 0 1rem  !important }

        header .navbar-collapse { 
            background: var(--flex-primary);
            width: 100%;
            position: fixed;
            z-index: 1000000;
            top: 0;
            left: 0  }

        header .navbar-nav {
            justify-content: center;
            width: 100%;
            height: 100vh }  

        header .nav-link { 
            text-align: center;
            padding: .75rem 3rem !important; 
            width: 100%;
            font-size: 120% }

        header .nav-item .btn { font-size: 100% }

    }
</style>