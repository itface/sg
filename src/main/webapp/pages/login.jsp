<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pages/inc/header.jsp"%>
<html>
  <head>
  	<link href="${ctx}/css/sapgarden/login.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/sapgarden/Selectyze.jquery.css" rel="stylesheet" type="text/css" />
	<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/script/Selectyze.jquery.js'/>" type="text/javascript"></script>
  </head>
  <body>
	<form id="postForm" method="POST" action="${ctx}/login-process">
		<div class="login_bg">
		  <div class="login_logo" style="display: none"></div>
		  <div class="login_box" style="display: none">
		    <h1>SAP数据集：</h1>
		    <form:select id="sapDataCollection" class="selectyze1 font1" name="sapDataCollection" path="sapDataCollection"  items="${sapDataCollection}" itemValue="id" itemLabel="alias"/>
		    <h1>用户名：</h1>
		    <input name="username" type="text" class="login_box_input" id='username' value='admin'/>
		    <h1>密码：</h1>
		     <input name="password" type="password" class="login_box_input" id='password' value='123456'/>
		    <input name="" type="submit" class="login_btn_a" value="登 录" onmousedown="this.className='login_btn_b'" onmouseup="this.className='login_btn_a'" onclick="javascript:document.location.href='index.html'"/>
		  </div>
		</div>
		<!-- div id="login-error">${error}</div>
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
   		</div-->
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.selectyze1').Selectyze({
				theme : 'orange'
			});
			$(".login_logo").delay(500).animate({opacity: 'show'});
			$(".login_box").delay(800).animate({
			   top: 100, opacity: 'show'
			 }, 500);
		});
	</script>
  </body>
</html>