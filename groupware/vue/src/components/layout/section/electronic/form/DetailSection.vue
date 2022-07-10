<template>
  <one-article-section>
    <detail-article
      :electronicNo="electronicNo"
      :listTitle="listTitle"
      :kind="kind"
    ></detail-article>
  </one-article-section>
</template>

<script>
import OneArticleSection from "@/components/layout/section/common/OneArticleSection.vue";
import DetailArticle from "@/components/layout/article/electronic/form/DetailArticle.vue";
export default {
  components: { OneArticleSection, DetailArticle },
  name: "DetailSection",
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
};
</script>

<style scoped>
.row {
  margin: 24px;
  flex-grow: 1;
}
</style>
