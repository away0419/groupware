<template>
  <div class="room-file-upload-wrapper">
    <div v-if="filePreview == ''" class="room-file-upload-example-container">
      <div class="room-file-upload-example">
        <div class="room-file-image-example-wrapper">이미지</div>

        <div class="room-file-notice-item room-file-upload-button">
          <div class="image-box">
            <label for="file">이미지 등록</label>
            <input
              type="file"
              id="file"
              ref="files"
              accept="image/*"
              @change="imageUpload"
            />
          </div>
        </div>
      </div>
    </div>
    <div v-else class="file-preview-content-container">
      <div class="file-preview-container">
        <div class="file-preview-wrapper">
          <div class="file-close-button" @click="fileDeleteButton">x</div>
          <img :src="filePreview" />
        </div>
      </div>
      <div v-if="isSign" class="image-box">
        <label for="file">이미지 수정</label>
        <input
          type="file"
          id="file"
          ref="files"
          accept="image/*"
          @change="imageUpload"
        />
      </div>
      <b-button
        v-else
        type="button"
        class="file-register-button"
        @click="registSignClick"
        >등록</b-button
      >
    </div>
  </div>
</template>

<script>
import { registerSign, selectSign } from "@/api/electronic";
import { mapState } from "vuex";
const empStore = "empStore";

export default {
  name: "SignRegisterDiv",
  data() {
    return {
      file: "",
      filePreview: "",
      uploadImageIndex: 0,
      isSign: false,
    };
  },
  computed: {
    ...mapState(empStore, ["empInfo"]),
  },
  created() {
    selectSign(
      { empNo: this.empInfo.empNo },
      (res) => {
        if (res.data.result == "success") {
          this.filePreview = "data:image/jpeg;base64," + res.data.image;
          this.isSign = true;
        }
      },
      () => {},
    );
  },
  methods: {
    imageUpload() {
      this.file = this.$refs.files.files[0];
      this.filePreview = URL.createObjectURL(this.$refs.files.files[0]);
      this.uploadImageIndex = 1;
      this.isSign = false;
    },
    fileDeleteButton() {
      this.file = "";
      this.filePreview = "";
    },
    registSignClick() {
      const frm = new FormData();
      frm.append("sign", this.file);
      frm.append("empNo", this.empInfo.empNo);
      registerSign(
        frm,
        () => {
          // console.log("성공");
          this.$bvModal.hide("signRegisterModal");
          alert("등록 완료");
        },
        () => {
          // console.log("실패");
        },
      );
    },
  },
};
</script>

<style scoped>
.room-file-upload-example {
  height: 100%;
}

.file-preview-content-container {
  height: 100%;
}

.room-file-upload-wrapper {
  margin: 20px;
  border: 1px solid #dddddd;
  background-color: #f4f4f4;
  min-height: 350px;
  font-size: 15px;
  color: #888888;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.room-file-upload-example-container {
  display: flex;
  align-items: center;
  justify-content: center;
  /* height: 100%;
  width: 100%; */
}

.room-file-image-example-wrapper {
  text-align: center;
}

.room-file-notice-item {
  margin-top: 5px;
  text-align: center;
}

.room-file-notice-item-red {
  color: #ef4351;
}

.image-box {
  margin-top: 30px;
  padding-bottom: 20px;
  text-align: center;
}

.image-box input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 10px 20px;
  background-color: #232d4a;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper > img {
  position: relative;
  width: 190px;
  height: 130px;
  z-index: 10;
}

.file-close-button {
  position: absolute;
  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-register-button {
  margin: auto;
  display: flex;
}
</style>
