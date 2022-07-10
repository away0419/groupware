<template>
  <div>
    <div class="container-table100">
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary">기안서 작성</h6>
        </div>
        <div class="card-body">
          <div>
            <input
              type="text"
              class="form-control"
              id="title"
              name="electronicTitle"
              placeholder="제목을 입력해주세요"
              v-model.lazy="title"
            />
          </div>
          <component
            :is="folderArr[folderNo - 1].styleArr[folderStyleNo - 1]"
            class="component-div"
            :dContent="dContent"
          ></component>
        </div>
        <div class="register-bottom-div">
          <div class="select-user-div">
            <b-form-file
              multiple
              placeholder="최대 5개 첨부 가능"
              drop-placeholder="Drop file here..."
              browse-text="파일 선택"
              v-model="files"
              @input="fileCntCheck"
            >
              <template slot="file-name" slot-scope="{ names }">
                <b-badge
                  class="file-b-badge"
                  v-for="(name, index) in names"
                  :key="index"
                  variant="dark"
                  >{{ name }}</b-badge
                >
              </template>
            </b-form-file>
          </div>
          <div class="select-user-div">
            <div>
              <tree-select
                :multiple="true"
                :options="soptions"
                placeholder="결재자를 선택하세요(선택한 순서가 결재 순서입니다)"
                v-model="approver"
                :show-count="true"
                noResultsText="해당 이름 없음"
                :clearable="false"
                :max-height="300"
                valueConsistsOf="LEAF_PRIORITY"
                :disable-branch-nodes="true"
                @select="select"
                @deselect="deselect"
              />
            </div>
          </div>
          <div class="select-user-div">
            <tree-select
              :multiple="true"
              :options="roptions"
              placeholder="수신자를 선택하세요(결재자는 선택 불가합니다)"
              v-model="receiver"
              :show-count="true"
              noResultsText="해당 이름 없음"
              :clearable="true"
              :max-height="300"
              :limit="30"
              valueConsistsOf="LEAF_PRIORITY"
            />
          </div>
          <div class="confirm-div">
            <b-button
              type="button"
              class="confirm-item"
              @click="registerElimpClick"
              >결재 요청
            </b-button>
            <b-button class="confirm-item" @click="draftClick"
              >임시 저장
            </b-button>
            <b-button class="confirm-item">취소</b-button>
            <b-checkbox class="confirm-item" v-model="emergency">
              긴급
            </b-checkbox>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { selectEmpTree } from "@/api/emp";
