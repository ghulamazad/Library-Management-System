<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="./header.jsp"></jsp:include>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
	</div>

	<!-- Content Row -->
	<div class="row">

		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-primary shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-primary text-uppercase mb-1">
								Books</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${totalBook}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-book fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-success shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-success text-uppercase mb-1">
								Users</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${totalUser}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-users fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-info shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-info text-uppercase mb-1">
								Issue Books</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${totalIssueBook}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-list fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-warning shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-warning text-uppercase mb-1">
								Book Issue Request</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${totalBookIssueRequest}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-comments fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- DataTales -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Book Issue Request</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>ID</th>
							<th>User's Name</th>
							<th>Book's Name</th>
							<th>Request Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>ID</th>
							<th>User's Name</th>
							<th>Book's Name</th>
							<th>Request Date</th>
							<th>Return Date</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="bookRequest" items="${bookIssueRequests}">
							<tr>
								<td>${bookRequest.id}</td>
								<td>${bookRequest.user.username}</td>
								<td>${bookRequest.book.bookName}</td>
								<td>${bookRequest.issueDate}</td>
								<td>${bookRequest.returnDate}</td>
								<td><a href="accept?id=${bookRequest.id}"
									class="btn btn-success btn-icon-split"> <span
										class="icon text-white-50"> <i class="fas fa-check"></i>
									</span> <span class="text">Accept</span>
								</a> <a href="reject?id=${bookRequest.id}"
									class="btn btn-danger btn-icon-split"> <span
										class="icon text-white-50"> <i class="fas fa-trash"></i>
									</span> <span class="text">Reject</span>
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
<jsp:include page="./footer.jsp"></jsp:include>