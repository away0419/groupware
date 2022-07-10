<template>
  <div id="content-wrapper" class="d-flex flex-column">
    <!-- Topbar s -->
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand id="head-navbar-brand" :to="{ name: 'home' }"
        >GroupWare</b-navbar-brand
      >

      <!-- 공지사항 s -->
      <div id="cotainer">
        <div id="flip">
          <div>
            <div>공지사항 예정1</div>
          </div>
          <div>
            <div>공지사항 예정2</div>
          </div>
          <div>
            <div>공지사항 예정3</div>
          </div>
        </div>
      </div>
      <!-- 공지사항 e -->

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item :to="{ name: 'electronic' }">결제</b-nav-item>
          <b-nav-item>근태</b-nav-item>
          <b-nav-item :to="{ name: 'email' }">메일</b-nav-item>
          <b-nav-item href="#">일정</b-nav-item>
          <b-nav-item href="#">주소록</b-nav-item>
          <b-nav-item :to="{ name: 'board' }">게시판</b-nav-item>
          <b-nav-item href="#">예약</b-nav-item>
          <b-nav-item href="#">채팅</b-nav-item>
          <b-nav-item-dropdown right>
            <template #button-content>
              <span>사용자</span>
            </template>
            <b-dropdown-item href="#">
              <font-awesome-icon class="folder-icon" icon="fa-user" />
              내정보</b-dropdown-item
            >
            <b-dropdown-item
              v-if="empInfo != null && empInfo.empAdminLev < 4"
              href="#"
            >
              <font-awesome-icon class="folder-icon" icon="fa-user" />
              관리자</b-dropdown-item
            >
            <hr />
            <b-dropdown-item href="#" @click="empLogout">
              <font-awesome-icon
                class="folder-icon"
                icon="fa-arrow-right-from-bracket"
              />
              로그아웃</b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <!-- Topbar e -->
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const empStore = "empStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(empStore, ["empInfo"]),
  },
  methods: {
    ...mapActions(empStore, ["logout"]),
    empLogout() {
      this.logout();
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
#head-navbar-brand {
  width: 220px;
  margin: 0;
  flex-shrink: 0;
}
@media (max-width: 991px) {
  #head-navbar-brand {
    display: none;
  }
}
.navbar {
  padding-left: 0;
}

/* 어사이드 */
#adminMain {
  height: 40px;
  text-align: center;
  text-decoration: none;
  color: white;
  font-weight: 300;
  font-size: 1em;
}

/*공지사항 s*/
#container {
  color: #999;
  text-transform: uppercase;
  font-size: 24px;
  font-weight: bold;
  width: 40%;
  bottom: 50%;
  display: block;
  float: left;
}

#flip {
  height: 50px;
  overflow: hidden;
}

#flip > div > div {
  color: #fff;
  padding: 4px 12px;
  height: 50px;
  margin-bottom: 50px;
  display: inline-block;
}

#flip div:first-child {
  animation: show 7s linear infinite;
}

#flip div div {
  background: #ff859d;
}

#flip div:first-child div {
  background: #46ddff;
}

#flip div:last-child div {
  background: #89ffa8;
}

@keyframes show {
  0% {
    margin-top: -270px;
  }
  5% {
    margin-top: -180px;
  }
  33% {
    margin-top: -180px;
  }
  38% {
    margin-top: -90px;
  }
  66% {
    margin-top: -90px;
  }
  71% {
    margin-top: 0px;
  }
  99.99% {
    margin-top: 0px;
  }
  100% {
    margin-top: -270px;
  }
}
/*공지사항 e */
</style>
