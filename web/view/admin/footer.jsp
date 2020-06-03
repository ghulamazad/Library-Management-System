<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</div>
<!-- End of Main Content -->
<!-- Footer -->
<footer class="sticky-footer bg-white">
	<div class="container my-auto">
		<div class="copyright text-center my-auto">
			<span>Copyright &copy; Md.Ghulam Azad Ansari 2019</span>
		</div>
	</div>
</footer>
<!-- End of Footer -->
</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Message Container-->
<div id="notification" class="toast my-toast fade hide"
	data-delay='10000' data-animation="true"></div>
<!-- User Profile Modal-->
<div class="modal fade" id="userProfile" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Profile</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<i class="fa fa-times" aria-hidden="true"></i>
				</button>
			</div>
			<div class="modal-body">
				<form class="user" action="updateCurrentUser" method="post"
					enctype="multipart/form-data">
					<div class="form-group text-center position-relative">
						<img class="img-profile rounded-circle"
							src="data:image/png;base64,${sessionScope['loggedUser'].getBase64Image()}"
							height="160" width="160" id="profile-img" /> <label
							class="btn btn-primary btn-circle btn-img-upload"> <i
							class="fa fa-camera" aria-hidden="true"></i> <input type="file"
							name="img" onchange="loadImage(event,'profile-img')" style="display: none;"
							accept="image/*" required="required">
						</label>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							name="username" placeholder="Enter name"
							value="${sessionScope['loggedUser'].getUsername()}"
							required="required" />
					</div>
					<div class="form-group">
						<input type="email" class="form-control form-control-user"
							name="email" placeholder="Enter Email Address..."
							value="${sessionScope['loggedUser'].getEmail()}"
							required="required" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							name="password" placeholder="Enter New Password" required="required">
					</div>
					<button class="btn btn-primary btn-user btn-block" type="submit">Update</button>
					<button class="btn btn-danger btn-user btn-block" type="button"
						data-dismiss="modal">Cancel</button>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- Activity Log Modal-->
<div class="modal fade" id="activityLog" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Activity Log</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<i class="fa fa-times" aria-hidden="true"></i>
				</button>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>ID</th>
								<th>Date & Time</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="activityLog" items="${activityLogs}">
								<tr>
									<td>${activityLog.id}</td>
									<td>${activityLog.localDateTime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<i class="fa fa-times" aria-hidden="true"></i>
				</button>
			</div>
			<div class="modal-body">Select "Logout" below if you are ready
				to end your current session.</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
				<form action="logout" method="post">
					<button class="btn btn-primary" type="submit">Logout</button>
				</form>
			</div>
		</div>
	</div>
</div>
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
<!-- call toast -->
<c:if test="${isShowToast}">
	<script>
		showToast("${msg}", "${alertType}", "${icon}")
	</script>
</c:if>
<!-- call model -->
<c:if test="${isUpdate}">
	<script>
		$("#updateModal").modal("show");
	</script>
</c:if>
</body>

</html>