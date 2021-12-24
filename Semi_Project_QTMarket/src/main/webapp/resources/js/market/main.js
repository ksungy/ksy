"use strict";

const frame = "section";
const box = "article";
const speed = "0.5s";
const activeClass = "on";
const btns = document.querySelectorAll("main ul li");
const navbar = document.querySelector("#navbar");
const navbarHeight = navbar.getBoundingClientRect().height;
let grid;

let options = {
  valueNames: ["title", "area", "content", "writer"],
};

let userList = new List("users", options);

const searchBox = document.querySelector(".search-box");
const searchBtn = document.querySelector(".search-icon");
const cancelBtn = document.querySelector(".cancel-icon");
const searchInput = document.querySelector("input");
searchBtn.onclick = () => {
  searchBox.classList.add("active");
  searchBtn.classList.add("active");
  searchInput.classList.add("active");
  cancelBtn.classList.add("active");
  searchInput.focus();
};

cancelBtn.onclick = () => {
  searchBox.classList.remove("active");
  searchBtn.classList.remove("active");
  searchInput.classList.remove("active");
  cancelBtn.classList.remove("active");
  searchInput.value = "";
};


window.addEventListener("load", () => {
  init();
  filter(btns);
});

function init() {
  grid = new Isotope("section", {
    itemSelector: "article",
    columnWidth: "article",
    transitionDuration: "0.5s",
  });
}

function filter(arr) {
  for (let el of arr) {
    el.addEventListener("click", (e) => {
      e.preventDefault();

      const sort = e.currentTarget.querySelector("a").getAttribute("href");

      grid.arrange({
        filter: sort,
      });

      for (let el of arr) {
        el.classList.remove(activeClass);
      }

      e.currentTarget.classList.add(activeClass);
    });
  }
}

// Make navbar transparent when it is on the top
document.addEventListener("scroll", () => {
  if (window.scrollY > navbarHeight) {
    navbar.classList.add("navbar--dark");
  } else {
    navbar.classList.remove("navbar--dark");
  }
});

// Make wish by Heart
function heartEmpty() {
  const heartEmpty = document.querySelector(".wish__btn__0");

  heartEmpty.addEventListener("click", () => {
    heartEmpty.setAttribute("class", "wish__btn__1");
    heartEmpty.setAttribute("onclick", "heartFull()");
    heartEmpty.innerHTML = "<i class='fas fa-heart'>";
  });
}

function heartFull() {
  const heartFull = document.querySelector(".wish__btn__1");

  heartFull.addEventListener("click", () => {
    heartFull.setAttribute("class", "wish__btn__0");
    heartFull.setAttribute("onclick", "heartEmpty()");
    heartFull.innerHTML = "<i class='far fa-heart'>";
  });
}

// 위는 div에 속한 단일 객체로서의 heart를 바꿔주는 스크립트로서 다중의 heart를 각각의 객체로 분리시켜줘야 함. 위의 필터랑 배열을 이용하면 될 듯.
