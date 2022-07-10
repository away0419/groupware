<template>
  <div class="ori" :class="oriClasses">
    <div>
      <input
        type="text"
        placeholder="이름"
        title="이름"
        id="ori-input"
        @input="search"
      />
      <button type="button" class="ori-search-btn" @click="oriToggle">
        <font-awesome-icon class="fa-angle-up" :icon="oriIcon" />
      </button>
    </div>
    <div v-show="isOri" class="tree-div">
      <ul>
        <tree-list
          v-for="(folder, index) in treeData"
          :key="index"
          :folder="folder"
          kind="ori"
          :searchKeyword="searchKeyword"
        >
        </tree-list>
      </ul>
    </div>
  </div>
</template>

<script>
import TreeList from "@/components/etc/TreeList.vue";
import { selectEmpTree } from "@/api/emp";

export default {
  components: { TreeList },
  name: "OrganizationDiv",
  data() {
    return {
      isOri: false,
      oriIcon: "fa-solid fa-angle-up",
      treeData: null,
      searchKeyword: "",
    };
  },
  created() {
    selectEmpTree(
      {
        kind: "ori",
      },
      (res) => {
        this.treeData = res.data.treeData;
      },
      () => {},
    );
  },
  computed: {
    oriClasses() {
      return {
        oriClosed: !this.isOri,
        oriOpend: this.isOri,
      };
    },
  },
  methods: {
    oriToggle() {
      this.isOri = !this.isOri;
      if (this.isOri) {
        this.oriIcon = "fa-solid fa-angle-down";
      } else {
        this.oriIcon = "fa-solid fa-angle-up";
      }
    },
    search(e) {
      this.searchKeyword = e.target.value;
      this.isOri = true;
      this.oriIcon = "fa-solid fa-angle-down";
    },
  },
};
</script>

<style scoped>
/*조직도 검색창 s */
.fa-angle-up {
  color: white;
}
.ori-search-btn {
  border-style: none;
  background-color: #434343;
  padding-left: 12px;
}
.oriClosed {
  width: 220px;
  bottom: 0px;
  padding: 5px 0px;
  background: #434343;
  display: block;
  position: fixed;
  left: 0;
}
.oriOpend {
  width: 220px;
  height: 300px;
  bottom: 0px;
  padding: 5px 0px;
  background: #434343;
  display: block;
  position: fixed;
  left: 0;
}
#ori-input {
  width: 180px;
}
/*조직도 검색창 e */

/* 트리 s */
.tree-div {
  height: 255px;
  background-color: white;
  margin: 5px 6px;
  overflow: auto;
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
