<template>
  <div
    v-b-modal.oriModal
    @click="modalShow"
    :class="{ 'search-div': isSearch }"
  >
    <span><font-awesome-icon icon="fa-regular fa-address-card" /></span>
    <div class="item-div">
      {{ item.name }}
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
const empModalStore = "empModalStore";
export default {
  name: "TreeListItemOri",
  props: {
    item: Object,
    searchKeyword: String,
  },
  data() {
    return {
      isSearch: false,
    };
  },
  watch: {
    searchKeyword: function (nv) {
      if (nv == "") {
        this.isSearch = false;
      } else if (this.item.name.includes(nv)) {
        this.isSearch = true;
      } else {
        this.isSearch = false;
      }
    },
  },
  methods: {
    ...mapMutations(empModalStore, [
      "SET_NO",
      "SET_NAME",
      "SET_PHOTO",
      "SET_TEL",
      "SET_EMAIL",
    ]),
    modalShow() {
      this.SET_NO(this.item.empNo);
      this.SET_NAME(this.item.name);
      this.SET_PHOTO(this.item.empPhoto);
      this.SET_TEL(this.item.empTel);
      this.SET_EMAIL(this.item.empEmail);
    },
  },
};
</script>

<style scoped>
.item-div {
  display: inline;
}
.search-div {
  color: rgb(205, 14, 14);
}
</style>
