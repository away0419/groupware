import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueCookies from "vue-cookies";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "@/fontAwesomeIcon.js";
import Dayjs from "vue-dayjs";

import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

Vue.use(VueCookies);
Vue.use(BootstrapVue);
Vue.use(Dayjs);
Vue.component("TreeSelect", Treeselect);

Vue.$cookies.config("90d");
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
