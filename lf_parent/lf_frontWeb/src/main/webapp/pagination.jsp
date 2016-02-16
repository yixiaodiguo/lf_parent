<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/common-tags" prefix="m"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${ctx}/page/list" id="showForm" method="post">
	<table>
		<c:forEach items="${page.items }" var="o">
			<tr>
				<td>${o.id }</td>
				<td>${o.name }</td>
				<td>${o.time }</td>
			</tr>
		</c:forEach>
	</table>
		<m:page name="page" formID="showForm"/>
</form>
<script src="${ctx}/js/sea.js"></script>
<script>
  // Set configuration
  seajs.config({
    base: "modules/",
    alias: {
      "jquery": "jquery/1.10.1/jquery.js",
      "jquery-debug": "jquery/1.10.1/jquery-debug.js",
      "common":"common/common.js"
    }
  });
  var baseUrl = "${ctx}";
  seajs.use("user/list.js");
</script>
</body>
</html>