import { login } from "@/api/emp";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    empInfo: null,
  },
  getters: {
    checkEmpInfo: function (state) {
      return state.empInfo;
    },
  },
  actions: {
    async login({ commit }, emp) {
      await login(
        emp,
        (res) => {
          if (res.data.result) {
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_EMP_INFO", res.data.emp);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    logout({ commit }) {
      commit("SET_EMP_INFO", null);
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_LOGIN_ERROR", true);
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_EMP_INFO: (state, empInfo) => {
      state.empInfo = empInfo;
    },
  },
};
