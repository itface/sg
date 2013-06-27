<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<style>
tr{
	line-height:30px;
}
</style>
</head>
<body>
<form>
	<table style='width:100%'>
		<tr>
			<td>功能</td>
		</tr>
		<tr>
			<td>从指定的SAP系统中将公司代码的详细信息同步到Garden系统对应的表中。</td>
		</tr>
		<tr>
			<td>用途</td>
		</tr>
		<tr>
			<td>
			SAP系统公司代码的信息，包括其对应的公司代码名称、城市、国家代码、货币码、语言代码、账目表、会计年度变式、增值税登记号、公司及地址号码，通过此接口同步到Garden对应的表MASTER_COMPANYCODE_DETAIL。 
　　 		<br/>
			此接口可以得到SAP系统中所有公司代码的信息。
			</td>
		</tr>
		<tr>
			<td>运行条件</td>
		</tr>
		<tr>
			<td>
				Garden系统连接SAP系统正常。
				<br/>
				登录SAP系统的账号有足够的权限执行RFC。
				<br/>
				表MASTER_COMPANYCODE_DETAIL存在Garden系统中。
			</td>
		</tr>
		<tr>
			<td>页签功能</td>
		</tr>
		<tr>
			<td>1、运行监控页签功能</td>
		</tr>
		<tr>
			<td>
			监控接口发生的公司代码主数据从SAP同步到Garden表MASTER_COMPANYCODE_DETAIL异常情况，同步发生异常将被反映在“异常监控记录”区域。
			<br/>
			通过查看异常记录，点击需要处理记录行前面的选择框选中此行记录，点击“处理异常记录”按钮，系统重新执行此行的同步SAP公司代码数据动作。
			<br/>
			当异常记录较多时，如果需要选择特定的异常记录，可以通过设置特定的查询条件后点击“查询”按钮即可在“异常监控记录”区域显示筛选后的异常记录，然后再选中处理。
			</td>
		</tr>
		<tr>
			<td>异常监控记录各列含义：</td>
		</tr>
		<tr>
			<td>处理状态是指异常记录被处理的情况，分为未处理、处理成功和处理失败三种情况。异常被监控到后，在此页显示的处理状态为"未处理"；选中此行点击"处理异常记录"按钮后如果重新同步没有发生错误等异常，这行的处理状态在重新运行结束后将被置为"处理成功"，否则置为“处理失败”。</td>
		</tr>
		<tr>
			<td>初始运行时间是这条记录首次运行的时间。</td>
		</tr>
		<tr>
			<td>异常发生时间是最终发生异常的时间。</td>
		</tr>
		<tr>
			<td>处理时间是处理（重新运行）这条异常的时间。</td>
		</tr>
		<tr>
			<td>SAP系统连接是同步公司代码时连接的SAP client，即表明同步时哪个SAP系统的公司代码数据。</td>
		</tr>
		<tr>
			<td> 
			运行内容是表明系统执行同步公司代码时传给SAP的主要参数，一般情况下同步公司代码不需要参数，但也可传输如语言等参数，传输语言参数后，同步的公司代码数据只限于这种语言下的数据。
			<br/>
			执行人是此项同步动作的Garden用户账号。
			</td>
		</tr>
		<tr>
			<td>2、运行设置页签功能：</td>
		</tr>
		<tr>
			<td>页签中包括三个栏目：启用设置、计划任务和字段映射。</td>
		</tr>
		<tr>
			<td>启用设置：设置API是否可用，如果激活选择框中打钩，即为可以使用，否则API中的所有设置，包括计划任务、测试运行等均不能执行。</td>
		</tr>
		<tr>
			<td>计划任务：可以设置定时任务，需要输入任务名称、描述等。
			<br/>
			有效期设置后此任务在有效期范围内有效，否则为失效。公司代码数据API默认有效期为空，即一直有效。可以调整有效期。
			<br/>
			任务状态可以设置任务的停用和启用，与有效期同时起作用。本数据分类默认为启用。
			<br/>
			执行频率是定时任务执行的间隔时间段。
			<br/>
			执行时间是同步公司代码数据具体发生的时间。
			</td>
		</tr>
		<tr>
			<td>字段映射：设置哪些字段同步，哪些字段不需要同步。</td>
		</tr>
		<tr>
			<td>Garden表名是公司代码数据在Garden表系统中存储数据的物理表名，描述是这个表的描述。
			<br/>
			SAP字段是SAP系统中函数的输出字段名，Garden表字段即为Garden系统公司代码数据表中的字段名，是否同步用来设置这个字段的内容是否需要同步，如果某行是灰色，表示这些字段为必须同步的字段。如果不需要同步可以将字段后面的选择框中的打钩去掉后保存即可。
			</td>
		</tr>
		<tr>
			<td>3、测试执行页签功能</td>
		</tr>
		<tr>
			<td> 输入必要的参数值，选择连接的SAP系统，点击“测试执行”按钮，测试结果将显示在测试执行结果区域。</td>
		</tr>
		<tr>
			<td>4、数据校对页签功能</td>
		</tr>
		<tr>
			<td> 
			选择与哪个SAP系统比较后，点击“校对执行”按钮，系统将Garden系统中公司代码数据与选择的SAP系统中公司代码数据进行比较，列出差异。
			<br/>
			选择需要处理的差异数据行，点击“SAP同步Garden”按钮，系统将以SAP中的公司代码数据为准将数据覆盖到Garden系统中。
			</td>
		</tr>
		<tr>
			<td>5、数据浏览页签功能</td>
		</tr>
		<tr>
			<td>查看Garden系统中公司代码的数据。“Garden数据表”表明了公司代码数据存放的表名称。可以根据查询条件筛选需要查询的公司代码数据。“Garden数据表内容”下方区域显示查询的数据结果。</td>
		</tr>
		<tr>
			<td>6、运行日志页签功能</td>
		</tr>
		<tr>
			<td>记录了公司代码数据同步的情况，包括数据的同步的时间、人员、状态、 变动类型、操作类型、运行开始时间、运行结束时间以及运行数据内容等。</td>
		</tr>
		<tr>
			<td>7、接口描述页签功能</td>
		</tr>
		<tr>
			<td>描述了公司代码接口的功能及使用方法。通过阅读接口描述页签可以帮助您了解此接口的功能以及如何正确使用此接口的各项页签功能。</td>
		</tr>
	</table>
</form>
</body>
<script>

</script>
</html>