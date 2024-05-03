<script setup>
import { ref } from "vue"

const article = ref({
  articleNo: 100,
  subject: "안녕하세요 뷰테스트 중입니다.",
  content:
    "뷰테스트 중입니다. 컴포넌트 연습하고 있으며, 앞으로 props와 custom event를 처리 할 예정입니다.",
  userId: "ssafy",
  userName: "김싸피",
  hit: 123,
  registerDate: "25.01.01",
})
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글수정</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <form>
          <div class="mb-3">
            <label for="userid" class="form-label">작성자 ID : </label>
            <input type="text" class="form-control" v-model="article.userId" readonly />
          </div>
          <div class="mb-3">
            <label for="subject" class="form-label">제목 : </label>
            <input type="text" class="form-control" v-model="article.subject" />
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">내용 : </label>
            <textarea class="form-control" v-model="article.content" rows="7"></textarea>
          </div>
          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-success mb-3">글수정</button>
            <button type="button" class="btn btn-outline-danger mb-3 ms-1">목록으로이동...</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
