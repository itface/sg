<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/calendar.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<title>无标题文档</title>
<style>
body {
	padding:0px;
	margin:0px;
	background:#f9f9f9;
}
.biangeng {
	padding:10px;


}
.biangeng_main {
	font-family:微软雅黑;
	font-size:12px;
	line-height:40px;
}
.biangeng_botton_main {
	height:41px;
	padding-top:15px;
	padding-right:8px;
	position:relative;
	bottom:2px;
 *bottom:1px;
	text-align:right;
	margin-left:1px;
	margin-right:1px;

 *margin-left:1px;
 *margin-right:1px;
	background:url(images/alert_toobar_bg.png);

}
.alert_right_bold{ font-weight:bold; float:right; width:200px;text-align:left;}
a{ font-size:12px; text-decoration:none}

</style>
</head>

<body style="overflow:hidden">
<div class="biangeng">
  <div class="biangeng_main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right"  class="alert_right_bold">当前运行状态：</td>
        <td>停止运行</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right" class="alert_right_bold">变更运行状态：</td>
        <td colspan="2"><input name="input" type="checkbox" value="" id='focusEl'/>
启动运行</td>
      </tr>
      <tr>
        <td align="right" class="alert_right_bold" >实时同步，设定起始时间：</td>
        <td colspan="2"><div class="schedule">
          <input type="text" class="input2"  value="起始日期" onclick="javascript:if(this.value=='起始日期') this.value='';return false;" onfocus="WdatePicker({sShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){document.getElementById('focusEl').focus();}})">
			</div>
          <select name="" class="select">
            <option>选择小时</option>
            <option>9点</option>
            <option>10点</option>
            <option>11点</option>
            <option>12点</option>
          </select>
      <select name="" class="select">
            <option>选择分钟</option>
            <option>15分</option>
            <option>30分</option>
            <option>45分</option>
            <option>60分</option>
          </select>
        </td>
      </tr>
      <tr>
        <td align="right" class="alert_right_bold">间隔时间：</td>
        <td colspan="2">          
        <select name="" class="select">
            <option>选择小时</option>
            <option>9点</option>
            <option>10点</option>
            <option>11点</option>
            <option>12点</option>
          </select>
      <select name="" class="select">
            <option>选择分钟</option>
            <option>15分</option>
            <option>30分</option>
            <option>45分</option>
            <option>60分</option>
          </select></td>
      </tr>
    </table>
  </div>
</div>
<div class="biangeng_botton_main">

    <a href="#" class="btn4"  onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right">取消</a> 
  <a href="#" class="btn4"  onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right">保存</a> 

</div>
</body>
</html>
