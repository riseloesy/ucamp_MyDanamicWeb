<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 리스트</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>UserId</th>
			<th>Name</th>
			<th>Gender</th>
			<th>City</th>
		</tr>
		<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.id}</td>
			<td>${user.userId}</td>
			<td>${user.name}</td>
			<td>${user.gender}</td>
			<td>${user.city}</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>