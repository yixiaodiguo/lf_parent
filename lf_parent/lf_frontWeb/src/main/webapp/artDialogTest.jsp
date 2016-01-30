<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="${ctx}/script/sea.js"></script>
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