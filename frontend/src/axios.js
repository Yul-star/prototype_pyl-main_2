
import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8001/api',
    headers: {
        'Content-Type': 'application/json'
    }
})

// 요청 보낼 때마다 JWT 토큰 붙이기
instance.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
}, error => {
    return Promise.reject(error)
})

export default instance
