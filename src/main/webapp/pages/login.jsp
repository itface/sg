<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pages/inc/header.jsp"%>
<html>
  <head>
  </head>
  <body>
	<form id="postForm" method="POST" action="${ctx}/login-process">
		<div id="login-error">${error}</div>
		<!--div id="error">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</div-->
   		<div style='top:300px;left:550px;position:absolute;height:200px;width:300px'>
   			<table width=100% >
   				<tr style='line-height:30px'>
   					<td style='width:40%;text-align:right;font-size:12px'><label>用户名:</label></td>
   					<td style='width:60%;text-align:right;font-size:12px'><input type='text' name='username' id='username' value='admin'/></td>
   				</tr>
   				<tr style='line-height:30px'>
   					<td style='width:40%;text-align:right;font-size:12px'><label>密码:</label></td>
   					<td style='width:60%;text-align:right;font-size:12px'><input type='text' name='password' id='password' value='123456'/></td>
   				</tr>
   				<tr style='line-height:30px'>
   					<td style='width:40%;text-align:right;font-size:12px'><label>sap客户端:</label></td>
   					<td style='width:60%;text-align:right;font-size:12px'><form:select id="sapDataCollection" name="sapDataCollection" path="sapDataCollection"  items="${sapDataCollection}" itemValue="id" itemLabel="alias"/></td>
   				</tr>
   				<tr style='line-height:30px'>
   					<td align='right' colspan='2'><input type='submit' value='登录'/></td>
   				</tr>
   			</table>
   		</div>
	</form>
  </body>
</html>