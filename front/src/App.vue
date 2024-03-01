<script setup>
import PaginaPrincipal from './components/PaginaPrincipal.vue'
import Vue from 'vue';
import VueCarousel from 'vue-carousel';
Vue.use(VueCarousel);
</script>

<template>
  <div id="app">
    <main>
      <PaginaPrincipal />
    </main>
  </div>
</template>

<style scoped>
</style>
