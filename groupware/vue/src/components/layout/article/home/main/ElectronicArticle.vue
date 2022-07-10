<template>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">최근 받은 기안서</h6>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <colgroup>
            <col style="width: 60%" />
            <col style="width: 40%" />
          </colgroup>
          <thead class="table-info">
            <tr>
              <th scope="col">제목</th>
              <th scope="col">기안자</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td class="name" colspan="2">최근 받은 기안서가 없습니다</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ElectronicAritcle",
};
</script>

<style></style>
