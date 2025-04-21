<template>
  <div class="register-container">
    <h2>회원가입</h2>
    <form @submit.prevent="handleRegister">
      <input type="text" placeholder="아이디" v-model="username" required />
      <input type="password" placeholder="비밀번호" v-model="password" required />
      <input type="email" placeholder="이메일" v-model="email" required />
      <input type="text" placeholder="이름" v-model="name" required />
      <button type="submit">가입하기</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const username = ref('')
const password = ref('')
const email = ref('')
const name = ref('')

const handleRegister = () => {
  console.log('회원가입 정보:', {
    username: username.value,
    password: password.value,
    email: email.value,
    name: name.value
  })

  // 여기에 실제 회원가입 API 연동할 예정!
}
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 3rem auto;
  padding: 2rem;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  font-family: 'Segoe UI', sans-serif;
}
h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}
input {
  display: block;
  width: 100%;
  padding: 0.75rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 6px;
}
button {
  width: 100%;
  padding: 0.75rem;
  background-color: #2d8cf0;
  color: #fff;
  font-weight: bold;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.3s ease;
}
button:hover {
  background-color: #1c6dc1;
}
</style>
