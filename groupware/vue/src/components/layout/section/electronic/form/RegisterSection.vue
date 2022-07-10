<template>
  <one-article-section>
    <register-article
      :folderNo="folderNo"
      :folderStyleNo="folderStyleNo"
      :electronicNo="electronicNo"
      :electronicDratf="electronicDratf"
    ></register-article>
  </one-article-section>
</template>

<script>
import OneArticleSection from "@/components/layout/section/common/OneArticleSection.vue";
import RegisterArticle from "@/components/layout/article/electronic/form/RegisterArticle.vue";
export default {
  components: { OneArticleSection, RegisterArticle },
  name: "RegisterSection",
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
};
</script>

<style scoped>
.row {
  margin: 24px;
  flex-grow: 1;
}
</style>
