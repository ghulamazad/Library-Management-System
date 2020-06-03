<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="./header.jsp"></jsp:include>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">User Details</h1>
	<div class="row justify-content-end">
		<div class="p-2">
			<a href="#" class="btn btn-primary btn-icon-split"
				data-toggle="modal" data-target="#addNewUser"> <span
				class="icon text-white-50"> <i class="fa fa-plus"
					aria-hidden="true"></i>
			</span> <span class="text">Add User</span>
			</a>
		</div>
	</div>
	<!-- DataTales -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Users</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Type</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Type</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.email}</td>
								<td>${user.type}</td>
								<td><a href="modifyUser?id=${user.id}"
									class="btn btn-success btn-circle btn-sm"> <i
										class="fa fa-pencil-alt" aria-hidden="true"></i>
								</a> <a href="deleteUser?id=${user.id}"
									class="btn btn-danger btn-circle btn-sm"> <i
										class="fa fa-trash"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- /.container-fluid -->
<!-- Add User Modal-->
<div class="modal fade" id="addNewUser" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add New User</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<i class="fa fa-times" aria-hidden="true"></i>
				</button>
			</div>
			<div class="modal-body">
				<form class="user" action="addUser" method="post"
					enctype="multipart/form-data" id="add-user-form">
					<div class="form-group text-center position-relative">
						<img class="img-profile rounded-circle"
							src="./assets/img/no_image.jpg" height="160" width="160"
							id="add-user-image" /> <label
							class="btn btn-primary btn-circle btn-img-upload"> <i
							class="fa fa-camera" aria-hidden="true"></i> <input type="file"
							name="img" onchange="loadImage(event,'add-user-image')" style="display: none;"
							accept="image/*" required="required">
						</label>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							name="username" placeholder="Enter name" required="required" />
					</div>
					<div class="form-group">
						<input type="email" class="form-control form-control-user"
							name="email" placeholder="Enter Email Address..."
							required="required" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							name="password" placeholder="Enter New Password"
							required="required">
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							name="cpassword" placeholder="Re-type Password"
							required="required">
					</div>
					<div class="form-group" style="margin-left: .6rem;">
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" name="user_type"
								id="admin"> <label class="custom-control-label"
								for="admin">Admin</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" name="user_type"
								id="user" checked> <label class="custom-control-label"
								for="user">User</label>
						</div>
					</div>
					<button class="btn btn-primary btn-user btn-block" type="submit">Add</button>
					<button class="btn btn-danger btn-user btn-block" type="button"
						onclick="reset('add-user-form')" data-dismiss="modal">Cancel</button>
				</form>
			</div>
		</div>
	</div>
</div>
<!--Update User Modal-->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Update User</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<i class="fa fa-times" aria-hidden="true"></i>
				</button>
			</div>
			<div class="modal-body">
				<form class="user" action="updateUser" method="post"
					enctype="multipart/form-data" id="update-user-form">
					<input type="text" name="id" value="${user.id}" hidden="true">
					<div class="form-group text-center position-relative">
						<img class="img-profile rounded-circle"
							src="data:image/png;base64,${user.getBase64Image()}" height="160"
							width="160" id="update-user-image" /> <label
							class="btn btn-primary btn-circle btn-img-upload"> <i
							class="fa fa-camera" aria-hidden="true"></i> <input type="file"
							name="img" onchange="loadImage(event,'update-user-image')" style="display: none;"
							accept="image/*" required="required">
						</label>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							name="username" value="${user.username}" placeholder="Enter name"
							required="required" />
					</div>
					<div class="form-group">
						<input type="email" class="form-control form-control-user"
							name="email" value="${user.email}"
							placeholder="Enter Email Address..." required="required" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							name="password" placeholder="Enter New Password"
							required="required">
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							name="cpassword" placeholder="Re-type Password"
							required="required">
					</div>
					<div class="form-group" style="margin-left: .6rem;">
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" name="user_type"
								   id="admin"> <label class="custom-control-label"
													  for="admin">Admin</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" name="user_type"
								   id="user" checked> <label class="custom-control-label"
															 for="user">User</label>
						</div>
					</div>
					<button class="btn btn-primary btn-user btn-block" type="submit">Update</button>
					<button class="btn btn-danger btn-user btn-block" type="button"
						onclick="reset('update-user-form')" data-dismiss="modal">Cancel</button>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./footer.jsp"></jsp:include>