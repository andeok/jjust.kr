import Axios from "axios"

export const API = Axios.create({
  baseURL: import.meta.env.VITE_SERVER_HOST,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
  timeout: 10000, // Set a timeout of 10 seconds
})

API.interceptors.request.use(
    (config) => {
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
)

API.interceptors.response.use(
    function (response) {
      // 응답 데이터가 있는 작업 수행
      return response
    },
    function (error) {
      // 응답 오류가 있는 작업 수행
      return Promise.reject(error)
    },
)