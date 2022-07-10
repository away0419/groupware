<template>
  <div>
    <div class="form-header">
      <p>보고 양식2</p>
    </div>
    <div class="form-body">
      <table class="table table-bordered table-hover">
        <colgroup>
          <col style="width: 20%" />
          <col style="width: 80%" />
        </colgroup>
        <tbody>
          <tr>
            <td align="center">보고 목적</td>
            <td :class="{ 'detail-td': isContent }">
              <b-input v-if="!isContent" v-model="content.item1" />
              <span v-else v-html="content.item1"></span>
            </td>
          </tr>
          <tr>
            <td align="center">보고 내용</td>
            <td :class="{ 'detail-td': isContent }">
              <b-input v-if="!isContent" v-model="content.item2" />
              <span v-else v-html="content.item2"></span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
const electronicStore = "electronicStore";

export default {
  name: "ReportBasic2Form",
  props: {
    elContent: String,
    dContent: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      content: {
        item1: "",
        item2: "",
      },
    };
  },
  computed: {
    isContent() {
      if (this.elContent == null || this.elContent == "") return false;
      return true;
    },
  },
  watch: {
    elContent: {
      immediate: true,
      handler(elContent) {
        if (elContent != null && elContent != "")
          this.content = JSON.parse(elContent);
      },
    },
    dContent: {
      immediate: true,
      handler(dContent) {
        if (dContent != "") {
          this.content = JSON.parse(dContent);
        }
      },
    },
    content: {
      handler: function (nv) {
        this.SET_CONTENT(JSON.stringify(nv));
      },
      deep: true,
    },
  },
  methods: {
    ...mapMutations(electronicStore, ["SET_CONTENT"]),
  },
};
</script>

<style scoped>
.datepicker-td {
  display: flex;
}
table td {
  vertical-align: middle;
}
.v-a-middle {
  margin: auto;
}
.form-header > p {
  padding: 10px 0;
  margin: 0;
}
</style>
