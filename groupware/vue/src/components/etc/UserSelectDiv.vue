<template>
  <div class="user-select-div">
    <div>
      <input type="text" placeholder="이름" title="이름" />
      <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
    </div>
    <div class="tree-div">
      <ul>
        <tree-list
          v-for="(folder, index) in treeData"
          :key="index"
          :folder="folder"
          kind="select"
        >
        </tree-list>
      </ul>
    </div>
  </div>
</template>

<script>
import TreeList from "@/components/etc/TreeList.vue";

export default {
  components: { TreeList },
  name: "UserSelectDiv",
  data() {
    return {
      treeData: [
        {
          name: "My Tree",
          children: [
            { name: "hello" },
            { name: "wat" },
            { name: "child folder" },
          ],
        },
        {
          name: "My Tree",
          children: [
            { name: "hello" },
            { name: "wat" },
            { name: "child folder" },
          ],
        },
        {
          name: "My Tree",
          children: [
            { name: "hello" },
            { name: "wat" },
            { name: "child folder" },
          ],
        },
        {
          name: "My Tree",
          children: [
            { name: "hello" },
            { name: "wat" },
            { name: "child folder" },
          ],
        },
        {
          name: "사장",
        },
      ],
    };
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
