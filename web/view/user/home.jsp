<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Library Management System">
    <meta name="author" content="Md.Ghulam Azad Ansari">

    <title>Library Management System</title>
    <!-- Custom fonts for this template-->
    <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- bootstrap css-->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">
</head>

<body>
    <div class="wrapper">
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <a class="d-flex align-items-center justify-content-center" href="AdminDashboard">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Library Management System</div>
            </a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown no-arrow mx-1">
                    <a class="nav-link" onclick="redirect('my-cart')">
                        <i class="fas fa-envelope fa-fw"></i>
                        <span class="badge badge-danger badge-counter" id="my-cart"></span>
                    </a>
                </li>
                <div class="topbar-divider d-none d-sm-block"></div>
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">Ghulam</span>
                        <img class="img-profile rounded-circle" src="/assets/img/Ghulam.jpg" />
                    </a>
                    <!-- Dropdown - User Information -->
                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                        aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#userProfile">
                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                            Profile
                        </a>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#activityLog">
                            <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                            Activity Log
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                            Logout
                        </a>
                    </div>
                </li>
            </ul>
        </nav>
        <div class="container-fluid">
            <div class="row justify-content-center p-3">
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" class="form-control search-input" placeholder="Search" name="bookName"
                            id="filter-txt">
                        <button type="submit" class="btn-search" id="search-btn"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="text-center">
                    <h1>LATEST BOOK</h1>
                    <h4 id="search-msg"></h4>
                </div>
                <div class="row book-cards">
                    <c:forEach var="book" items="${books}">
                    <div class="col-md-3 col-sm-6 book-card ${book.bookName.toLowerCase()}">
                        <div class="book-grid shadow">
                            <div class="book-cover">
                                <img src="/assets/img/no_image.jpg">
                                <a class="add-to-list" onclick="addTocart(${book.id},event)">Add to list</a>
                            </div>
                            <div class="book-content">
                                <h3 class="title">${book.bookName}</h3>
                                <span class="author"><a onclick="author('${book.author}')">${book.author}</a></span>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Md.Ghulam Azad Ansari 2020</span>
                </div>
            </div>
        </footer>
    </div>
    <!-- Message Container-->
    <div id="notification" class="toast my-toast fade hide"
         data-delay='10000' data-animation="true"></div>
    <!-- Bootstrap core JavaScript-->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugins -->
    <script src="assets/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>
    <!-- Page level custom scripts -->
    <script src="assets/js/datatables.js"></script>
    <script src="assets/js/script.js"></script>
</body>
</html>