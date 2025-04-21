
import { reactive } from 'vue'

export const userStore = reactive({
    isLoggedIn: false,
    username: '',
    login(name) {
        this.isLoggedIn = true
        this.username = name
    },
    logout() {
        this.isLoggedIn = false
        this.username = ''
    }
})
