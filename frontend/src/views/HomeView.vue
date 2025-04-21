<script setup>
import { userStore } from '../stores/user'
import { useRouter } from 'vue-router'

const router = useRouter()

const goToMyPage = () => {
  if (userStore.isLoggedIn) {
    router.push('/mypage')
  } else {
    alert('로그인이 필요합니다!')
  }
}
</script>

<template>
  <div class="home">
    <!-- 네비게이션 -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
      <div class="container">
        <a class="navbar-brand fw-bold text-primary" href="#">MyService</a>

        <div class="d-flex align-items-center">
          <template v-if="userStore.isLoggedIn">
            <span class="me-3 text-muted">👤 {{ userStore.username }} 님</span>
            <button class="btn btn-outline-danger me-2" @click="userStore.logout()">로그아웃</button>
            <button class="btn btn-outline-secondary" @click="goToMyPage">마이페이지</button>
          </template>

          <template v-else>
            <router-link to="/login" class="btn btn-outline-primary me-2">로그인</router-link>
            <router-link to="/register" class="btn btn-primary">회원가입</router-link>
          </template>
        </div>
      </div>
    </nav>

    <!-- 메인 배너 -->
    <section class="hero d-flex align-items-center text-center text-white">
      <div class="container">
        <h1 class="display-4 fw-bold">새로운 경험, 함께 체험해보세요!</h1>
        <p class="lead mt-3 mb-4">MyService 체험단에 참여하고 특별한 혜택을 누리세요.</p>
        <router-link to="/register" class="btn btn-lg btn-light text-primary fw-bold">지금 바로 체험단 신청하기</router-link>
      </div>
    </section>

    <!-- 서비스 소개 -->
    <section class="features py-5 bg-light">
      <div class="container">
        <div class="row text-center">
          <div class="col-md-4">
            <i class="bi bi-shield-check fs-1 text-primary mb-3"></i>
            <h4 class="fw-bold">안심하고 참여하세요</h4>
            <p>개인정보는 안전하게 보호되며, 믿을 수 있는 서비스만 제공합니다.</p>
          </div>
          <div class="col-md-4">
            <i class="bi bi-lightning-charge fs-1 text-primary mb-3"></i>
            <h4 class="fw-bold">간편한 신청 절차</h4>
            <p>몇 번의 클릭만으로 쉽게 체험단에 참여할 수 있습니다.</p>
          </div>
          <div class="col-md-4">
            <i class="bi bi-heart fs-1 text-primary mb-3"></i>
            <h4 class="fw-bold">특별한 혜택과 보상</h4>
            <p>참여자 전원에게 다양한 혜택과 소정의 보상을 드립니다.</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 푸터 -->
    <footer class="text-center text-muted py-4 bg-white">
      &copy; 2025 MyService. All rights reserved.
    </footer>
  </div>
</template>

<style scoped>
.hero {
  background: linear-gradient(120deg, #3f87a6, #ebf8e1);
  min-height: 60vh;
}
</style>