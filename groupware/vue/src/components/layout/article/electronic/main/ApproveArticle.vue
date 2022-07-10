<template>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">승인된 문서 목록</h6>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <colgroup>
            <col style="width: 60%" />
            <col style="width: 20%" />
            <col style="width: 20%" />
          </colgroup>
          <thead class="table-primary">
            <tr>
              <th scope="col">제목</th>
              <th scope="col">기안자</th>
              <th scope="col">날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="approveSendList == ''">
              <td colspan="3" align="center">최근 항목이 없습니다.</td>
            </tr>
            <tr v-else v-for="(item, index) in approveSendList" :key="index">
              <td>{{ item.electronicTitle }}</td>
              <td>{{ item.empName }}</td>
              <td>{{ $dayjs(item.electronicDate).format("YYYY-MM-DD") }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { sendList } from "@/api/electronic";
import { mapState } from "vuex";
const empStore = "empStore";

export default {
  name: "ApproveArticle",
  data() {
    return {
      approveSendList: "",
    };
  },
  computed: {
    ...mapState(empStore, ["empInfo"]),
  },
  methods: {},
  created() {
    sendList(
      {
        currentPage: 1,
        perPage: 5,
        electronicCompletFlag: 1,
        empNo: this.empInfo.empNo,
        electronicDraft: 0,
      },
      (res) => {
        this.approveSendList = res.data.list;
      },
      () => {},
    );
  },
};
</script>

<style></style>
