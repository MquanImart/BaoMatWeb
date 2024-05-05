<%@ page import="Model.taikhoan" %>
<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
		  integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
		  crossorigin="anonymous" referrerpolicy="no-referrer" />

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		  crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/css/favicon.ico">
	<meta http-equiv="Content-Security-Policy" content="default-src 'none'; script-src 'self' https://cdnjs.cloudflare.com; style-src 'self' https://stackpath.bootstrapcdn.com https://fonts.googleapis.com https://cdnjs.cloudflare.com; font-src 'self' https://fonts.gstatic.com https://cdnjs.cloudflare.com; connect-src 'self'; img-src 'self' https://www.evn.com.vn/userfile/VH/User/huyent_tcdl/images/2021/6/hrmscuatapdoan24621(1).jpeg; form-action 'self';">
</head>
<body>
<%
	String csrfToken = UUID.randomUUID().toString();
	session.setAttribute("csrfToken", csrfToken);
	taikhoan username = (taikhoan) session.getAttribute("user"); %>
<%if (username != null) {
	%>
<jsp:forward page="/trangchu"></jsp:forward>
<%} else {%>
<div class="container">
	<div class="image">
		<img src="https://www.evn.com.vn/userfile/VH/User/huyent_tcdl/images/2021/6/hrmscuatapdoan24621(1).jpeg" style="width:700px;height:460px;">
	</div>
	<div class="form">
		<form action="<%=request.getContextPath()%>/login_post" method="POST" onsubmit="encryptPassword()">
			<h1>ĐĂNG NHẬP</h1>
			<div class="input-box">
				<div class = "box_icon_login"><i class="fa-solid fa-user fa-2xl"></i></div>
				<input type="text" name="username" placeholder="Tài khoản" autocomplete=“off” maxlength="30" title="Tài khoản gồm ký tự thường, hoa, số và không quá 30 ký tự">
			</div>
			<div class="input-box">
				<div class = "box_icon_login"><i class="fa-solid fa-lock fa-2xl"></i></div>
				<input type="password" name="password" id="password" placeholder="Mật khẩu" autocomplete=“off” required maxlength="30">
			</div>
			<input type="hidden" name="csrfToken" value="<%= session.getAttribute("csrfToken") %>">
			<div class="error_mess" style="color:red;">
				<%String errorMsg = (String) request.getAttribute("error"); %>
				<%if (errorMsg != null) { %>
				<p><%=errorMsg %></p>
				<%} %>
			</div>
			<div class="box_show">
				<input type="checkbox" onclick="showpass()"><i>Show pass</i>
			</div>
			<div class = "box_button_login"> <button type="submit" class="btn"><b>Đăng nhập</b></button> </div>
			<div class = "box_form_a">
				<a href="<%=request.getContextPath()%>/forgot" class = "col" >Quên mật khẩu</a>
				<a href="<%=request.getContextPath()%>/change" class = "col" style="text-align: right;" >Đổi mật khẩu</a>
			</div>

		</form>
	</div>
</div>
<script>
	function showpass() {
		var x = document.getElementById("password");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
	window.onload = function() {
		// Lấy tất cả các input trừ input mật khẩu
		var inputs = document.querySelectorAll('input:not([type="password"])');

		// Duyệt qua từng input (trừ password) và thêm sự kiện 'input' để kiểm tra giá trị
		inputs.forEach(function(input) {
			input.addEventListener('input', function() {
				// Kiểm tra nếu giá trị chứa ký tự đặc biệt
				if (/[^a-zA-Z0-9\s]/.test(input.value)) {
					// Nếu có, loại bỏ ký tự đặc biệt đó
					input.value = input.value.replace(/[^a-zA-Z0-9\s]/g, '');
				}

				// Kiểm tra giới hạn độ dài tối đa của input
				var maxLength = input.getAttribute('maxlength');
				if (maxLength !== null && input.value.length > maxLength) {
					input.value = input.value.slice(0, maxLength);
				}
			});
		});

		// Lấy input password
		var passwordInput = document.querySelector('input[type="password"]');

		// Thêm sự kiện 'blur' để kiểm tra mật khẩu sau khi người dùng nhập xong
		passwordInput.addEventListener('blur', function() {
			var password = passwordInput.value;

			// Kiểm tra mật khẩu phải chứa ít nhất 8 ký tự, ít nhất 1 chữ hoa, 1 chữ thường, 1 số, và 1 ký tự đặc biệt
			var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

			if (!passwordPattern.test(password)) {
				// Nếu mật khẩu không đáp ứng yêu cầu
				// Báo cho người dùng và xóa tất cả ký tự trong ô input
				passwordInput.setCustomValidity("Mật khẩu phải chứa ít nhất 8 ký tự, 1 chữ hoa, 1 chữ thường, 1 số, và 1 ký tự đặc biệt");
			} else {
				// Nếu mật khẩu đáp ứng yêu cầu, xóa thông báo lỗi
				passwordInput.setCustomValidity('');
			}
		});
	};

</script>
<%}%>
</body>
</html>