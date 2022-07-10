<template>
  <b-modal
    id="electronicFormModal"
    title="결재 양식 선택"
    hide-footer
    centered
    no-close-on-backdrop
  >
    <template #modal-header> </template>

    <template #default>
      <electronic-form-div></electronic-form-div>
    </template>
  </b-modal>
</template>

<script>
import ElectronicFormDiv from "../etc/ElectronicFormDiv.vue";
export default {
  components: { ElectronicFormDiv },
  name: "ElectronicFormModal",
};
</script>

<style scoped></style>
