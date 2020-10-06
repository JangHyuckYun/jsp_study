<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>

<section class="login rel">
    <div class="cover rel">
        <form action="/login_action" method="post">
            <div class="form-group">
                <p>아이디</p>
                <input class="form-control" required type="text" name="id" placeholder="아이디를 입력하여 주세요...">
            </div>
            <div class="form-group">
                <p>패스워드</p>
                <input class="form-control" required type="password" name="pw" placeholder="패스워드를 입력하여 주세요...">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-info loginButton">로그인</button>
                <button type="button" onclick="location.replace('/user/join');" class="btn btn-secondary">회원가입</button>
            </div>
        </form>
    </div>
</section>

<script>
	window.onload = function() {
		$(document)
		.on("click", ".loginButton", function(e) {
			let
			id = $("[name='id']").val(),
			pw = $("[name='pw']").val();
			
			$.ajax({
				url: '/user/login_action',
				type: 'POST',
				data: {id, pw},
				success:function(value, e1, e2) {
					console.log(value, e1, e2);
				}
			});
		});
	}
</script>

<%@include file="template/footer.jsp" %>