<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<section class="login rel">
    <div class="cover rel">
        <form action="/user/join_action" method="post">
            <div class="form-group">
                <p>아이디</p>
                <input class="form-control" required type="text" name="id" placeholder="아이디를 입력하여 주세요...">
            </div>
            <div class="form-group">
                <p>이름</p>
                <input type="text"class="form-control" name="name" placeholder="이름을 입력하여 주세요..." required>
            </div>
            <div class="form-group">
                <p>패스워드</p>
                <input class="form-control" required type="password" name="pw" placeholder="패스워드 입력하여 주세요...">
            </div>
            <div class="form-group">
                <p>패스워드 확인</p>
                <input type="password"class="form-control" required name="pw2" placeholder="패스워드를 입력하여 주세요...">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-info joinButton">회원가입</button>
                <button type="button" onclick="location.replace('/');" class="btn btn-info">취소</button>
            </div>
        </form>
    </div>
</section>

<script>
	window.onload = _ => {
		$(".joinButton").on("click", e => {
			let
			id = $("[name='id']").val(),
			pw = $("[name='pw']").val(),
			name = $("[name='name']").val(),
			pw2 = $("[name='pw2']").val();
			
			if(pw !== pw) {
				e.preventDefault();
				return alert("비밀번호가 서로 일치하지 않습니다.");
			}else {
				$.ajax({
					url: '/join_action',
					type: 'POST',
					data: {id, pw, name},
					success:function(e) {
						alert("회원가입이 완료되었습니다.");
						location.replace('/');
					}
				})

			}
		})
	}
</script>
<%@include file="template/footer.jsp" %>
