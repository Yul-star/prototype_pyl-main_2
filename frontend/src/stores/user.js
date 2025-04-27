
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => ({
        username: localStorage.getItem('username') || '',
        token: localStorage.getItem('token') || ''
    }),
    getters: {
        isLoggedIn: (state) => !!state.token
    },
    actions: {
        login(token, username) {
            this.token = token
            this.username = username
            localStorage.setItem('token', token)
            localStorage.setItem('username', username)
            console.log('Logged in:', { username: this.username, token: this.token }) //콘솔 ㅍ출력
        },
        logout() {
            this.token = null
            this.username = null
            this.role = null
            this.email = null
            localStorage.removeItem('token')
            localStorage.removeItem('username')
            localStorage.removeItem('role')
            localStorage.removeItem('email')
        }
    }
})