import { registerElimp, selectDraft } from "@/api/electronic";
import { mapState, mapMutations } from "vuex";
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
  },
  name: "RegisterArticle",
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
      title: "",
      files: [],
      receiver: [],
      roptions: [],
      approver: [],
      soptions: [],
      emergency: false,
      approverNo: 0,
      dContent: "",
    };
  },
  props: {
    folderNo: {
      type: [Number, String],
      require: true,
    },
    folderStyleNo: {
      type: [Number, String],
      require: true,
    },
    electronicNo: {
      type: [Number, String],
      require: true,
    },
    electronicDratf: {
      type: [Number, String],
      require: true,
    },
  },
  computed: {
    ...mapState(empStore, ["empInfo"]),
    ...mapState(electronicStore, ["content"]),
  },
  watch: {
    approver: {
      handler: function () {
        if (this.approver.length > 5) {
          this.approver = this.approver.filter((e) => e != this.approverNo);
        }
      },
      deep: true,
    },
  },
  created() {
    this.SET_CONTENT("");
    if (
      this.electronicDratf < 0 ||
      this.electronicDratf > 1 ||
      (this.electronicNo != 0 && this.electronicDratf == 0) ||
      (this.electronicNo == 0 && this.electronicDratf == 1)
    ) {
      alert("잘못된 접근");
      this.$router.push({ name: "home" });
    }
    if (this.electronicNo > 0 && this.electronicDratf == 1) {
      selectDraft(
        {
          empNo: this.empInfo.empNo,
          electronicNo: this.electronicNo,
        },
        (res) => {
          const elImp = res.data.elDto;
          this.title = elImp.electronicTitle;
          this.dContent = elImp.electronicContent;
        },
        (res) => {
          console.log(res);
          alert("잘못된 접근");
        },
      );
    }
    selectEmpTree(
      {
        kind: "select",
        empNo: this.empInfo.empNo,
      },
      (res) => {
        this.roptions = res.data.treeData;
        this.soptions = this.deepCopy(res.data.treeData);
      },
      () => {},
    );
  },
  methods: {
    ...mapMutations(electronicStore, ["SET_CONTENT"]),

    registerElimpClick() {
      if (!this.registerParamCheck()) return;
      const receiverAll = this.receiver.concat(this.approver);
      const frm = new FormData();
      frm.append("empNo", this.empInfo.empNo);
      frm.append("title", this.title);
      frm.append("content", this.content);
      frm.append("receiver", receiverAll);
      frm.append("approver", this.approver);
      frm.append("folderStyleNo", this.folderStyleNo);
      frm.append("folderNo", this.folderNo);
      frm.append("empName", this.empInfo.empName);
      frm.append("draft", 0);
      frm.append("currentDraft", this.electronicDratf);
      frm.append("elNo", this.electronicNo);
      if (this.emergency) {
        frm.append("emergency", 1);
      } else {
        frm.append("emergency", 0);
      }
      for (let i = 0; i < this.files.length; i++) {
        frm.append(`files`, this.files[i]);
      }
      registerElimp(
        frm,
        (res) => {
          this.$router.push({
            name: "electronicFormDetail",
            params: {
              kind: "send",
              listTitle: "wait",
              electronicNo: res.data.electronicNo,
            },
          });
        },
        (res) => {
          console.log(res);
        },
      );
    },
    draftClick() {
      if (!this.draftParamCheck()) return;
      const frm = new FormData();
      frm.append("empNo", this.empInfo.empNo);
      frm.append("title", this.title);
      frm.append("content", this.content);
      frm.append("folderStyleNo", this.folderStyleNo);
      frm.append("folderNo", this.folderNo);
      frm.append("empName", this.empInfo.empName);
      frm.append("draft", 1);
      frm.append("currentDraft", this.electronicDratf);
      frm.append("elNo", this.electronicNo);
      if (this.emergency) {
        frm.append("emergency", 1);
      } else {
        frm.append("emergency", 0);
      }

      registerElimp(
        frm,
        (res) => {
          this.$router.push({
            name: "electronicFormDetail",
            params: {
              kind: "send",
              listTitle: "temp",
              electronicNo: res.data.electronicNo,
            },
          });

          this.$router.push({
            name: "electronicSend",
            params: { listTitle: "temp" },
          });
        },
        () => {},
      );
    },
    deepCopy(object) {
      if (object === null || typeof object !== "object") {
        return object;
      }
      const copy = Array.isArray(object) ? [] : {};
      for (let key of Object.keys(object)) {
        copy[key] = this.deepCopy(object[key]);
      }
      return copy;
    },
    select(node) {
      const no = node.id;
      this.approverNo = no;
      if (this.approver.length > 4) {
        return;
      }
      const thiss = this;
      const setDisable = function (val) {
        thiss.$set(val, "isDisabled", true);
      };
      this.receiver = this.receiver.filter((e) => e != no);
      this.roptions.forEach(function (item) {
        item.children.forEach(function (item2) {
          if (item2.id == no) {
            setDisable(item2);
          }
        });
      });
    },
    deselect(node) {
      const no = node.id;
      const thiss = this;
      const setDisable = function (val) {
        thiss.$set(val, "isDisabled", false);
      };
      this.roptions.forEach(function (item) {
        item.children.forEach(function (item2) {
          if (item2.id == no) {
            setDisable(item2);
          }
        });
      });
    },
    registerParamCheck() {
      if (this.empInfo == null || this.empInfo == "") {
        console.log("사용자 정보 없음");
        return false;
      } else if (this.title == null || this.title == "") {
        console.log("제목 없음");
        return false;
      } else if (this.content == null || this.content == "") {
        console.log("양식 없음");
        return false;
      } else if (this.approver == null || this.approver == "") {
        console.log("결재자 없음");
        return false;
      } else if (this.folderStyleNo == null || this.folderStyleNo == "") {
        console.log("양식 번호 없음");
        return false;
      } else if (this.folderNo == null || this.folderNo == "") {
        console.log("폴더 번호 없음");
        return false;
      }
      return true;
    },
    draftParamCheck() {
      if (this.empInfo == null || this.empInfo == "") {
        console.log("사용자 정보 없음");
        return false;
      } else if (this.title == null || this.title == "") {
        console.log("제목 없음");
        return false;
      } else if (this.folderStyleNo == null || this.folderStyleNo == "") {
        console.log("양식 번호 없음");
        return false;
      } else if (this.folderNo == null || this.folderNo == "") {
        console.log("폴더 번호 없음");
        return false;
      }
      return true;
    },
    fileCntCheck(files) {
      if (files.length > 5) {
        alert("최대 5개 첨부 가능합니다.");
        this.files = [];
      }
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
</style>
