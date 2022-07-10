<template>
  <b-modal
    id="signRegisterModal"
    title="사인/도장 등록"
    hide-footer
    centered
    no-close-on-backdrop
  >
    <template #modal-header> </template>

    <template #default>
      <sign-register-div></sign-register-div>
    </template>
  </b-modal>
</template>

<script>
import SignRegisterDiv from "../etc/SignRegisterDiv.vue";
export default {
  components: { SignRegisterDiv },
  name: "SignRegisterModal",
};
</script>

<style scoped></style>
