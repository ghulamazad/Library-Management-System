<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<jsp:include page="./header.jsp"></jsp:include>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Issue Book Details</h1>
                    <!-- DataTales -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Books</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>User's Name</th>
                                            <th>Book's Name</th>
                                            <th>Issue Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>ID</th>
                                            <th>User's Name</th>
                                            <th>Book's Name</th>
                                            <th>Issue Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach var="issueBook" items="${issueBooks}">
                                            <tr>
                                                <td> ${issueBook.id} </td>
                                                <td> ${issueBook.user.username} </td>
                                                <td> ${issueBook.book.bookName} </td>
                                                <td> ${issueBook.date1} </td>
                                                <td>
                                                    <a href="deleteIssueBooks?id=${issueBook.id}"
                                                        class="btn btn-danger btn-circle btn-sm">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
  					<!-- /.container-fluid -->
  <!-- Book add Modal-->
    <div class="modal fade" id="addBookModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add New Book</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <i class="fa fa-times" aria-hidden="true"></i>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="user">
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" id="bookName"
                                aria-describedby="bookNameHelp" placeholder="Enter Book's Name">
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control form-control-user" id="bookQauntity"
                                aria-describedby="quantityHelp" placeholder="Enter Quantity">
                        </div>
                        <a href="saveBook" class="btn btn-primary btn-user btn-block">
                            Save
                        </a>
                        <button class="btn btn-danger btn-user btn-block" type="button"
                            data-dismiss="modal">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
            <jsp:include page="./footer.jsp"></jsp:include>