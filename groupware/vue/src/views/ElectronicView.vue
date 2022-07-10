<template>
  <div>
    <electronic-nav></electronic-nav>
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script>
import ElectronicNav from "@/components/layout/nav/ElectronicNav.vue";
export default {
  components: { ElectronicNav },
};
</script>

<style></style>
