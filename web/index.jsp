<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Library Management System">
	<meta name="author" content="Md.Ghulam Azad Ansari">
	<title>Library Management System</title>
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<!-- custom css-->
	<link href="assets/css/main.css" rel="stylesheet">
	<link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
</head>

<body class="bg-gradient-primary">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
									</div>
									<form class="user" action="login" method="post">
										<div class="form-group">
											<input type="email" class="form-control form-control-user" id="email"
												name="email" aria-describedby="email"
												placeholder="Enter Email Address..." required>
										</div>
										<div class="form-group">
											<input type="password" name="password"
												class="form-control form-control-user" id="password"
												placeholder="Password" required>
										</div>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" name="remember_me" class="custom-control-input"
													id="customCheck" value="true"> <label class="custom-control-label"
													for="customCheck">Remember
													Me</label>
											</div>
										</div>
										<button class="btn btn-primary btn-user btn-block" type="submit">Login</button>
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="forgot-password.html">Forgot
											Password?</a>
									</div>
									<div class="text-center">
										<a class="small" href="register.html">Create an Account!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Message Container-->
<div id="notification" class="toast my-toast fade hide" data-delay='6000' data-animation="true"></div>
	<!-- Bootstrap core JavaScript-->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugins -->
	<script src="assets/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<script src="assets/js/script.js"></script>
	<c:if test="${isShowToast}">
		<script>showToast("${msg}","${alertType}","${icon}")</script>
	</c:if>
</body>

</html>