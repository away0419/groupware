import axios from "axios";
import { GROUPWARE_BASE_URL } from "@/config";

function apiInstance() {
  const instance = axios.create({
    baseURL: GROUPWARE_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance };
