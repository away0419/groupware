<template>
  <div @click="click" :class="{ 'search-div': isSearch }">
    <span><font-awesome-icon icon="fa-regular fa-file" /></span>
    <div class="item-div">
      {{ item.name }}
    </div>
  </div>
</template>

<script>
export default {
  name: "TreeListItemEle",
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
    click() {
      this.$bvModal.hide("electronicFormModal");
      this.$router.push({
        name: "electronicFormRegister",
        params: {
          folderNo: this.item.folderNo,
          folderStyleNo: this.item.folderStyleNo,
          electronicDratf: 0,
          electronicNo: 0,
        },
      });
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
