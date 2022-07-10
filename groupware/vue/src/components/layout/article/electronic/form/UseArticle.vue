<template>
  <div>
    <div class="container-table100">
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary">최근 사용 양식</h6>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover">
              <colgroup>
                <col style="width: 60%" />
                <col style="width: 40%" />
              </colgroup>
              <thead class="table-info">
                <tr>
                  <th scope="col">양식</th>
                  <th scope="col">사용 날짜</th>
                </tr>
              </thead>
              <tbody>
                <tr v-if="useList == '' || useList == null">
                  <td colspan="3" align="center">최근 사용 양식이 없습니다.</td>
                </tr>
                <tr v-else v-for="(item, index) in useList" :key="index">
                  <td>{{ item.styleName }}</td>
                  <td>{{ $dayjs(item.useDate).format("YYYY-MM-DD") }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { recentlyList } from "@/api/electronic";
import { mapState } from "vuex";

const empStore = "empStore";

export default {
  name: "UseArticle",
  data() {
    return {
      useList: null,
    };
  },
  computed: {
    ...mapState(empStore, ["empInfo"]),
  },
  created() {
    recentlyList(
      {
        empNo: this.empInfo.empNo,
      },
      (res) => {
        this.useList = res.data.list;
      },
    );
  },
};
</script>

<style scoped>
.card {
  margin-top: 24px;
}
</style>
