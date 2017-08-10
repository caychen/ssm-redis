<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/user/toadd">添加</a>
	<table style="border: 1px solid black">
		<thead>
			<tr>
				<th>用户名</th>
				<th>生日</th>
				<th>薪资</th>
				<th>职位</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users }" var="user">
				<tr>
					<td>${user.username }</td>
					<td><fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/> </td>
					<td>${user.salary }</td>
					<td>${user.employee.empName }</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
</body>
</html>