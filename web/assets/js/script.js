// Active Menu
$(function () {
  let requestUrl = location.pathname.split("/")[2];
  switch (requestUrl) {
    case "AdminUser":
    case "addUser":
    case "modifyUser":
    case "deleteUser":
    case "updateUser":
      $('li a[href^="AdminUser"]').parent().addClass("active");
      break;
    case "AdminBook":
    case "saveBook":
    case "modifyBook":
    case "deleteBook":
    case "updateBook":
      $('li a[href^="AdminBook"]').parent().addClass("active");
      break;
    case "AdminIssueBook":
      $('li a[href^="AdminIssueBook"]').parent().addClass("active");
      break;
    default:
      $('li a[href^="AdminDashboard"]').parent().addClass("active");
      break;
  }
});

// upload image
const loadImage = function (event, imageTagIdName) {
  let image = document.getElementById(imageTagIdName);
  image.src = URL.createObjectURL(event.target.files[0]);
};

// Notification
const showToast = function (msg, alertType, icon) {
  let msgContainer = document.getElementById("notification");
  msgContainer.innerHTML = `<div class="toast-body ${alertType}"><i class="fa ${icon}"></i> ${msg}</div>`;
  $("#notification").toast("show");
};

// show model
const showModel = function (id) {
  $(id).modal("show");
};

// reset form
const reset = function (formId) {
  $(formId).reset();
};

// Book Search
var searchBtn = $("#search-btn");
var bookCard = $(".book-card");
searchBtn.on("click", function () {
  let filterValue = $("#filter-txt").val().toLowerCase();
  if (filterValue == "") {
    $("#search-msg").html("");
    bookCard.show();
    return;
  }
  bookCard.hide();
  let foundBook = $("." + filterValue);
  foundBook.show();
  $("#search-msg").html(foundBook.length + " Book Found");
});

// cart
var carts = [];

function addTocart(bookId, event) {
  if (event.target.innerHTML == "Add to list") {
    carts.push(bookId);
    event.target.innerHTML = "Remove to list";
  } else {
    carts.splice(carts.indexOf(bookId), 1);
    event.target.innerHTML = "Add to list";
  }
  $("#my-cart").html(carts.length);
}

function author(authorName) {
  let author = $(".author");
  author.parent().parent().parent().hide();
  for (let i = 0; i < author.length; i++) {
    if (authorName == author[i].innerText) {
      $(author.parent().parent().parent()[i]).show();
    }
  }
}

function removeBookInCart(bookId) {
  if(typeof carts =="string"){
    carts = carts.split(",").map(data => parseInt(data));
  }
  $("."+bookId).hide();
  carts.splice(carts.indexOf(bookId), 1);
}

function redirect(url) {
  if(carts.length > 0){
    location.href = url+"?id="+carts;
  }else {
    showToast("Book is not available","alert-info","fa-info-circle");
  }
}