<template>
  <b-modal id="oriModal" hide-backdrop hide-footer :title="name">
    <template #modal-header> </template>

    <template #default>
      <div>
        <img :src="filePreview" />
      </div>
      <div class="mb-2">
        <label class="ori-label">이메일</label> <br />
        <a class="fs-3" id="userEmail" v-text="email"></a>
      </div>
      <div class="mb-3">
        <label class="ori-label">전화번호</label>
        <p class="fs-3" id="userTel" v-text="tel"></p>
      </div>
    </template>
  </b-modal>
</template>

<script>
import { mapState } from "vuex";
const empModalStore = "empModalStore";

export default {
  name: "OrganizationModal",
  data() {
    return {};
  },
  computed: {
    ...mapState(empModalStore, [
      "empNo",
      "empName",
      "empPhoto",
      "empTel",
      "empEmail",
    ]),
    email() {
      return this.empEmail;
    },
    tel() {
      return this.empTel;
    },
    name() {
      return this.empName;
    },
    filePreview() {
      return "data:image/jpeg;base64," + this.empPhoto;
    },
  },
};
</script>

<style scoped>
::v-deep #oriModal {
  width: 220px;
  height: 300px;
  bottom: 0px;
  position: fixed;
  left: 220px;
  top: auto;
}
::v-deep #oriModal .modal-body {
  padding: 0;
}
::v-deep #oriModal #userEmail:hover {
  cursor: pointer;
}
::v-deep .modal-dialog {
  margin: 0;
  height: 100%;
}
</style>
