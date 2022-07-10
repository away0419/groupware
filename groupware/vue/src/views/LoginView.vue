<template>
  <div id="login">
    <div class="px-1 px-md-5 px-lg-1 py-5 mx-auto">
      <div class="card card0 border-0">
        <div class="row d-flex">
          <div class="col-lg-6">
            <div class="card1 pb-5">
              <div class="row">
                <img src="../assets/login/logo.png" class="logo" />
              </div>
              <div
                class="row px-3 justify-content-center mt-4 mb-5 border-line"
              >
                <img src="../assets/login/office.png" class="image" />
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <form class="loginFrm login-box login-box">
              <div class="card2 card border-0 px-4 py-5">
                <div class="row mb-4 px-3"></div>
                <div class="row mb-4 px-3"></div>
                <div class="row px-3 mb-4"></div>
                <div class="row px-3 user-box">
                  <label class="mb-1 user-label">사원번호</label>
                  <input
                    class="mb-4 textGroup"
                    type="text"
                    name="empNo"
                    id="empNo"
                    v-model="emp.empNo"
                    @keyup.enter="empLogin"
                    placeholder="Enter employee number"
                  />
                </div>
                <div class="row px-3 user-box">
                  <label class="mb-1 user-label">비밀번호</label>
                  <input
                    class="mb-4 textGroup"
                    type="password"
                    name="empPwd"
                    v-model="emp.empPwd"
                    id="empPwd"
                    placeholder="Enter password"
                    @keyup.enter="empLogin"
                  />
                </div>
                <div class="row px-3 mb-4 find-div">
                  <input
                    type="checkbox"
                    id="switch1"
                    name="chkSave"
                    class="input__on-off"
                    v-model="isRememberId"
                  />
                  <label for="switch1" class="label__on-off">
                    <span class="marble"></span>
                    <span class="on">on</span>
                    <span class="off">off</span>
                  </label>
                  <span style="margin-top: 3px; margin-right: 5px"
                    >&nbsp;저장</span
                  >
                  <span style="font-size: 0.8em; margin-top: 6px"
                    >비밀번호를 잃어버리셨나요?
                  </span>

                  <div class="svg-wrapper">
                    <svg
                      height="30"
                      width="90"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <rect class="shape" style="height: 30px; width: 90px" />
                    </svg>
                    <div class="findPwd" v-b-modal.modal-1>
                      <p id="findPwd">find</p>
                    </div>
                  </div>
                </div>

                <div class="row mb-3 px-3">
                  <button
                    type="button"
                    class="btn btn-blue text-center"
                    id="btLongin"
                    @click="empLogin"
                  >
                    로그인
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div class="bg-blue py-4">
          <div class="row px-3">
            <small class="ml-4 ml-sm-5 mb-2"
              >Copyright &copy; IntranetUserInterface</small
            >
          </div>
        </div>
      </div>
    </div>
    <find-pwd-modal></find-pwd-modal>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import FindPwdModal from "@/components/modal/FindPwdModal";

const empStore = "empStore";

export default {
  name: "LoginView",
  data() {
    return {
      emp: {
        empNo: null,
        empPwd: null,
      },
      isRememberId: false,
    };
  },
  components: {
    FindPwdModal,
  },
  computed: {
    ...mapState(empStore, ["isLogin", "isLoginError"]),
  },
  watch: {
    isRememberId: function (newVal) {
      if (!newVal) {
        this.$cookies.remove("isRememberId");
      }
    },
  },
  created() {
    if (this.$cookies.isKey("isRememberId")) {
      this.isRememberId = true;
      this.emp.empNo = this.$cookies.get("isRememberId");
    }
  },
  methods: {
    ...mapActions(empStore, ["login"]),
    async empLogin() {
      await this.login(this.emp);
      if (this.isLogin) {
        if (this.isRememberId) {
          // console.log(this.emp.empNo);
          this.$cookies.set("isRememberId", this.emp.empNo);
        }
        this.$router.push({ name: "home" });
      } else {
        alert("아이디 또는 비밀번호가 다릅니다.");
      }
    },
  },
};
</script>
<style>
@charset "UTF-8";
</style>
<style scoped>
#login {
  color: #000;
  overflow-x: hidden;
  height: 100%;
  background-color: #b0bec5;
  background-repeat: no-repeat;
}
.user-label {
  padding-right: 10px;
  padding-top: 12px;
}
.card0 {
  box-shadow: 0px 4px 8px 0px #757575;
  border-radius: 0px;
  margin: 0 auto;
  width: 78%;
}

