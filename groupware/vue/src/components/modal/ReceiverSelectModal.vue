<template>
  <b-modal
    id="receiverSelectModal"
    title="수신자 선택"
    hide-footer
    centered
    no-close-on-backdrop
  >
    <template #modal-header> </template>

    <template #default>
      <user-select-div></user-select-div>
    </template>
  </b-modal>
</template>

<script>
import UserSelectDiv from "@/components/etc/UserSelectDiv.vue";
export default {
  components: { UserSelectDiv },
  name: "ApprovalSelectModal",
};
</script>

<style scoped></style>
