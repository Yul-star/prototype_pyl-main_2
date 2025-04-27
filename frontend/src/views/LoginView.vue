<template>
  <div class="login-container">
    <div class="login-box">
      <h2>로그인</h2>
      <form @submit.prevent="handleLogin">
        <div class="input-group">
          <label for="username">아이디</label>
          <input id="username" name="username" v-model="username" type="text" required />
        </div>
        <div class="input-group">
          <label for="password">비밀번호</label>
          <input id="password" name="password" v-model="password" type="password" required />
        </div>
        <button type="submit">로그인</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'  // 스토어 경로에 맞게 수정해줘


const username = ref('')
const password = ref('')
const router = useRouter()
const userStore = useUserStore()


const handleLogin = async () => {
  try {
    const res = await axios.post('http://localhost:8001/api/auth/login', { //스프링서버 불러오기
      username: username.value,
      password: password.value
    })


    alert('로그인 성공!') // 로그인 성공


    const { token, username: resUsername } = res.data
    userStore.login(token, resUsername)
    router.push('/')
  } catch (err) {
    alert('로그인 실패!')
  }
}



</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f0f4f8;
}

.login-box {
  background: white;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
  width: 320px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #2c3e50;
}

.input-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 6px;
  color: #34495e;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  cursor: pointer;
}

button:hover {
  background-color: #2980b9;
}
</style>
