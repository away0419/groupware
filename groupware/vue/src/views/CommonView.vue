<template>
  <div id="common-div">
    <header-navi-bar></header-navi-bar>
    <router-view id="body-content"></router-view>
    <footer-bar id="footer-content"></footer-bar>
    <organization-div></organization-div>
    <organization-modal></organization-modal>
  </div>
</template>

<script>
import HeaderNaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
import OrganizationDiv from "@/components/etc/OrganizationDiv.vue";
import OrganizationModal from "@/components/modal/OrganizationModal.vue";

export default {
  name: "CommonView",
  components: {
    HeaderNaviBar,
    FooterBar,
    OrganizationDiv,
    OrganizationModal,
  },
};
</script>

<style scoped>
@charset "UTF-8";

::v-deep .icon-bar {
  width: 220px;
  border-right: 1px solid rgba(0, 0, 0, 0.125);
  flex-shrink: 0;
}
@media (max-width: 991px) {
  ::v-deep .icon-bar {
    display: none;
  }
}
::v-deep section {
  padding: 25px;
  flex-grow: 1;
}
#body-content {
  display: flex;
  flex-direction: row !important;
  flex-basis: 90%;
}
#common-div {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: white;
}
#footer-content {
  border-top: 1px solid rgba(0, 0, 0, 0.125);
  align-content: center;
  font-family: "Times New Roman", Times, serif;
}
</style>
