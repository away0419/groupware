import { apiInstance } from ".";

const api = apiInstance();

async function login(emp, succes, fail) {
  await api.post("/emp/login", JSON.stringify(emp)).then(succes).catch(fail);
}

async function findPwd(emp, succes, fail) {
  await api.put("/emp/findPwd", JSON.stringify(emp)).then(succes).catch(fail);
}

function selectEmpTree(param, succes, fail) {
  api.get("/emp/selectEmpTree", { params: param }).then(succes).catch(fail);
}

export { login, findPwd, selectEmpTree };
