import { createApp } from "vue"
import App from "./App.vue"
import router from "./router"
import "./assets/tailwind.css"
import Vue3Toasity from "vue3-toastify"
import "vue3-toastify/dist/index.css"
const app = createApp(App).use(router).use(Vue3Toasity)

app.mount("#app")
