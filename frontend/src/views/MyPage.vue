<template>
  <div class="container mt-5">
    <h2 class="mb-4">마이페이지</h2>
    <p>환영합니다, {{ userStore.username }}님!</p>
  </div>
</template>

<script setup>
import { userStore } from '../stores/user'
</script>
