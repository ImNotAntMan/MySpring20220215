<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>장바구니</title>

    <!-- Custom fonts for this template -->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/js/demo/datatables-demo.js"></script>
	<script>
	function count(type)  {
		  // 결과를 표시할 element
		  const resultElement = document.getElementById('cs_cnt');
		  
		  // 현재 화면에 표시된 값
		  let number = resultElement.value;
		  
		  // 더하기/빼기
		  if(type === 'plus') {
		    number = parseInt(number) + 1;
		  }else if(type === 'minus')  {
		    number = parseInt(number) - 1;
		  }
		  
		  // 결과 출력
		  resultElement.value = number;
		}
	</script>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

<%@include file="../include/left.jsp" %>
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

<%@include file="../include/header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">
                    	장바구니 정보<br>
                    	아이디 : ${m_id}<br>
                    	이  름 : ${m_name}
                    </h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/shop/list">상품 리스트</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table border=2 class="table table-bordered" width="100%" cellspacing="0" align="center">
                                	<thead>
                                		<tr>
                                			<td>번호</td>
                                			<td>상품명</td>
                                			<td>단가</td>
                                			<td>수량</td>
                                			<td>이미지</td>
                                			<td>합계</td>
                                			<td>전체삭제</a></td>
                                		</tr>
                                	</thead>
                                    <tbody>
                                    	<c:set var="total" value="0" />
                                    	<c:forEach items="${list}" var="shop">
                                    		<tr>
                                    			<td>${shop.cs_code}</td>
                                    			<td><a href="/shop/read?p_code=${shop.p_code}">${shop.p_name}</a></td>
                                    			<td><fmt:formatNumber value="${shop.p_price}" pattern="#,###" />원</td>
                       							<td>
                      								<form method="post" action="/shop/updatecnt">
 														<input type="hidden" name="p_code" value="${shop.p_code}">
														<input type="hidden" name="cs_code" value="${shop.cs_code}">
														<input type="hidden" name="cm_code" value="${shop.cm_code}">
 														<select name = "cs_cnt">
														<c:forEach var="count" begin="1" end="30">
															<c:if test="${count == shop.cs_cnt}">
																<option value="${count}" selected>${count}</option>
															</c:if>
															<c:if test="${count != shop.cs_cnt}">
																<option value="${count}">${count}</option>
															</c:if>
														</c:forEach>
														</select>
														<input type="submit" value="변경">
														<a href="/shop/delete?cs_code=${shop.cs_code}&cm_code=${shop.cm_code}">삭제</a>
                                    				</form>
                                    			</td>
                                    			<td>${shop.cs_cnt}</td>
                                    			<td><img src="/resources/product/${shop.p_code}.jpg"  width = "200">
                                    			</td>
                                    			<td><fmt:formatNumber value="${shop.cs_cnt*shop.p_price}" pattern="#,###" />원</td>
                                    			<td><a href="/shop/delete?cs_code=${shop.cs_code}">삭제</a></td>
                                    		</tr>
                                    		<c:set var="total" value="${total + shop.cs_cnt*shop.p_price}" />
                                    	</c:forEach>
                                    		<tr>
                                    			<td>합계</td>
                                    			<td></td>
                                    			<td></td>
                                    			<td></td>
                                    			<td></td>
                                    			<td><fmt:formatNumber value="${total}" pattern="#,###" />원</td>
                                    			<td><a href="/shop/cartdeleteall?cm_code=${cm_code}&cs_code=${shop.cs_code}">전체삭제</a></td>
                                    		</tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

<%@include file="../include/footer.jsp" %>
</body>

</html>