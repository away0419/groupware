export default {
  namespaced: true,
  state: {
    empNo: null,
    empName: null,
    empPhoto: null,
    empTel: null,
    empEmail: null,
    searchKeyword: "",
  },
  getters: {
    isSearch(state) {
      if (state.searchKeyword != "") return true;
      return false;
    },
  },
  actions: {},
  mutations: {
    SET_NO(state, data) {
      state.empNo = data;
    },
    SET_NAME(state, data) {
      state.empName = data;
    },
    SET_PHOTO(state, data) {
      state.empPhoto = data;
    },
    SET_TEL(state, data) {
      state.empTel = data;
    },
    SET_EMAIL(state, data) {
      state.empEmail = data;
    },
    SET_SEARCHKEYWORD(state, data) {
      state.searchKeyword = data;
    },
  },
};
