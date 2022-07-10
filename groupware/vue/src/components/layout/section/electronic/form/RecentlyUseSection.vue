<template>
  <one-article-section>
    <use-article></use-article>
  </one-article-section>
</template>

<script>
import UseArticle from "@/components/layout/article/electronic/form/UseArticle.vue";
import OneArticleSection from "@/components/layout/section/common/OneArticleSection.vue";
export default {
  components: { UseArticle, OneArticleSection },
  name: "RecentlyUseSection",
};
</script>

<style scoped>
.row {
  margin: 24px;
  flex-grow: 1;
}
</style>
