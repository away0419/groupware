<template>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">오늘의 예약</h6>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <colgroup>
            <col style="width: 60%" />
            <col style="width: 20%" />
            <col style="width: 20%" />
          </colgroup>
          <thead class="table-info">
            <tr>
              <th scope="col">예약 내용</th>
              <th scope="col">자산명</th>
              <th scope="col">예약한 날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="3" align="center">금일 예약 내역이 없습니다.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReservationArticle",
};
</script>

<style></style>
