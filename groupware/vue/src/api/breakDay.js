import { apiInstance } from ".";

const api = apiInstance();

async function getThemeList(succes, fail) {
  await api.get("/breakDay/themeList").then(succes).catch(fail);
}

export { getThemeList };
