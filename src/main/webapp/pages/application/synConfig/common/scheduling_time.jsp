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
	background:url(${ctx}/images/alert_toobar_bg.png);

}
.alert_right_bold{ font-weight:bold; float:right; width:160px;text-align:left;}
a{ font-size:12px; text-decoration:none}

</style>
</head>

<body style="overflow:hidden">
<form  id='schedulingForm'>
<div class="biangeng">
  <div class="biangeng_main" style="height:160px">
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left"  class="alert_right_bold">当前运行状态：</td>
        <td width="400">${currentStatus}</td>
      </tr>
      <tr>
        <td align="left" class="alert_right_bold">变更运行状态：</td>
        <td >
        	<input type="checkbox" id="runstatus"/>&nbsp;启动运行
		</td>
      </tr>
      <tr class='runStatusRow'>
        <td align="left" class="alert_right_bold" >定时同步，设定起始时间：</td>
        <td>
        	<div class="schedule">
        		<input type='text' class="input2" onclick="WdatePicker()" value="${begindate}" id="begindate"/>
   			</div>
   			<div style="float:left">
	   			<select class="select" id="beginhour">
						<option>选择小时</option>
						<option value='0'>0点</option>
						<option value='01'>1点</option>
						<option value='02'>2点</option>
						<option value='03'>3点</option>
						<option value='04'>4点</option>
						<option value='05'>5点</option>
						<option value='06'>6点</option>
						<option value='07'>7点</option>
						<option value='08'>8点</option>
						<option value='09'>9点</option>
						<option value='10'>10点</option>
						<option value='11'>11点</option>
						<option value='12'>12点</option>
						<option value='13'>13点</option>
						<option value='14'>14点</option>
						<option value='15'>15点</option>
						<option value='16'>16点</option>
						<option value='17'>17点</option>
						<option value='18'>18点</option>
						<option value='19'>19点</option>
						<option value='20'>20点</option>
						<option value='21'>21点</option>
						<option value='22'>22点</option>
						<option value='23'>23点</option>
				</select>
   			</div>
   			<div style="float:left;height:28px;line-height:23px;padding-right:5px;">
   				:
   			</div>
			<div>
				<select class="select" id="beginminute">
	            	<option>选择分钟</option>
	            	<option value='0'>0分</option>
					<option value='10'>10分</option>
					<option value='20'>20分</option>
					<option value='30'>30分</option>
					<option value='40'>40分</option>
					<option value='50'>50分</option>
	          </select>
			</div>
        </td>
      </tr>
      <tr class='runStatusRow'>
        <td align="left" class="alert_right_bold">间隔时间：</td>
        <td >
        	<form:input path='job.intervalminute' class="input2"/><div style="float:left;line-height:30px;color:#747373">分钟</div>    
        </td>
      </tr>
    </table>
  </div>
</div>
<div class="biangeng_botton_main">
  <a href="javascript:void(0);" class="btn4 cancelBtn"  onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right">取消</a> 
  <a href="javascript:void(0);" class="btn4 saveBtn"  onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right">保存</a> 
</div>
<div style="display:none">
	<form:input path="job.id"/>
	<form:input path="job.jobbegindate"/>
	<form:input path="job.jobclass"/>
	<form:input path="job.jobgroup"/>
	<form:input path="job.jobowner"/>
	<form:input path="job.sapclient"/>
	<form:input path="job.jobname"/>
	<form:input path="job.jobstatus"/>
	<input type="text" id="jobtype" name="jobtype" value="TIME">
	<input type="text" id="_method" name="_method"/>
</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	initEvent();
	initForm();
	function initForm(){
		var jobbegindate = $('#jobbegindate').val();
		if(jobbegindate!=''){
			var date = jobbegindate.substring(0,10);
			var hour = jobbegindate.substring(11,13);
			var minute = jobbegindate.substring(14,16);
			$('#begindate').val(date);
			$('#beginhour').val(hour);
			$('#beginminute').val(minute);
		}
		if($('#jobstatus').val()==1){
			$('#runstatus').attr("checked",true);
		}else{
			$('#runstatus').attr("checked",false);
		}
		setFomrDisabled();
	}
	function setFomrDisabled(){
		if($('#runstatus').attr("checked")=="checked"){
			$('.runStatusRow').show();
			/*
			$('#intervalminute').attr("disabled",false);
			$('#begindate').attr("disabled",false);
			$('#beginhour').attr("disabled",false);
			$('#beginminute').attr("disabled",false);
			*/
		}else{
			$('.runStatusRow').hide();
			/*
			$('#intervalminute').attr("disabled",true);
			$('#begindate').attr("disabled",true);
			$('#beginhour').attr("disabled",true);
			$('#beginminute').attr("disabled",true);
			*/
		}
	}
	function initEvent(){
		$('.cancelBtn').bind('click',function(e){
			closeWin();
		});
		$('#runstatus').bind('click',function(e){
			setFomrDisabled();
		});
		$('.saveBtn').bind('click',function(e){
			var   r   =   /^[0-9]*[1-9][0-9]*$/;　
			if($('#begindate').val()==''){
				alert('起始日期不能为空');
				return false;
			}else if($('#beginhour').val()==''){
				alert('起始小时不能为空');
				return false;
			}else if($('#beginminute').val()==''){
				alert('起始分钟不能为空');
				return false;
			}else if($('#intervalhour').val()==''){
				alert('间隔小时不能为空');
				return false;
			}else if($('#intervalminute').val()==''){
				alert('间隔分钟不能为空');
				return false;
			}
			if(!r.test($('#intervalminute').val())){
				alert("间隔分钟数必须是正整数");
				return false;
			}
			$('#jobbegindate').val($('#begindate').val()+" "+$('#beginhour').val()+":"+$('#beginminute').val()+":00");
			if($('#runstatus').attr("checked")=="checked"){
				$('#jobstatus').val(1);
			}else{
				$('#jobstatus').val(0);
			}
			if($('#id').val()>0){
				$('#_method').val("PUT");
			}else{
				$('#_method').val("POST");
			}
			$.ajax({
				type:'POST',
				url:'${ctx}/application/commonScheduling/time/${type}',
				data:$("#schedulingForm").serialize(),//序列化表单里所有的内容
				success: function(data){
					alert('操作成功');
					reloadParendPage();
					closeWin();
				},
				error:function(){
					alert('保存失败');
				}
			 });
		});
	}
	function closeWin(){
		var api = frameElement.api;
		var  W = api.opener; 
		var dia = W.$.dialog.data('dialog');
		dia.close();
	}
	function reloadParendPage(){
		var api = frameElement.api;
		var  W = api.opener; 
		var reloadPage = W.$.dialog.data('reloadPage');
		reloadPage();
	}
});

</script>
</body>
</html>
