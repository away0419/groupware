import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import empStore from "@/store/modules/empStore";
import empModalStore from "@/store/modules/empModalStore";
import electronicStore from "@/store/modules/electronicStore";

export default new Vuex.Store({
  modules: {
    empStore,
    empModalStore,
    electronicStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
