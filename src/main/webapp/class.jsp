<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Map<String,String>> classList = (List<Map<String,String>>)request.getAttribute("welcomeAttr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
<%
	for(Map<String,String> classMap:classList){
%>
		<div>
			<%
				for(String key:classMap.keySet()){
			%>
			<span>
				<%=classMap.get(key)%>
			</span>
			<%	
				}
			%>
		</div>	
<%		
	}
%>
	</div>
</body>
</html>