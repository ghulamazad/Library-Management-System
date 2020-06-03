<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp"></jsp:include>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Books</h1>
    <div class="row justify-content-end">
        <div class="p-2">
            <a href="#"
               class="btn btn-primary btn-icon-split" data-toggle="modal" data-target="#addBookModal"> <span
                    class="icon text-white-50"> <i class="fa fa-plus"
                                                   aria-hidden="true"></i>
			</span> <span class="text">Add Book</span>
            </a>
        </div>
    </div>
    <!-- DataTales -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Books</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%"
                       cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.bookName}</td>
                            <td>${book.author}</td>
                            <td>${book.quantity}</td>
                            <td><a href="modifyBook?id=${book.id}"
                                   class="btn btn-success btn-circle btn-sm"> <i
                                    class="fa fa-pencil-alt" aria-hidden="true"></i>
                            </a> <a href="deleteBook?id=${book.id}"
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

<!-- add Book Modal-->
<div class="modal fade" id="addBookModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add New Book</h5>
                <button class="close" type="reset" data-dismiss="modal"
                        aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
            </div>
            <div class="modal-body">
                <form class="user" action="saveBook" method="post" id="add-book-form" enctype="multipart/form-data">
                    <div class="form-group text-center position-relative">
                        <img class="img-profile rounded-circle"
                             src="./assets/img/no_image.jpg" height="160"
                             width="160" id="add-book-image"/>
                        <label class="btn btn-primary btn-circle btn-img-upload">
                            <i class="fa fa-camera" aria-hidden="true"></i>
                            <input type="file" name="img" onchange="loadImage(event,'add-book-image')" style="display: none;"
                                   accept="image/*" required="required">
                        </label>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control form-control-user"
                               name="bookName" placeholder="Enter Book's Name"
                               required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control form-control-user"
                               name="author" placeholder="Enter Book's Author"
                               required="required">
                    </div>
                    <div class="form-group">
                        <input type="number"
                               class="form-control form-control-user form-control-number"
                               name="bookQuantity" placeholder="Enter Quantity"
                               required="required">
                    </div>
                    <button type="submit" class="btn btn-primary btn-user btn-block">Save</button>
                    <button class="btn btn-danger btn-user btn-block" type="button"
                            onclick="document.getElementById('add-book-form').reset()"
                            data-dismiss="modal">Cancel
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Update Book Modal-->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Update Book</h5>
                <button class="close" type="reset" data-dismiss="modal"
                        aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
            </div>
            <div class="modal-body">
                <form class="user" action="updateBook" method="post" id="update-book-form" enctype="multipart/form-data">
                    <input type="text" name="id" value="${book.id}" hidden="true">
                    <div class="form-group text-center position-relative">
                        <img class="img-profile rounded-circle"
                             src="data:image/png;base64,${book.getBase64Image()}" height="160"
                             width="160" id="update-book-image"/>
                        <label class="btn btn-primary btn-circle btn-img-upload">
                            <i class="fa fa-camera" aria-hidden="true"></i>
                            <input type="file" name="img" onchange="loadImage(event,'update-book-image')" style="display: none;"
                                   accept="image/*" required="required">
                        </label>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control form-control-user"
                               name="bookName" placeholder="Enter Book's Name"
                               value="${book.bookName}" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control form-control-user"
                               name="bookName" placeholder="Enter Book's Author"
                               value="${book.author}" required="required">
                    </div>
                    <div class="form-group">
                        <input type="number"
                               class="form-control form-control-user form-control-number"
                               name="bookQuantity" placeholder="Enter Quantity"
                               value="${book.quantity }" required="required">
                    </div>
                    <button id="update" class="btn btn-primary btn-user btn-block"
                            type="submit">Update
                    </button>
                    <button class="btn btn-danger btn-user btn-block" type="button" onclick="reset('update-book-form')"
                            data-dismiss="modal">Cancel
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="./footer.jsp"></jsp:include>