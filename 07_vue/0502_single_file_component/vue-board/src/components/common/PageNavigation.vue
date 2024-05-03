<script setup>
import { ref } from "vue"

const startPage = ref(11)
const endPage = ref(20)
const currentPage = ref(17)

function range(start, end) {
  const list = []
  for (let i = start; i <= end; i++) list.push(i)
  return list
  //   return Array(end - start + 1)
  //     .fill()
  //     .map((val, i) => start + i);
}
</script>

<template>
  <div class="row">
    <ul class="pagination justify-content-center">
      <li class="page-item">
        <a class="page-link" href="#">이전</a>
      </li>
      <template v-for="pg in range(startPage, endPage)" :key="pg">
        <li class="page-item" :class="currentPage === pg ? 'active' : ''">
          <a class="page-link" href="#">{{ pg }}</a>
        </li>
      </template>
      <li class="page-item"><a class="page-link" href="#">다음</a></li>
    </ul>
  </div>
</template>

<style scoped></style>
