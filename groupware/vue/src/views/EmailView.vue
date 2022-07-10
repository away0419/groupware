<template>
  <div>
    <email-nav></email-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import EmailNav from "@/components/layout/nav/EmailNav.vue";

export default {
  name: "HomeView",
  components: {
    EmailNav,
  },
};
</script>

<style></style>
