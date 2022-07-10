<template>
  <div>
    <b-modal id="modal-1" centered>
      <!-- Modal Header -->
      <template #modal-title>
        <h4 class="modal-title">임시비밀번호 발급</h4>
      </template>

      <!-- Modal body -->
      <div class="modal-body">
        <form name="findPwdfrm" id="findPwdfrm">
          <div class="div-empinfo">
            <label class="label-empinfo" for="empNo">사원번호</label>
            <input
              class="input-empinfo"
              type="text"
              name="empNo"
              id="modalEmpNo"
              v-model="emp.empNo"
              placeholder="Enter employee number"
              required
            />
          </div>
          <div class="div-empinfo">
            <label class="label-empinfo" for="empEmail">E-mail</label>
            <input
              class="input-empinfo"
              type="email"
              name="empEmail"
              id="modalEmpEmail"
              v-model="emp.empEmail"
              placeholder="Enter e-mail"
              required
            />
          </div>
          <br />
          <div>
            <span>등록한 E-mail로 임시비밀번호가 전송됩니다.</span>
            <p v-if="sendMail" v-html="message"></p>
          </div>
        </form>
      </div>

      <!-- footer -->
      <template #modal-footer="{ cancel }">
        <div class="row px-4 buttonGroup">
          <button
            type="button"
            class="btn btn-info modalButton"
            id="btfindPwd"
            @click="find"
          >
            찾기
          </button>
          <button
            type="button"
            class="btn btn-danger modalButton"
            data-bs-dismiss="modal"
            id="btcloseModal"
            @click="cancel()"
          >
            취소
          </button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { findPwd } from "@/api/emp";

export default {
  name: "FindPwdModal",
  data() {
    return {
      emp: {
        empNo: null,
        empEmail: null,
      },
      sendMail: false,
      message: null,
    };
  },
  methods: {
    async find() {
      await findPwd(
        this.emp,
        (res) => {
          this.sendMail = true;
          if (res.data == "fail") {
            this.message =
              '<span style="color:red">해당 정보가 없습니다.</span>';
          } else {
            this.message =
              '<span style="color:green">메일을 보냈습니다!</span>';
          }
        },
        () => {},
      );
    },
  },
};
</script>

<style scoped>
.modalButton {
  margin-left: 30px;
}

.div-empinfo {
  display: flex;
  flex-direction: row;
  padding-bottom: 10px;
}

.label-empinfo {
  width: 20%;
}
.input-empinfo {
  flex-grow: 1;
}
</style>
