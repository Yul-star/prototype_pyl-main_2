// main.js
import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'  // 📦 Pinia 가져오기
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'bootstrap'
import axios from 'axios'

const app = createApp(App)
app.use(createPinia())  // ✅ Pinia 연결
app.use(router)
app.mount('#app')


axios.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})