.card2 {
  margin: 0px 40px;
}

.logo {
  width: 180px;
  height: 90px;
  margin-top: 20px;
  margin-left: 35px;
}

.image {
  width: 80%;
  height: 360px;
}

.border-line {
  border-right: 1px solid #eeeeee;
}

.line {
  height: 1px;
  width: 45%;
  background-color: #e0e0e0;
  margin-top: 10px;
}

.or {
  width: 10%;
  font-weight: bold;
}

.text-sm {
  font-size: 14px !important;
}

::placeholder {
  color: #bdbdbd;
  opacity: 1;
  font-weight: 300;
}

input,
textarea {
  padding: 10px 12px 10px 12px;
  border: 1px solid lightgrey;
  border-radius: 2px;
  margin-bottom: 5px;
  margin-top: 2px;
  width: 100%;
  box-sizing: border-box;
  color: #2c3e50;
  font-size: 14px;
  letter-spacing: 1px;
}

input:focus,
textarea:focus {
  -moz-box-shadow: none !important;
  -webkit-box-shadow: none !important;
  box-shadow: none !important;
  border: 1px solid #2da6bb;
  outline-width: 0;
}

button:focus {
  -moz-box-shadow: none !important;
  -webkit-box-shadow: none !important;
  box-shadow: none !important;
  outline-width: 0;
}

.findPwd > p {
  color: inherit;
  cursor: pointer;
  padding-left: 10px;
  text-decoration: none !important;
}

.btn-blue {
  background-color: #2da6bb;
  width: 150px;
  color: #fff;
  border-radius: 2px;
}

.btn-blue:hover {
  background-color: #000;
  cursor: pointer;
}

.bg-blue {
  color: #fff;
  background-color: #2da6bb;
}

.findPwd:hover {
  color: #2da6bb;
  cursor: pointer;
}

.form-check #emp {
  width: 30px;
}
.form-check #admin {
  width: 30px;
}
.logo {
  height: 50px;
}
.textGroup {
  width: 500px;
}
.buttonGroup {
  margin-left: 115px;
}

input[type="checkbox"] {
  display: none;
}

.label__on-off {
  overflow: hidden;
  position: relative;
  display: inline-block;
  width: 58px;
  height: 26px;
  -webkit-border-radius: 13px;
  -moz-border-radius: 13px;
  border-radius: 13px;
  background-color: #ed4956;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
  margin-top: 3px;
}

.label__on-off > * {
  vertical-align: sub;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
  font-size: 14px;
}

.label__on-off .marble {
  position: absolute;
  top: 1px;
  left: 1px;
  display: block;
  width: 24px;
  height: 24px;
  background-color: #fff;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  -moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.label__on-off .on {
  display: none;
}

.label__on-off .off {
  display: flex;
  padding-top: 2px;
  padding-left: 33px;
}

.input__on-off:checked + .label__on-off {
  background-color: #0bba82;
}

.input__on-off:checked + .label__on-off .on {
  display: flex;
  padding-left: 5px;
  padding-top: 2px;
}

.input__on-off:checked + .label__on-off .off {
  display: none;
}

.input__on-off:checked + .label__on-off .marble {
  left: 33px;
}

.svg-wrapper {
  height: 30px;
  width: 100px;
  float: right;
}

.shape {
  fill: transparent;
  stroke-dasharray: 140 540;
  stroke-dashoffset: -474;
  stroke-width: 8px;
  stroke: #2da6bb;
}

.findPwd {
  font-size: 0.9em;
  letter-spacing: 8px;
  position: relative;
  top: -27px;
}

@keyframes draw {
  0% {
    stroke-dasharray: 140 540;
    stroke-dashoffset: -474;
    stroke-width: 8px;
  }
  100% {
    stroke-dasharray: 760;
    stroke-dashoffset: 0;
    stroke-width: 2px;
  }
}

.svg-wrapper:hover .shape {
  -webkit-animation: 0.5s draw linear forwards;
  animation: 0.5s draw linear forwards;
}
</style>
