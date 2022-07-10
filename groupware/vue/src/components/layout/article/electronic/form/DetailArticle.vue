<template>
  <div>
    <div class="container-table100">
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" v-text="titleName"></h6>
        </div>
        <div class="card-body">
          <div class="approver-div">
            <sign-div
              v-for="(approve, index) in approver"
              :key="index"
              :approve="approve"
            ></sign-div>
          </div>
          <component
            :is="
              folderArr[elSty.folderNo - 1].styleArr[elSty.folderStyleNo - 1]
            "
            :elContent="elImp.electronicContent"
            class="component-div"
          ></component>
        </div>
        <div class="register-bottom-div">
          <div class="file-div" v-if="isFile">
            <div class="file-header-div">
              <span v-text="'첨부파일(' + files.length + ')'"></span>
            </div>
            <div class="file-list-div" v-if="isFile">
              <div
                class="file-item-div"
                v-for="(file, index) in files"
                :key="index"
                @click="fileDownLoad(file)"
              >
                {{ file.fileOriginalname }}
              </div>
            </div>
          </div>
          <div class="confirm-div" v-if="isWait">
            <b-button type="button" class="confirm-item" @click="approveClick"
              >승인
            </b-button>
            <b-button class="confirm-item" @click="deniedClick">반려 </b-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { detailElimp, download, elApprove, elDenied } from "@/api/electronic";
import { mapState } from "vuex";
import SignDiv from "@/components/etc/SingDiv.vue";

const electronicStore = "electronicStore";
const empStore = "empStore";

