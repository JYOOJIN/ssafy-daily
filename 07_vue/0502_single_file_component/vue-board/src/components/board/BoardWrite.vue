<script setup>
import { ref } from "vue"

const article = ref({
  articleNo: 0,
  subject: "",
  content: "",
  userId: "",
  userName: "",
  hit: 0,
  registerDate: "",
})
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글쓰기</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <form>
          <div class="mb-3">
            <label for="userid" class="form-label">작성자 ID : </label>
            <input
              type="text"
              class="form-control"
              v-model="article.userId"
              placeholder="작성자ID..."
            />
          </div>
          <div class="mb-3">
            <label for="subject" class="form-label">제목 : </label>
            <input
              type="text"
              class="form-control"
              v-model="article.subject"
              placeholder="제목..."
            />
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">내용 : </label>
            <textarea class="form-control" v-model="article.content" rows="7"></textarea>
          </div>
          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-primary mb-3">글작성</button>
            <button type="button" class="btn btn-outline-danger mb-3 ms-1">목록으로이동...</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
