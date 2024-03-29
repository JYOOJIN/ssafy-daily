let likeBtnElements = document.getElementsByClassName("like-btn");

// window loalcStoratge 전체 값 삭제
//window.localStorage.clear();

for (let i = 0; i < likeBtnElements.length; i++) {
  likeBtnElements[i].addEventListener("click", function () {
    //consolePrintInfo(i);
    printInfo(i);
  });
}

// 화면에 영화 정보 출력하는 함수
function printInfo(index) {
  saveInfo(index);
  getInfo(index);
}

var xhr = new XMLHttpRequest();
xhr.open("GET", "./data/movie.json", true);
xhr.onreadystatechange = function () {
  if (xhr.readyState == 4 && xhr.status == 200) {
    var movieData = JSON.parse(xhr.responseText);
    console.log(movieData);
  }
};
xhr.send();

//localStorage에 저장한 movieinfo를 불러와서 화면에 출력하는 함수
function getInfo(index) {
  //영화 정보를 출력할 element
  let contentmovielikeElement = document.querySelector(".content-movie-like div");

  //localStorage에 저장한 movies 가져오기 -> JSON 형태
  let movieinfoString = window.localStorage.getItem("movies");
  //JSON 파싱
  let movieinfoParsed = JSON.parse(movieinfoString);
  //영화정보 string 생성 및 값 넣기
  let info =
    movieinfoParsed.name +
    " | " +
    movieinfoParsed.genre +
    " | " +
    movieinfoParsed.director +
    " | " +
    movieinfoParsed.runningtime +
    "<br>";

  if (contentmovielikeElement.innerHTML == "아직 찜한 영화가 없습니다....") {
    //찜한 영화가 없었다면
    //영화 정보 출력
    contentmovielikeElement.innerHTML = info;
  } else {
    //찜한 영화가 있었다면
    if (contentmovielikeElement.innerHTML.includes(info)) {
      //찜했던 기록이 있다면 중복해서 출력하지 않는다
    } else {
      //찜했던 기록이 없다면 출력
      contentmovielikeElement.innerHTML += info;
    }
  }
}

//localStorage에 movieinfo 저장하는 함수
function saveInfo(index) {
  let movieinfoElements = document.getElementsByClassName("movie-info");
  let infoList = movieinfoElements[index].querySelector("div:nth-child(1)");

  let moviename = infoList.querySelector("div:nth-child(1)").textContent;
  let moviegenre = infoList.querySelector("div:nth-child(3)").textContent;
  let moviedir = infoList.querySelector("div:nth-child(4)").textContent;
  let movietime = infoList.querySelector("div:nth-child(5)").textContent;

  let movie = {
    name: moviename,
    genre: moviegenre,
    director: moviedir,
    runningtime: movietime,
  };

  let movieString = JSON.stringify(movie);
  window.localStorage.setItem("movies", movieString);
}

//콘솔에 영화정보 출력하는 함수
function consolePrintInfo(index) {
  let movieinfoElements = document.getElementsByClassName("movie-info");
  let infoList = movieinfoElements[index].querySelector("div:nth-child(1)");

  let movieInfo = "";
  movieInfo += infoList.querySelector("div:nth-child(1)").textContent + "\n";
  movieInfo += infoList.querySelector("div:nth-child(3)").textContent + "\n";
  movieInfo += infoList.querySelector("div:nth-child(4)").textContent + "\n";
  movieInfo += infoList.querySelector("div:nth-child(5)").textContent + "\n";
  console.log(movieInfo);
}
