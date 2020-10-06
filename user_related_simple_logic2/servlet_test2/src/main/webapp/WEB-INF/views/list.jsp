
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="template/header.jsp"%>

<button class="btn btn-info logoutButton">로그아웃</button>
<section class="search rel">
	<div class="cover flex center align rel">
		<input type="text" class="form-control" name="search"
			placeholder="검색어를 입력하여 주세요...">
		<button class="btn btn-info searchButton">검색하기</button>
	</div>
</section>
<section class="uesr_list rel">
	<div class="cover rel">
		<table class="table user_table">
			<thead>
				<tr>
					<th>No</th>
					<th>회원 ID</th>
					<th>회원 이름</th>
					<th>가입 일자</th>
					<th>회원 삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${memberList}" varStatus="status">
					<tr>
						<td data-check="idx">${item.idx}</td>
						<td><span class='openModal' data-toggle="modal"
							data-target="#user_modal" data-idx="${item.idx}" data-check="id"><c:out
									value="${item.id}" /></span></td>
						<td data-check="name"><c:out value="${item.name}" /></td>
						<td data-check="dates"><c:out value="${item.dates}" /></td>
						<td>
							<button class="btn btn-info removeButton"
								data-idx="<c:out value="${item.idx}" />">회원삭제</button>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</section>
<div id="user_modal" class="modal fade" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h3>회원정보 수정 팝업</h3>
				<button class="btn" onclick="$('#user_modal').modal('hide');">X</button>
			</div>
			<div class="modal-body">
				<form action="/user_modify_action" method="post">
					<div class="form-group">
						<p>이름</p>
						<input class="form-control" type="text" name="name" value="asd">
					</div>
					<div class="form-group">
						<p>아이디</p>
						<input class="form-control" type="text" name="id" value="asd">
					</div>
					<div class="form-group">
						<p>패스워드</p>
						<input class="form-control" type="password" name="pw" value="asd">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-info modifyButton">수정 완료</button>
			</div>
		</div>
	</div>
</div>

<script>

let list = Array.from($(".user_table tbody tr").clone());
const example = {
		"1월": "Jan",
		"2월":"Feb",
		"3월":"Mar",
		"4월":"Apr",
		"5월":"May",
		"6월":"Jun",
		"7월":"Jul",
		"8월":"Aug",
		"9월":"Sep",
		"10월":"Oct",
		"11월":"Nov",
		"12월":"Dec",
};
const msg = "${test}";
 if(msg !== "success") location.replace('/');
	function render(data) {
		$(".user_table tbody").html(data.map((v, idx) => `
		<tr>
		<td data-check="idx">`+(idx + 1)+`</td>
		<td data-check="id"><span class='openModal'
			data-toggle="modal" data-target="#user_modal"
			data-idx="`+(v.idx)+`">`+v.id+`</span></td>
		<td data-check="name">`+v.name+`</td>
		<td data-check="dates">`+v.dates+`</td>
		<td>
			<button class="btn btn-info removeButton"
				data-id="`+v.id+`">회원삭제</button>
		</td>
	</tr> `).join(''));
	}
	
	function PlusZero(txt) {
		return String(txt).length == 1 ? "0"+txt : txt;
	}
	
	function search(e) {
		const value = $("[name='search']").val();
		
		$.ajax({
			url: '/user/select',
			type: 'POST',
			data: {value},
			success:function(value, e1, e2) {
				console.log(value);
				const data = value.map((v) => {
					const month_idx = v.dates.indexOf("월");
					const cut = v.dates.substr(0, month_idx + 1);
					v.dates = v.dates.replace(cut, example[cut]);
					
					const date = new Date(v.dates);
					const year = date.getFullYear();
					const month = PlusZero((date.getMonth() + 1));
					const day = PlusZero(date.getDate());
					console.log(v.dates, date, year, month, day);
					return {
						...v,
						dates:(year+"-"+month+"-"+day),
					}
				});
				
				render(data);
			},
			error:function(e) {
				console.log("error", e);
			}
		})
		
	}
	
	$(document)
	.on("click", ".searchButton", search)
	.on("keydown", "[name='search']", function( { key } ) {
		if(key === "Enter") search();
	})
	.on("click", ".logoutButton", function(e) {
		
		$.ajax({
			url: '/user/logout',
			type: 'GET',
			data: {test:"test"},
			success:function(value, e1 ,e2) {
				location.replace("/");
			},
			error:function(e) {
				console.log(e);
			}
		})
	})
	.on("click", ".openModal", function( { target } ) {
		const idx =  Number(target.dataset.idx);
		console.log(idx);
		$.ajax({
			url: '/user/getUserData',
			type: 'POST',
			data: {idx},
			success:function(value, e1 ,e2) {
				console.log(value);
				$("#user_modal form").html( value.map( function( v ) { return`
					<div class="form-group">
					<p>이름</p>
					<input class="form-control" type="text" name="name" value="`+v.name+`">
				</div>
				<div class="form-group">
					<p>아이디</p>
					<input class="form-control" type="text" name="id" value="`+v.id+`">
				</div>
				<div class="form-group">
					<p>패스워드</p>
					<input class="form-control" type="password" name="pw" value="`+v.pw+`">
				</div>
			`}).join('') );
				$(".modifyButton").attr("data-idx",idx);
			},
			error:function(e) {
				console.log(e);
			}
		})
	})
	.on("click", ".modifyButton", function(e) {
		const idx = e.target.dataset.idx;
		const pw = $("[name='pw']").val();
		const id = $("[name='id']").val();
		const name = $("[name='name']").val();
		console.log(idx, pw, id, name);
		
		$.ajax({
			url: '/user/updateUserData',
			type: 'POST',
			data: {idx,id, pw, name},
			success:function(value, e1 ,e2) {
				location.reload();
			},
			error:function(e) {
				console.log(e);
			}
		})
	})
	.on("click", ".removeButton", function(e) {
		const idx = e.target.dataset.idx;
		
		$.ajax({
			url: '/user/removeUserData',
			type: 'POST',
			data: {idx},
			success:function(value, e1 ,e2) {
				location.reload();
			},
			error:function(e) {
				console.log(e);
			}
		})
	})
</script>

<%@include file="template/footer.jsp"%>