export default {
  components: {
    BreakDayForm: () =>
      import("@/components/electronicForm/break/BreakDayForm.vue"),
    BreakBasic2Form: () =>
      import("@/components/electronicForm/break/BreakBasic2Form.vue"),
    BreakBasic3Form: () =>
      import("@/components/electronicForm/break/BreakBasic3Form.vue"),
    ExpenseForm: () =>
      import("@/components/electronicForm/expense/ExpenseForm.vue"),
    ExpenseBasic2Form: () =>
      import("@/components/electronicForm/expense/ExpenseBasic2Form.vue"),
    ExpenseBasic3Form: () =>
      import("@/components/electronicForm/expense/ExpenseBasic3Form.vue"),
    ReportBasic1Form: () =>
      import("@/components/electronicForm/report/ReportBasic1Form.vue"),
    ReportBasic2Form: () =>
      import("@/components/electronicForm/report/ReportBasic2Form.vue"),
    SignDiv,
  },
  name: "DetailArticle",
  data() {
    return {
      folderArr: [
        {
          styleArr: ["BreakDayForm", "BreakBasic2Form", "BreakBasic3Form"],
        },
        {
          styleArr: ["ExpenseForm", "ExpenseBasic2Form", "ExpenseBasic3Form"],
        },
        {
          styleArr: ["ReportBasic1Form", "ReportBasic2Form"],
        },
      ],
      elImp: {},
      elSty: {
        folderNo: 1,
        folderStyleNo: 1,
      },
      isWait: false,
      titleName: "결재 조회",
      titleNo: 0,
      approver: [],
      isFile: false,
      files: [],
    };
  },
  props: {
    electronicNo: {
      type: [Number, String],
      require: true,
    },
    listTitle: {
      type: String,
      require: true,
    },
    kind: {
      type: String,
      require: true,
    },
  },
  computed: {
    ...mapState(empStore, ["empInfo"]),
    ...mapState(electronicStore, ["content"]),
  },
  watch: {},
  created() {
    if (this.kind == "receive") {
      if (this.listTitle == "wait") {
        this.titleName = "결재 대기 목록";
        this.isWait = true;
        this.titleNo = 0;
      } else if (this.listTitle == "plan") {
        this.titleName = "결재 예정 목록";
        this.titleNo = 1;
      } else if (this.listTitle == "approve") {
        this.titleName = "결재 승인 목록";
        this.titleNo = 2;
      } else if (this.listTitle == "return") {
        this.titleName = "결재 반려 목록";
        this.titleNo = 3;
      } else if (this.listTitle == "read") {
        this.titleName = "수신용 결재 목록";
        this.titleNo = 4;
      }
    } else if (this.kind == "send") {
      if (this.listTitle == "wait") {
        this.titleName = "결재 대기 목록";
        this.titleNo = 5;
      } else if (this.listTitle == "approve") {
        this.titleName = "결재 승인 목록";
        this.titleNo = 6;
      } else if (this.listTitle == "return") {
        this.titleName = "결재 반려 목록";
        this.titleNo = 7;
      }
    } else {
      alert("잘못된 접근");
      this.$router.push({ name: "home" });
    }

    if (this.titleNo < 0 || this.titleNo > 7) {
      alert("잘못된 접근");
      this.$router.push({ name: "home" });
    }
    detailElimp(
      {
        electronicNo: this.electronicNo,
        titleNo: this.titleNo,
        empNo: this.empInfo.empNo,
      },
      (res) => {
        this.elImp = res.data.elDto;
        this.elSty = res.data.elStyDto;
        this.approver = res.data.approver;
        this.files = res.data.fileList;
        if (this.files != "") {
          this.isFile = true;
        }
      },
      (res) => {
        console.log(res);
        alert("예상치 못한 상태");
        this.$router.push({ name: "home" });
      },
    );
  },
  methods: {
    fileDownLoad(file) {
      download(
        file,
        (response) => {
          const blob = new Blob([response.data]);
          const fileObjectUrl = window.URL.createObjectURL(blob); // blob 객체 URL을 설정할 링크를 만듭니다.

          const link = document.createElement("a");
          link.href = fileObjectUrl;
          link.style.display = "none"; // 다운로드 파일 이름을 지정 할 수 있습니다. // 일반적으로 서버에서 전달해준 파일 이름은 응답 Header의 Content-Disposition에 설정됩니다.

          link.download = file.fileOriginalname;
          document.body.appendChild(link);
          link.click();
          link.remove(); // 다운로드가 끝난 리소스(객체 URL)를 해제합니다.

          window.URL.revokeObjectURL(fileObjectUrl);
        },
        (res) => {
          console.log(res);
        },
      );
    },
    approveClick() {
      elApprove(
        {
          electronicNo: this.elImp.electronicNo,
          empNo: this.empInfo.empNo,
        },
        () => {
          this.$router.push({
            name: "electronicFormDetail",
            params: {
              kind: "receive",
              listTitle: "read",
              electronicNo: this.elImp.electronicNo,
            },
          });
        },
        (res) => {
          console.log(res);
        },
      );
    },
    deniedClick() {
      elDenied(
        {
          electronicNo: this.elImp.electronicNo,
          empNo: this.empInfo.empNo,
        },
        () => {
          this.$router.push({
            name: "electronicFormDetail",
            params: {
              kind: "receive",
              listTitle: "return",
              electronicNo: this.elImp.electronicNo,
            },
          });
        },
        (res) => {
          console.log(res);
        },
      );
    },
  },
};
</script>

<style scoped>
.card {
  margin-top: 24px;
}
.component-div {
  border: 1px solid;
  margin: 15px 0px;
}
.register-bottom-div {
  padding: 0px 20px 20px 20px;
}
.select-user-div {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
}
.select-user-div > button {
  margin-right: 10px;
}
.confirm-div {
  display: flex;
  flex-direction: row-reverse;
  margin-bottom: 10px;
  align-content: center;
}
.confirm-div > button {
  margin-left: 10px;
}
.confirm-item {
  align-self: center;
}
.file-b-badge {
  margin-right: 5px;
}
::v-deep .detail-td {
  text-align: left;
}
.approver-div {
  display: flex;
  justify-content: flex-end;
}
::v-deep .form-header {
  background-color: rgb(251, 239, 239);
}
::v-deep .form-body tbody > tr > td:first-child {
  background-color: rgb(251, 239, 239);
}
.file-div {
  border: 1px solid;
}
.file-header-div {
  border-bottom: 1px solid;
  background-color: antiquewhite;
}
.file-item-div:hover {
  cursor: pointer;
  background-color: rgba(212, 230, 254, 0.591);
}
</style>
