import { apiInstance } from ".";

const api = apiInstance();

async function login(emp, succes, fail) {
  await api.post("/emp/login", JSON.stringify(emp)).then(succes).catch(fail);
}

export { login };
