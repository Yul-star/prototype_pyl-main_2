import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // ✅ 라우터 불러오기
import 'bootstrap/dist/css/bootstrap.min.css'


createApp(App)
    .use(router)                // ✅ 라우터 등록
    .mount('#app')
