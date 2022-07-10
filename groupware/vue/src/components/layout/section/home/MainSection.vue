<template>
  <section>
    <div class="row">
      <div class="col-lg-6">
        <notice-article></notice-article>
        <mail-article></mail-article>
        <electronic-article></electronic-article>
      </div>
      <div class="col-lg-6">
        <reservation-article></reservation-article>
        <schedule-article></schedule-article>
        <weather-article></weather-article>
      </div>
    </div>
  </section>
</template>

<script>
import ElectronicArticle from "@/components/layout/article/home/main/ElectronicArticle.vue";
import MailArticle from "@/components/layout/article/home/main/MailArticle.vue";
import NoticeArticle from "@/components/layout/article/home/main/NoticeArticle.vue";
import ScheduleArticle from "@/components/layout/article/home/main/ScheduleArticle.vue";
import ReservationArticle from "@/components/layout/article/home/main/ReservationArticle.vue";
import WeatherArticle from "@/components/layout/article/home/main/WeatherArticle.vue";

export default {
  name: "MainSection",
  components: {
    ElectronicArticle,
    MailArticle,
    ScheduleArticle,
    WeatherArticle,
    NoticeArticle,
    ReservationArticle,
  },
};
</script>
<style scoped></style>
