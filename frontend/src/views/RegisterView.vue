<template>
  <div>
    <h2>회원가입</h2>
    <input v-model="username" placeholder="아이디" />
    <input v-model="password" placeholder="비밀번호" type="password" />
    <input v-model="email" placeholder="이메일" />
    <button @click="register">회원가입</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const email = ref('')
const router = useRouter()

const register = async () => {
  try {
    await axios.post('http://localhost:8001/api/auth/register', {
      username: username.value,
      password: password.value,
      email: email.value
    })
    alert('회원가입 성공! 로그인 해주세요.')
    router.push('/login')
  } catch (error) {
    alert('회원가입 실패!')
  }
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
