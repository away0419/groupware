<template>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">안읽은 메일함</h6>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <colgroup>
            <col style="width: 20%" />
            <col style="width: 50%" />
            <col style="width: 30%" />
          </colgroup>
          <thead class="table-info">
            <tr>
              <th>보낸 사람</th>
              <th>제목</th>
              <th>보낸 날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="3" style="text-align: center">모두 읽으셨습니다.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MailArticle",
};
</script>

<style></style>
