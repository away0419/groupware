<template>
  <li>
    <div class="tree-li-div" @click="toggle">
      <div class="folder-div">
        <font-awesome-icon v-if="isFolder" :icon="folderListIcon" />
      </div>
      <div class="bold">
        <font-awesome-icon class="folder-icon" :icon="folderIcon" />
        {{ folder.name }}
      </div>
    </div>
    <div class="tree-child-div" v-show="isOpen" v-if="isFolder">
      <div>
        <component
          :is="componentArr[componentIndex]"
          class="item"
          v-for="(child, index) in folder.children"
          :key="index"
          :item="child"
          :searchKeyword="searchKeyword"
        >
        </component>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  components: {
    TreeListItemOri: () => import("@/components/etc/TreeListItemOri.vue"),
    TreeListItemEle: () => import("@/components/etc/TreeListItemEle.vue"),
  },
  name: "TreeList",
  props: {
    folder: Object,
    kind: String,
    searchKeyword: String,
  },
  data: function () {
    return {
      isOpen: false,
      folderIcon: "fa-regular fa-folder",
      folderListIcon: "fa-regular fa-square-plus",
      componentArr: ["TreeListItemOri", "TreeListItemEle"],
      componentIndex: 0,
    };
  },
  created() {
    if (this.kind == "ori") {
      this.componentIndex = 0;
    } else if (this.kind == "electronic") {
      this.componentIndex = 1;
    }
  },
  computed: {
    isFolder: function () {
      return this.folder.children && this.folder.children.length;
    },
  },
  watch: {
    searchKeyword: function (nv) {
      let res = false;
      if (nv != "") {
        for (let i = 0; i < this.folder.children.length; i++) {
          if (this.folder.children[i].name.includes(nv)) {
            res = true;
            break;
          }
        }
      }
      this.isOpen = res;
    },
  },
  methods: {
    toggle: function () {
      if (this.isFolder) {
        this.isOpen = !this.isOpen;
        if (this.isOpen) {
          this.folderIcon = "fa-regular fa-folder-open";
          this.folderListIcon = "fa-regular fa-square-minus";
        } else {
          this.folderIcon = "fa-regular fa-folder";
          this.folderListIcon = "fa-regular fa-square-plus";
        }
      }
    },
    makeFolder: function () {
      if (!this.isFolder) {
        this.isOpen = true;
      }
    },
  },
};
</script>

<style scoped>
.tree-child-div > div > div {
  padding-left: 2em;
}
.tree-child-div > div > div:hover {
  background-color: rgb(180, 218, 240);
  cursor: pointer;
}
.folder-div {
  flex-basis: 16px;
  flex-shrink: 0;
}
.bold {
  flex-basis: 80%;
}
.tree-li-div {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.tree-li-div:hover {
  background-color: rgb(180, 218, 240);
  cursor: pointer;
}
</style>
