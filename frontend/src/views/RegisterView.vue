<template>
  <div>
    <h2>회원가입</h2>
    <input v-model="username" placeholder="아이디" />
    <input v-model="password" type="password" placeholder="비밀번호" />
    <input v-model="email" placeholder="이메일" />
    <input v-model="name" placeholder="이름" />
    <button @click="register">가입하기</button>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";

const username = ref("");
const password = ref("");
const email = ref("");
const name = ref("");

const register = async () => {
  try {
    await axios.post("http://localhost:8001/auth/register", { //스프링서버
      username: username.value,
      password: password.value,
      email: email.value,
      name: name.value
    });
    alert("가입 성공! 로그인 해주세요");
    window.location.href = "/login";  // 로그인 페이지로 이동
  } catch (err) {
    alert("회원가입 실패 😥");
    console.error(err);
  }
};
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
