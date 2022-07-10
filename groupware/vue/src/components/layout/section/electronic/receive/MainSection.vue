<template>
  <one-article-section>
    <list-article :listTitle="listTitle"></list-article>
  </one-article-section>
</template>

<script>
import OneArticleSection from "@/components/layout/section/common/OneArticleSection.vue";
import ListArticle from "@/components/layout/article/electronic/receive/ListArticle.vue";
export default {
  components: { OneArticleSection, ListArticle },
  name: "MainSection",
  props: {
    listTitle: String,
  },
};
</script>

<style scoped>
.row {
  margin: 24px;
  flex-grow: 1;
}
</style>
