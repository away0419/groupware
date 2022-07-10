<template>
  <div>
    <div class="form-header">
      <p>유류대실비청구서</p>
    </div>
    <div class="form-body">
      <table class="table table-bordered table-hover">
        <colgroup>
          <col style="width: 20%" />
          <col style="width: 80%" />
        </colgroup>
        <tbody>
          <tr>
            <td align="center">운행 기간</td>
            <td class="datepicker-td" v-if="!isContent">
              <b-form-datepicker
                class="b-form-datepicker"
                v-model="startDate"
                :min="startMin"
                placeholder="시작 날짜"
              /><span class="v-a-middle"> ~ </span
              ><b-form-datepicker
                class="b-form-datepicker"
                v-model="endDate"
                :min="endMin"
                placeholder="끝 날짜"
              />
            </td>
            <td class="detail-td" v-else>
              <span v-html="content.startDate"></span>
              <span class="v-a-middle"> ~ </span>
              <span v-html="content.endDate"></span>
            </td>
          </tr>
          <tr>
            <td align="center">출발지</td>
            <td :class="{ 'detail-td': isContent }">
              <b-input v-if="!isContent" v-model="content.item1" />
              <span v-else v-html="content.item1"></span>
            </td>
          </tr>
          <tr>
            <td align="center">도착지</td>
            <td :class="{ 'detail-td': isContent }">
              <b-input v-if="!isContent" v-model="content.item2" />
              <span v-else v-html="content.item2"></span>
            </td>
          </tr>
          <tr>
            <td align="center">운행 거리(Km)</td>
            <td :class="{ 'detail-td': isContent }">
              <b-input v-if="!isContent" v-model="content.item3" />
              <span v-else v-html="content.item3"></span>
            </td>
          </tr>
          <tr>
            <td align="center">유류 소요량</td>
            <td :class="{ 'detail-td': isContent }">
              <b-input v-if="!isContent" v-model="content.item4" />
              <span v-else v-html="content.item4"></span>
            </td>
          </tr>
          <tr>
            <td align="center">운행 목적</td>
            <td :class="{ 'detail-td': isContent }">
              <b-form-textarea
                v-if="!isContent"
                rows="3"
                max-rows="6"
                v-model="content.item5"
              />
              <span v-else v-html="content.item5"></span>
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
  name: "ExpenseForm",
  props: {
    elContent: String,
    dContent: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      startMin: new Date(),
      startDate: "",
      endDate: "",
      content: {
        item1: "",
        item2: "",
        item3: "",
        item4: "",
        item5: "",
        startDate: "",
        endDate: "",
      },
    };
  },
  computed: {
    endMin() {
      if (this.startDate == "") {
        return new Date();
      } else {
        return this.startDate;
      }
    },
    isContent() {
      if (this.elContent == null || this.elContent == "") return false;
      return true;
    },
  },
  watch: {
    startDate: function (val) {
      if (val > this.endDate) this.endDate = val;
      this.content.startDate = val;
    },
    endDate: function (val) {
      this.content.endDate = val;
    },
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
          this.startDate = this.content.startDate;
          this.endDate = this.content.endDate;
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
