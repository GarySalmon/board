import axios from "axios"
// import { useToast } from "vue-toastification"

// const toast = useToast()
const requsest = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 5000,
})

requsest.interceptors.response.use(
  (response) => {
    if (response.data.token) {
      console.log("Get token")
      localStorage.setItem("token", response.data.token)
    }
    return response
  },
  (error) => {
    // toast.error(error.meessage)
    return Promise.reject(error)
  }
)

requsest.interceptors.request.use((config) => {
  const token = localStorage.getItem("token")
  if (token) {
    config.headers.Authorization = "Bearer " + token
  }
  return config
})

export default requsest
