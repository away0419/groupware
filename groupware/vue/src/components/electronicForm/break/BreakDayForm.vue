<template>
  <div>
    <div class="form-header">
      <p>휴가 신청서</p>
    </div>
    <div class="form-body">
      <table class="table table-bordered table-hover">
        <colgroup>
          <col style="width: 20%" />
          <col style="width: 80%" />
        </colgroup>
        <tbody>
          <tr>
            <td align="center">휴가 종류</td>
            <td :class="{ 'detail-td': isContent }">
              <b-select
                v-if="!isContent"
                v-model="content.item1"
                :options="options1"
              ></b-select>
              <span v-else v-html="content.item1"></span>
            </td>
          </tr>
          <tr>
            <td align="center">휴가 기간</td>
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
            <td align="center">휴가 사유</td>
            <td :class="{ 'detail-td': isContent }">
              <b-form-textarea
                v-if="!isContent"
                rows="3"
                max-rows="6"
                v-model="content.item2"
              />
              <span v-else v-html="content.item2"></span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { getThemeList } from "@/api/breakDay";
import { mapMutations } from "vuex";
const electronicStore = "electronicStore";

export default {
  name: "BreakDayForm",
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
      options1: [],
      content: {
        item1: "",
        item2: "",
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
  created() {
    if (!this.isContent) {
      getThemeList(
        (res) => {
          res.data.themeList.forEach((e) => {
            this.options1.push(e.breakthemeName);
          });
        },
        (res) => {
          console.log(res);
        },
      );
    }
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
          this.startDate = this.content.startDate;
          this.endDate = this.content.endDate;
        }
      },
    },
    startDate: function (val) {
      if (val > this.endDate) this.endDate = val;
      this.content.startDate = val;
    },
    endDate: function (val) {
      this.content.endDate = val;
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
