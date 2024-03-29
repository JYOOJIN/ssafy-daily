const likeList = document.getElementById("content-movie-like-list");
const body = document.getElementById("body");
let storedMovies = JSON.parse(localStorage.getItem("movie")) || [];

getMovieData();
initMovieLikeList();
// 이벤트 위임
body.addEventListener("click", (e) => {
  const likeButton = e.target.closest(".like-btn");
  const title = likeButton.parentElement.children[0].innerText;
  const [genreEl, producerEl, runningTimeEl] =
    likeButton.parentElement.parentElement.children[2].children;

  // 클릭한 영화가 localStorage의 목록에 존재하지 않으면
  if (!isMovieExists(storedMovies, title)) {
    const movieObj = {
      title,
      genre: genreEl.innerText,
      producer: producerEl.innerText,
      runningTime: runningTimeEl.innerText,
    };

    // 로컬 스토리지 업데이트
    storedMovies.push(movieObj);
    localStorage.setItem("movie", JSON.stringify(storedMovies));

    // 렌더링
    renderMovieList(storedMovies);
  } else {
    // 클릭한 영화가 이미 존재하면 리스트에서 제거후 로컬스토리지 업데이트
    storedMovies = storedMovies.filter((movie) => movie.title !== title);
    localStorage.setItem("movie", JSON.stringify(storedMovies));

    // 렌더링
    renderMovieList(storedMovies);
  }
});

function getMovieData() {
  const httpClient = new XMLHttpRequest();
  httpClient.open("GET", "/data/movie.json");
  httpClient.setRequestHeader("Content-Type", "application/json");
  httpClient.send();

  httpClient.onload = () => {
    if (httpClient.status !== 200) return;

    const cardContainer = document.getElementById("card-container");
    const responseData = JSON.parse(httpClient.response).movies;

    responseData.forEach((movieData) => {
      const card = createCardElement(
        movieData.title,
        movieData.genre,
        movieData.director,
        movieData.runningTime,
        movieData.img
      );
      cardContainer.append(card);
    });
  };
}

function initMovieLikeList() {
  if (!storedMovies || !storedMovies.length) {
    likeList.innerText = "아직 찜한 영화가 없습니다....";
  } else {
    renderMovieList(storedMovies);
  }
}

function renderMovieList(movies) {
  likeList.innerHTML = "";
  movies.forEach((movie) => appendMovieAsChild(likeList, movie));
}

function appendMovieAsChild(ul, movieObj) {
  const li = document.createElement("li");
  li.innerHTML = `${movieObj.title} | ${movieObj.genre} | ${movieObj.producer} | ${movieObj.runningTime}`;
  li.setAttribute("class", "list-group-item");
  ul.appendChild(li);
}

function isMovieExists(movies, title) {
  return movies.some((movie) => movie.title === title);
}

function createCardElement(title, genre, producer, runningTime, imgSrc) {
  const card = document.createElement("div");
  card.setAttribute("style", "width: 18rem");
  card.classList.add("card", "p-1", "m-2");
  card.innerHTML = `   <img class="card-img-top" src="${imgSrc}" alt="${title}" />
    <div class="card-body">
      <div class="d-flex">
        <h5 class="card-title flex-grow-1">${title}</h5>
        <button
          type="button"
          style="
            height: 38px;
            width: 38px;
            display: flex;
            align-items: center;
            justify-content: center;
          "
          class="like-btn btn btn-outline-danger"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-heart"
            viewBox="0 0 16 16"
          >
            <path
              d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"
            />
          </svg>
        </button>
      </div>
      <hr />
      <div class="card-text">
        <p class="m-0">${genre}</p>
        <p class="m-0">${producer}</p>
        <p class="m-0">${runningTime}분</p>
      </div>`;

  return card;
}
