<template>
  <div class="electronic-form-div">
    <div>
      <input type="text" placeholder="양식" title="양식" @input="search" />
      <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
    </div>
    <div class="tree-div">
      <ul>
        <tree-list
          v-for="(folder, index) in treeData"
          :key="index"
          :folder="folder"
          kind="electronic"
          :searchKeyword="searchKeyword"
        >
        </tree-list>
      </ul>
    </div>
  </div>
</template>

<script>
import TreeList from "@/components/etc/TreeList.vue";
import { selectFormTree } from "@/api/electronic";

export default {
  components: { TreeList },
  name: "ElectronicFormDiv",
  data() {
    return {
      treeData: null,
      searchKeyword: "",
    };
  },
  created() {
    selectFormTree(
      (res) => {
        this.treeData = res.data.treeData;
      },
      () => {},
    );
  },
  methods: {
    search(e) {
      this.searchKeyword = e.target.value;
    },
  },
};
</script>

<style scoped>
/* 트리 s */
.tree-div {
  height: 255px;
  background-color: white;
  margin-top: 5px;
  padding: 5px 6px;
  overflow: auto;
  border: 1px solid #666;
}
.tree-div > ul {
  list-style: none;
  padding: 0;
}
.tree-div > ul > li {
  list-style: none;
  padding: 0;
  text-align: left;
}
/* 트리 e */
</style>
