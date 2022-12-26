<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표하기</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">투표하기</div>
		<form name="frm" action="insert">
			<div class="wrapper">
				<table>
					<tr>
						<td>주민번호</td>
						<td class="inp">
							<input type="text" name="jumin" />
							<a>예 : 8906153154726</a>
						</td>
					</tr>
					<tr>
						<td>성명</td>
						<td class="inp"><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>투표번호</td>
						<td class="inp">
							<select name="number">
								<option value="0"></option>
								<option value="1">[1] 김후보</option>
								<option value="2">[2] 이후보</option>
								<option value="3">[3] 박후보</option>
								<option value="4">[4] 조후보</option>
								<option value="5">[5] 최후보</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>투표시간</td>
						<td class="inp"><input type="text" name="time" placeholder="예)09시30분 -> 0930" /></td>
					</tr>
					<tr>
						<td>투표장소</td>
						<td class="inp"><input type="text" name="place"placeholder="예)'제1투표장' 혹은 '제2투표장'" /></td>
					</tr>
					<tr>
						<td>유권자확인</td>
						<td>
							<label><input type="radio" name="check" value="Y" />확인</label>
							<label><input type="radio" name="check" value="N" />미확인</label>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit" onclick="fn_submit(); return false;">투표하기</button>
							<button class="btn" type="reset" onclick="fn_button();">다시하기</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>