import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import axios from "axios"
import router from "./router";
import 'element-plus/dist/index.css'
const app = createApp(App)
app.use(ElementPlus)
app.config.globalProperties.$axios = axios
app.use(router)
app.mount('#app')
