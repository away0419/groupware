<template>
  <div class="icon-bar">
    <b-button type="button" v-b-modal.electronicFormModal>결재 등록</b-button>
    <eletronic-form-modal></eletronic-form-modal>
    <router-link class="link-a" :to="{ name: 'electronicFormRecently' }"
      ><font-awesome-icon icon="fa-solid fa-copy" /> 최근 사용 양식</router-link
    >

    <a class="link-a" href="#" v-b-modal.signRegisterModal
      ><font-awesome-icon icon="fa-solid fa-feather-pointed" /> 사인/도장
      등록</a
    >
    <sign-register-modal></sign-register-modal>
    <div>
      <div :class="receiveDiv" @click="receiveToggle">
        <font-awesome-icon icon="fa-solid fa-list" />
        <span>결재 수신 목록</span>
      </div>
      <ul class="list-ul" :class="receiveList">
        <li>
          <router-link
            :to="{
              name: 'electronicReceive',
              params: { listTitle: 'wait' },
            }"
            >대기 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicReceive',
              params: { listTitle: 'plan' },
            }"
            >예정 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicReceive',
              params: { listTitle: 'approve' },
            }"
            >승인 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicReceive',
              params: { listTitle: 'return' },
            }"
            >반려 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicReceive',
              params: { listTitle: 'read' },
            }"
            >수신 문서</router-link
          >
        </li>
      </ul>
    </div>
    <div>
      <div :class="sendDiv" @click="sendToggle">
        <font-awesome-icon icon="fa-solid fa-list" />
        <span>결재 발신 목록</span>
      </div>
      <ul class="list-ul" :class="sendList">
        <li>
          <router-link
            :to="{
              name: 'electronicSend',
              params: { listTitle: 'wait' },
            }"
            >대기 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicSend',
              params: { listTitle: 'approve' },
            }"
            >승인 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicSend',
              params: { listTitle: 'return' },
            }"
            >반려 문서</router-link
          >
        </li>
        <li>
          <router-link
            :to="{
              name: 'electronicSend',
              params: { listTitle: 'temp' },
            }"
            >임시 문서</router-link
          >
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import EletronicFormModal from "@/components/modal/EletronicFormModal.vue";
import SignRegisterModal from "@/components/modal/SignRegisterModal.vue";
export default {
  components: { EletronicFormModal, SignRegisterModal },
  name: "ElectronicNav",
  data() {
    return {
      receiveList: "",
      receiveDiv: "hover-div",
      sendList: "",
      sendDiv: "hover-div",
    };
  },
  methods: {
    receiveToggle() {
      if (this.receiveList == "") {
        this.receiveList = "is-open";
        this.receiveDiv = "";
      } else {
        this.receiveList = "";
        this.receiveDiv = "hover-div";
      }
    },
    sendToggle() {
      if (this.sendList == "") {
        this.sendList = "is-open";
        this.sendDiv = "";
      } else {
        this.sendList = "";
        this.sendDiv = "hover-div";
      }
    },
  },
};
</script>

<style scoped>
.list-ul li {
  list-style: none;
}
.list-ul li a {
  width: 100%;
}
.list-ul {
  max-height: 0;
  width: 150px;
  list-style: none;
  overflow: hidden;
  transition: 200ms;
  padding: 0;
  margin: 0;
  margin: auto;
}
.list-ul.is-open {
  border: solid 1px;
  border-radius: 10%;
  max-height: 120px;
}
.link-a {
  display: block;
}
.icon-bar > div,
.icon-bar > a,
.icon-bar > button {
  margin-bottom: 15px;
  margin-top: 15px;
}
.icon-bar a {
  text-decoration: none;
  color: #2c3e50;
}
.icon-bar a:visited {
  text-decoration: none;
}

.hover-div:hover,
.icon-bar a:hover {
  background-color: rgba(241, 212, 212, 0.374);
  cursor: pointer;
}
</style>
