<template>
  <div id="app">


    <!-- 페이지별 내용 들어가는 자리 -->
    <router-view />

    <footer class="footer">
      <p>© 2025 My Portal</p>
    </footer>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const isLoggedIn = ref(false)  // 처음엔 false
const router = useRouter()

// 페이지 로드될 때 로그인 상태 확인 (세션 기반이면 더 정교하게)
onMounted(() => {
  const user = sessionStorage.getItem('username')
  if (user) {
    isLoggedIn.value = true
  }
})

const logout = async () => {
  try {
    await axios.get('http://localhost:5173/auth/logout')
    alert('로그아웃 되었습니다.')
    isLoggedIn.value = false
    sessionStorage.removeItem('username')
    router.push('/login')
  } catch (err) {
    console.error('로그아웃 실패:', err)
    alert('서버 오류!')
  }
}

const goLogin = () => {
  router.push('/login')
}
</script>

<style scoped>
#app {
  font-family: 'Noto Sans KR', sans-serif;
  text-align: center;
  background-color: #f4f6f8;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background-color: #3498db;
  color: white;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header button {
  background-color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 6px;
  cursor: pointer;
  font-weight: bold;
  color: #3498db;
}

.header button:hover {
  background-color: #ecf0f1;
}

.footer {
  margin-top: auto;
  background-color: #ecf0f1;
  padding: 10px;
  font-size: 14px;
  color: #7f8c8d;
}
</style>
