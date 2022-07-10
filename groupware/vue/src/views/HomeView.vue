<template>
  <div>
    <home-nav></home-nav>
    <main-section></main-section>
  </div>
</template>

<script>
import HomeNav from "@/components/layout/nav/HomeNav.vue";
import MainSection from "@/components/layout/section/home/MainSection.vue";

export default {
  name: "HomeView",
  components: {
    HomeNav,
    MainSection,
  },
};
</script>

<style></style>
