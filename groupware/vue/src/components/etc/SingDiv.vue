<template>
  <div class="sign-div">
    <div class="sign-header-div">
      <span v-text="approve.empName"></span>
    </div>
    <div class="sign-img-div">
      <img v-if="imageSrc != ''" :src="imageSrc" />
    </div>
  </div>
</template>

<script>
export default {
  name: "SignDiv",
  props: {
    approve: Object,
  },
  computed: {
    name() {
      return this.approve.empName;
    },
    imageSrc() {
      if (this.approve.imageSrc == null) return "";
      return "data:image/jpeg;base64," + this.approve.imageSrc;
    },
  },
};
</script>

<style scoped>
.sign-div {
  border: 1px solid;
  width: 100px;
  height: 100px;
  margin-left: 5px;
}
.sign-header-div {
  border-bottom: 1px solid;
}
.sign-img-div img {
  max-width: 72%;
  max-height: 72%;
}
</style>
