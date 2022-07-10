<template>
  <b-modal
    id="approvalSelectModal"
    title="결재자 선택"
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
