<template>
  <div class="row">
    <div class="col-lg-12">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "OneArticleSection",
};
</script>

<style scoped>
.row {
  margin: 24px;
  flex-grow: 1;
}
</style>
