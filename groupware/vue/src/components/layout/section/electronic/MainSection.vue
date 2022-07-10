<template>
  <section>
    <div class="row">
      <div class="col-lg-6">
        <approve-article></approve-article>
        <return-article></return-article>
      </div>
      <div class="col-lg-6">
        <wait-article></wait-article>
        <plan-article></plan-article>
      </div>
    </div>
  </section>
</template>

<script>
import ApproveArticle from "@/components/layout/article/electronic/main/ApproveArticle.vue";
import ReturnArticle from "@/components/layout/article/electronic/main/ReturnArticle.vue";
import PlanArticle from "@/components/layout/article/electronic/main/PlanArticle.vue";
import WaitArticle from "@/components/layout/article/electronic/main/WaitArticle.vue";
export default {
  components: { ApproveArticle, ReturnArticle, PlanArticle, WaitArticle },
};
</script>

<style></style>
