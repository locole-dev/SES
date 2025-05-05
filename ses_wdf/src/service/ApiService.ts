import axios, { AxiosResponse } from "axios";
import { toast } from "react-toastify";
import { API_URL } from "../util/Constants";

interface ApiServiceParams {
  url: string;
  method?: "GET" | "POST" | "PUT" | "DELETE";
  data?: any;
  headers?: Record<string, string>;
  onError?: (error: any) => void | null;
}

const apiCache = new Map<string, Promise<any>>();

const apiService = async ({ url, method = "GET", data = null, headers = {}, onError = undefined }: ApiServiceParams): Promise<any> => {
  const apiUrl = API_URL + url;
  // Kiểm tra nếu API này đang được gọi -> Trả về Promise cũ (chặn gọi 2 lần)
  if (apiCache.has(apiUrl)) {
    return apiCache.get(apiUrl);
  }

  if (sessionStorage.getItem("wdf-token") != null) {
    headers = { "Authorization": `Bearer ${sessionStorage.getItem("wdf-token")}`, ...headers }
  }
  const response = axios({
    url: apiUrl,
    method,
    data,
    headers: { "Content-Type": "application/json", ...headers },
  }).then((response: AxiosResponse) => {
    // console.log(response.data);
    apiCache.delete(apiUrl); // Xóa khỏi cache sau khi hoàn thành
    return response.data;
  }).catch((error) => {
    apiCache.delete(apiUrl); // Xóa khỏi cache nếu lỗi
    console.error("API Error:", error);
    if (onError && typeof onError === "function") {
      onError(error);
    } else {
      toast.error(`${error ?? "Có lỗi xảy ra!"}`);
    }
    return null;
  });

  apiCache.set(apiUrl, response); // Lưu request vào cache
  return response;
};

export default apiService;