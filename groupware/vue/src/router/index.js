import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/index";

Vue.use(VueRouter);

const onlyAuthEmp = async (to, from, next) => {
  const checkEmpInfo = store.getters["empStore/checkEmpInfo"];
  if (checkEmpInfo == null) {
    alert("로그인 해야 합니다.");
    next({ name: "login" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "login",
    beforeEnter: (to, from, next) => {
      const checkEmpInfo = store.getters["empStore/checkEmpInfo"];
      if (checkEmpInfo == null) {
        next();
      } else {
        next({ name: "home" });
      }
    },
    component: () => import("@/views/LoginView.vue"),
  },
  {
    path: "/home",
    name: "home",
    beforeEnter: onlyAuthEmp,
    component: () => import("@/views/CommonView.vue"),
    redirect: "/home/main",
    children: [
      {
        path: "main",
        name: "main",
        component: () => import("@/views/HomeView.vue"),
      },
      {
        path: "electronic",
        name: "electronic",
        component: () => import("@/views/ElectronicView.vue"),
        redirect: "/home/electronic/main",
        children: [
          {
            path: "main",
            name: "electronicMain",
            component: () =>
              import("@/components/layout/section/electronic/MainSection.vue"),
          },
          {
            path: "form",
            name: "electronicForm",
            component: () =>
              import(
                "@/components/layout/section/electronic/form/MainSection.vue"
              ),
            redirect: "/home/electronic/form/recently",
            children: [
              {
                path: "recently",
                name: "electronicFormRecently",
                component: () =>
                  import(
                    "@/components/layout/section/electronic/form/RecentlyUseSection.vue"
                  ),
              },
              {
                path: "register/:electronicNo/:electronicDratf/:folderNo/:folderStyleNo",
                name: "electronicFormRegister",
                component: () =>
                  import(
                    "@/components/layout/section/electronic/form/RegisterSection.vue"
                  ),
                props: true,
              },
              {
                path: "detail/:kind/:listTitle/:electronicNo",
                name: "electronicFormDetail",
                component: () =>
                  import(
                    "@/components/layout/section/electronic/form/DetailSection.vue"
                  ),
                props: true,
              },
            ],
          },
          {
            path: "receive/:listTitle",
            name: "electronicReceive",
            component: () =>
              import(
                "@/components/layout/section/electronic/receive/MainSection"
              ),
            props: true,
          },
          {
            path: "send/:listTitle",
            name: "electronicSend",
            component: () =>
              import("@/components/layout/section/electronic/send/MainSection"),
            props: true,
          },
        ],
      },
    ],
  },
  {
    path: "/home/birthday",
    name: "birthday",
    component: () => import("@/components/etc/BirthDayPopUp.vue"),
  },
  {
    path: "/email",
    name: "email",
    beforeEnter: onlyAuthEmp,
    component: () => import("@/views/CommonView.vue"),
    redirect: "/email/home/main",
    children: [
      {
        path: "home",
        name: "emailHome",
        beforeEnter: onlyAuthEmp,
        redirect: "/email/home/main",
        component: () => import("@/views/EmailView.vue"),
        children: [
          {
            path: "main",
            name: "emailMain",
            beforeEnter: onlyAuthEmp,
            component: () =>
              import("@/components/layout/section/email/MainSection.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    beforeEnter: onlyAuthEmp,
    component: () => import("@/views/CommonView.vue"),
    redirect: "/board/main",
    children: [
      {
        path: "main",
        name: "boardMain",
        beforeEnter: onlyAuthEmp,
        component: () => import("@/components/layout/nav/BoardNav.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
