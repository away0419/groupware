<template>
  <div>
    <board-nav></board-nav>
    <main-section></main-section>
  </div>
</template>

<script>
import BoarNav from "@/components/layout/nav/BoardNav.vue";
import MainSection from "@/components/layout/section/board/MainSection.vue";

export default {
  name: "HomeView",
  components: {
    BoarNav,
    MainSection,
  },
};
</script>

<style></style>
