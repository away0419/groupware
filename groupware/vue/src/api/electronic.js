import { apiInstance } from ".";

const api = apiInstance();

async function sendList(params, succes, fail) {
  await api
    .get("/electronic/sendList", { params: params })
    .then(succes)
    .catch(fail);
}

async function receiveList(params, succes, fail) {
  await api
    .get("/electronic/receiveList", { params: params })
    .then(succes)
    .catch(fail);
}

async function readList(params, succes, fail) {
  await api
    .get("/electronic/readList", { params: params })
    .then(succes)
    .catch(fail);
}

async function recentlyList(params, succes, fail) {
  await api
    .get("/electronic/recentlyList", { params: params })
    .then(succes)
    .catch(fail);
}

function registerSign(frm, succes, fail) {
  api
    .post("/electronic/registerSign", frm, {
      headers: { "Content-Type": "multipart/form-data" },
    })
    .then(succes)
    .catch(fail);
}

async function selectSign(params, succes, fail) {
  await api
    .get("/electronic/selectSign", { params: params })
    .then(succes)
    .catch(fail);
}

function selectFormTree(succes, fail) {
  api.get("/electronic/selectFormTree").then(succes).catch(fail);
}

function registerElimp(frm, succes, fail) {
  api
    .post("/electronic/registerElimp", frm, {
      headers: { "Content-Type": "multipart/form-data" },
    })
    .then(succes)
    .catch(fail);
}

function detailElimp(param, succes, fail) {
  api.get("/electronic/detail", { params: param }).then(succes).catch(fail);
}
async function selectElSty(param, succes, fail) {
  await api
    .get("/electronic/selectElSty", { params: param })
    .then(succes)
    .catch(fail);
}
async function selectDraft(param, succes, fail) {
  await api
    .get("/electronic/draft", { params: param })
    .then(succes)
    .catch(fail);
}

async function selectApprover(param, succes, fail) {
  await api
    .get("/electronic/approver", { params: param })
    .then(succes)
    .catch(fail);
}

function download(param, succes, fail) {
  api
    .get("/electronic/download", { params: param, responseType: "blob" })
    .then(succes)
    .catch(fail);
}
function elApprove(data, succes, fail) {
  api.put("/electronic/approve", data).then(succes).catch(fail);
}
function elDenied(data, succes, fail) {
  api.put("/electronic/denied", data).then(succes).catch(fail);
}

export {
  sendList,
  receiveList,
  recentlyList,
  registerSign,
  selectSign,
  selectFormTree,
  registerElimp,
  readList,
  detailElimp,
  selectElSty,
  selectDraft,
  selectApprover,
  download,
  elApprove,
  elDenied,
};
