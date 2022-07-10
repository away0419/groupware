<template>
  <div class="icon-bar">
    <ul class="navbar-nav accordion">
      <div>
        <ol class="breadcrumb mt-3" id="today-mail">
          <li class="breadcrumb-item">안읽은 메일</li>
          <li class="breadcrumb-item">개</li>
        </ol>
        <ol class="breadcrumb" id="today-schedule">
          <li class="breadcrumb-item">오늘 일정</li>
          <li class="breadcrumb-item">개</li>
        </ol>
        <div>
          <div class="menu-btn">
            <button type="button" class="btn btn-outline-info">
              메일 쓰기
            </button>
          </div>
          <div class="menu-btn">
            <button type="button" class="btn btn-outline-info">
              일정 등록
            </button>
          </div>
        </div>
      </div>
      <div id="today-assiduity">
        <div class="card-header">근태 관리</div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">
            오늘 일한 시간
            <p class="card-text" id="timeSize">-- : --</p>
          </li>
          <li class="list-group-item">
            출근 시간
            <p class="card-text" id="timeSize">-- : --</p>
          </li>
          <li class="list-group-item">
            퇴근 시간
            <p class="card-text" id="timeSize">-- : --</p>
          </li>
        </ul>
      </div>

      <!-- Divider -->
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";

const empStore = "empStore";
export default {
  name: "HomeNav",
  computed: {
    ...mapState(empStore, ["empInfo"]),
  },
  created() {
    let birthDay = this.$dayjs(this.empInfo.empBirth).format("MM-DD");
    let today = this.$dayjs(this.empInfo.empBirth).format("MM-DD");
    if (birthDay == today) {
      let getBirthCookie = this.$cookies.get("IsBirthDay");

      if (
        getBirthCookie == null ||
        getBirthCookie.empNo != this.empInfo.empNo
      ) {
        let birthCookie = {
          empNo: this.empInfo.empNo,
          check: "true",
        };
        this.$cookies.set("IsBirthDay", birthCookie);
        getBirthCookie = birthCookie;
      }
      if (getBirthCookie.check == "true") {
        window.open(
          "birthday",
          "name",
          "width=500,height=600,toolbar=no,scrollbars=no",
        );
      }
    }
  },
};
</script>

<style scoped>
#today-schedule {
  margin-bottom: 0;
}
#today-assiduity,
#today-mail,
#today-schedule {
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
  border-top: 1px solid rgba(0, 0, 0, 0.125);
}
.menu-btn {
  display: block;
  text-align: center;
  padding: 16px;
}
.menu-btn:hover {
  background-color: #858585;
}
</style>
