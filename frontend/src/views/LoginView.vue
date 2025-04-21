<template>
  <div class="login-container">
    <div class="login-box">
      <h2>로그인</h2>
      <form @submit.prevent="handleLogin">
        <div class="input-group">
          <label>아이디</label>
          <input v-model="username" type="text" required />
        </div>
        <div class="input-group">
          <label>비밀번호</label>
          <input v-model="password" type="password" required />
        </div>
        <button type="submit">로그인</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { userStore } from '../stores/user'

const username = ref('')
const password = ref('')
const doLogin = async () => {
  // 서버에서 로그인 성공 시

const handleLogin = () => {
  console.log('로그인 시도:', username.value, password.value)
  // TODO: 서버로 로그인 요청 보낼 예정
}

  const username = 'tester123' // 예: 백엔드 응답에서 가져온 아이디
  userStore.login(username)
  router.push('/')
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
