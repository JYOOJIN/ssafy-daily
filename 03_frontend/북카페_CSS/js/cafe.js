// cafe.js
//alert("loading cafe.js");

/* 로그인 함수 */
function login() {
  // 아이디 입력: 필수 입력 항목
  let userid = prompt("아이디 입력");
  // 아이디 데이터 검증
  if (userid == null || userid.trim().length == 0) {
    alert("아이디를 입력하세요");
    return;
  }

  // 비밀번호 입력: 필수 입력 항목
  let userpw = prompt("비밀번호 입력");
  if (userpw == null || userpw.trim().length == 0) {
    alert("비밀번호를 입력하세요");
    return;
  }

  // 로그인 정보 확인하기 (ssafy/1234)
  if (userid == "ssafy" && userpw == "1234") {
    // 로그인 성공처리
    alert("로그인 성공!!!");

    // 이미지 프로필 변경
    let profile_imgElement = document.getElementById("profile_img");
    profile_imgElement.src = "img/profile.png";
    //document.getElementById("profile_img").src = "img/profile.png";

    // nav 변경
    let header_nav_confirm_offElement = document.getElementById("header_nav_confirm_off");
    header_nav_confirm_offElement.style.display = "none"; // 로그인/회원가입
    document.getElementById("header_nav_confirm_on").style.display = "block"; // 로그아웃/마이페이지/관리자
  } else {
    // 로그인 실패처리
    alert("로그인 실패!!!\n아이디/비밀번호를 확인하세요.");
  }
}

function logout() {
  // 이미지 프로필 변경
  let profile_imgElement = document.getElementById("profile_img");
  profile_imgElement.src = "img/noimg.png";

  // nav 변경
  //   let header_nav_confirm_offElement = document.getElementById("header_nav_confirm_off");
  //   header_nav_confirm_offElement.style.display = "block"; // 로그인/회원가입
  //   document.getElementById("header_nav_confirm_on").style.display = "none"; // 로그아웃/마이페이지/관리자

  // setAttribute("속성명","속성값"), getAttribute("속성명"), removeAttribute("속성명")
  document.querySelector("#profile_img").setAttribute("src", "img/noimg.png");
  document.querySelector("#header_nav_confirm_off").setAttribute("style", "display:block");
  document.querySelector("#header_nav_confirm_on").setAttribute("style", "display:none");
}

/* 전국매장 펼치기&닫기 함수 */
function allSlide(onoff) {
  if (onoff == "on") {
    //전국 매장 펼치기
    let subs = document.getElementsByClassName("store_item_sub"); //배열
    for (let i = 0; i < subs.length; i++) {
      subs[i].style.display = "block";
    }
    document.getElementById("store_display_on").style.display = "none";
    document.getElementById("store_display_off").style.display = "block";
  } else {
    //전국 매장 접기
    let subs = document.getElementsByClassName("store_item_sub"); //배열
    for (let i = 0; i < subs.length; i++) {
      subs[i].style.display = "none";
    }
    document.getElementById("store_display_on").style.display = "block";
    document.getElementById("store_display_off").style.display = "none";
  }
}

function localSlide() {}
