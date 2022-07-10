export default {
  namespaced: true,
  state: {
    content: "",
  },
  getters: {},
  actions: {},
  mutations: {
    SET_CONTENT(state, data) {
      state.content = data;
    },
  },
};
