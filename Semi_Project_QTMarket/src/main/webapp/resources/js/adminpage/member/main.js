"use strict";

let options = {
  valueNames: ["no", "id", "name", "phone", "email", "enroll", "city", "district", "role"],
};

let userList = new List("list", options);

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

$(document).ready(() => {
	$(".deleteBtn").on("click", (event) => {
		const id = event.target.dataset.memberid;
		if (confirm("정말로 추방 하시겠습니까?")) {
			location.replace("member/delete?id=" + id);
		};
	});
});	