<template>
  <div>
    <div class="title-div">
      <h3 class="title-h3">{{ titleName }}</h3>
      <b-select class="cnt-select" v-model="perPage">
        <b-select-option value="15">15개</b-select-option>
        <b-select-option value="30">30개</b-select-option>
        <b-select-option value="50">50개</b-select-option>
      </b-select>
    </div>

    <b-table
      :items="items"
      :per-page="perPage"
      :current-page="1"
      :fields="fields"
      hover
      head-variant="light"
      small
      class="list-table"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
      id="my-table"
      @row-clicked="rowClick"
      :busy="isBusy"
      tbody-tr-class="row-tr"
      empty-text="해당 목록 없음"
      show-empty
    >
      <template #table-busy>
        <div class="text-center text-danger my-2">
          <b-spinner class="align-middle"></b-spinner>
          <strong>Loading...</strong>
        </div>
      </template>
    </b-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>

    <div class="list-search-div">
      <b-form-select
        v-model="selected"
        :options="options"
        size="sm"
      ></b-form-select>
      <b-form-input size="sm" v-model="searchKeyword"></b-form-input>
      <b-button size="sm" @click="myProvider">검색</b-button>
    </div>
  </div>
</template>

<script>
import { receiveList, readList } from "@/api/electronic";
import { mapState } from "vuex";
const empStore = "empStore";

export default {
  name: "ListArticle",
  props: {
    listTitle: String,
  },
  data() {
    return {
      titleName: "전체 목록",
      titleNo: 0,
      perPage: 15,
      currentPage: 1,
      rows: 1,
      searchKeyword: "",
      sortBy: "electronicDate",
      sortDesc: true,
      isBusy: false,
      fields: [
        {
          key: "electronicDate",
          label: "기안일",
          formatter: (value) => {
            return this.$dayjs(value).format("YYYY-MM-DD");
          },
          sortable: true,
          thStyle: "min-width:160px;width:160px ",
        },
        {
          key: "electronicEmergencyFlag",
          label: "긴급",
          formatter: (value) => {
            if (value == 0) return "";
            else return "긴급";
          },
          sortable: true,
          thStyle: "min-width:60px; width:60px",
        },
        {
          key: "styleName",
          label: "양식",
          sortable: true,
          thStyle: "min-width:150px",
        },
        {
          key: "electronicTitle",
          label: "제목",
          sortable: true,
          thStyle: "min-width:350px",
        },
        {
          key: "empName",
          label: "기안자",
          sortable: true,
          thStyle: "min-width:100px",
        },
      ],
      items: null,
      selected: null,
      options: [
        { value: null, text: "선택" },
        { value: "ELECTRONIC_TITLE", text: "제목" },
        { value: "EMP_NAME", text: "기안자" },
        { value: "STYLE_NAME", text: "결재양식" },
      ],
    };
  },
  created() {
    if (this.listTitle == "wait") {
      this.titleNo = 1;
      this.titleName = "결재 대기 목록";
    } else if (this.listTitle == "plan") {
      this.titleNo = 2;
      this.titleName = "결재 예정 목록";
    } else if (this.listTitle == "approve") {
      this.titleNo = 3;
      this.titleName = "결재 승인 목록";
    } else if (this.listTitle == "return") {
      this.titleNo = 4;
      this.titleName = "결재 반려 목록";
    } else if (this.listTitle == "read") {
      this.titleNo = 5;
      this.titleName = "수신용 결재 목록";
    }
    this.myProvider();
  },
  computed: {
    ...mapState(empStore, ["empInfo"]),
  },
  watch: {
    currentPage: function () {
      this.myProvider();
    },
    perPage: function () {
      this.myProvider();
    },
    sortBy: function () {
      this.myProvider();
    },
    sortDesc: function () {
      this.myProvider();
    },
  },
  methods: {
    async myProvider() {
      this.isBusy = true;
      let params = {
        currentPage: this.currentPage,
        perPage: this.perPage,
        electronicCompletFlag: 0,
        empNo: this.empInfo.empNo,
        approvalLineCompleteFlag: 0,
        searchCondition: this.selected,
        searchKeyword: this.searchKeyword,
        sortBy: this.sortBy,
        sortDesc: this.sortDesc,
      };
      if (this.titleNo == 1) {
        await receiveList(
          params,
          (res) => {
            this.items = res.data.list;
            this.rows = res.data.totalRecord;
          },
          () => {},
        );
      } else if (this.titleNo == 2) {
        params.approvalLineCompleteFlag = 3;

        await receiveList(
          params,
          (res) => {
            this.items = res.data.list;
            this.rows = res.data.totalRecord;
          },
          () => {},
        );
      } else if (this.titleNo == 3) {
        params.electronicCompletFlag = 1;
        params.approvalLineCompleteFlag = 1;

        await receiveList(
          params,
          (res) => {
            this.items = res.data.list;
            this.rows = res.data.totalRecord;
          },
          () => {},
        );
      } else if (this.titleNo == 4) {
        params.electronicCompletFlag = 2;
        params.approvalLineCompleteFlag = 2;

        await receiveList(
          params,
          (res) => {
            this.items = res.data.list;
            this.rows = res.data.totalRecord;
          },
          () => {},
        );
      } else if (this.titleNo == 5) {
        await readList(
          params,
          (res) => {
            this.items = res.data.list;
            this.rows = res.data.totalRecord;
          },
          () => {},
        );
      }
      this.isBusy = false;
    },
    rowClick(item) {
      this.$router.push({
        name: "electronicFormDetail",
        params: {
          kind: "receive",
          listTitle: this.listTitle,
          electronicNo: item.electronicNo,
        },
      });
    },
  },
};
</script>
<style scoped>
.title-div {
  display: flex;
  text-align: left;
  margin: 1.2rem;
}
.list-table {
  border: 1px solid #dee2e6;
}
.list-search-div {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.list-search-div > select {
  width: 120px;
}
.list-search-div > input {
  width: 400px;
}
.list-search-div > * {
  margin: 0px 5px;
}
.title-h3 {
  margin-right: 10px;
  margin-bottom: 0px;
  align-self: center;
}
.cnt-select {
  width: 80px;
}
::v-deep .row-tr:hover {
  cursor: pointer;
}
</style>
