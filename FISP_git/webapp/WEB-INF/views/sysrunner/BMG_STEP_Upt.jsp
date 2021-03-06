<script type="text/javascript">
	//init page
	$(function() {
		if ('1' == $("#dpCfgType").val()) {
			$("#dpCfgTypeShow").val("导入");
		} else if ('2' == $("#dpCfgType").val()) {
			$("#dpCfgTypeShow").val("映射");
		} else if ('3' == $("#dpCfgType").val()) {
			$("#dpCfgTypeShow").val("导出");
		}
		var s =$("#stepStartDay").val();
		if ('1' == s || '2' == s || '3' == s || '4' == s || '5' == s) {
			$("#stepStartDay").removeAttr("disabled");
		}else{
			$("#stepStartDay").attr("disabled","true");
		}
	});

	// 添加
	function addStep() {
		$("#dependencyStepList").append(
				$("#unDependencyStepList option:selected"));
	}
	// 移除
	function delStep() {
		$("#unDependencyStepList").append(
				$("#dependencyStepList option:selected"));
	}

	// 添加
	function addExclStep() {
		$("#excludeStepList").append($("#unExcludeStepList option:selected"));
	}
	// 移除
	function delExclStep() {
		$("#unExcludeStepList").append($("#excludeStepList option:selected"));
	}

	function updateStep() {
		$("select[name='dependencyStepList'] option").attr("selected", true);
		$("select[name='unDependencyStepList'] option").attr("selected", true);
		$("select[name='excludeStepList'] option").attr("selected", true);
		$("select[name='unExcludeStepList'] option").attr("selected", true);
		var form = document.getElementById("form");
		form.action = "${pageContext.request.contextPath}/BMG_STEP_Upt/SubmitStep";
		var msg = $("#confirmMsg1").val() + $("#update").val()
				+ $("#confirmMsg2").val();
		if (confirm(msg)) {
			form.submit();
		} else {
			return false;
		}
	};

	function queryCfg() {
		showSelCfg([ {
			"dpCfgType" : "param1",
			"dpCfgId" : "param2",
			"dpCfgName" : "param3"
		} ]);
		if ('1' == $("#dpCfgType").val()) {
			$("#dpCfgTypeShow").val("导入");
		} else if ('2' == $("#dpCfgType").val()) {
			$("#dpCfgTypeShow").val("映射");
		} else if ('3' == $("#dpCfgType").val()) {
			$("#dpCfgTypeShow").val("导出");
		}
	};
	function changeStepType(s){
		if ('1' == s || '2' == s || '3' == s || '4' == s || '5' == s) {
			$("#stepStartDay").removeAttr("disabled");
		}else{
			$("#stepStartDay").attr("disabled","true");
		}
	}
</script>

<div id="id_showMsg" style="display: none">
	<br /> <br />
	<div class="alert alert-error" id="errorMsg" style="display: none"></div>
	<div id="id_result">
		<t:messagePanel />
		<spring:hasBindErrors name="batchManageForm">
			<form:form commandName="batchManageForm">
				<div class="alert alert-error">
					<form:errors path="*" cssStyle="color:red"></form:errors>
				</div>
			</form:form>
		</spring:hasBindErrors>
	</div>
	<br />
</div>

<div class="page_title">
	<spring:message code="sysrunner.title.BatchManageStepUpt" />
</div>

<div class="row" style="margin-bottom: 40px;">
	<form:form id="form"
		action="${pageContext.request.contextPath}/bm01/03/stepModify"
		method="post" modelAttribute="batchManageForm" class="form-horizontal">
		<form:input path="batStepInfo.taskId" type="hidden" id="taskId" />
		<form:input path="batStepInfo.stepId" type="hidden" id="stepId" />
		<table class="tbl_search">
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="sysrunner.label.TaskName" /></td>
				<td><form:input id="taskName" path="batStepInfo.taskName"
						class=".input-large" readonly="true" /></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="sysrunner.label.StepName" /></td>
				<td><form:input id="stepName" path="batStepInfo.stepName"
						class=".input-large" /></td>
			</tr>
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="sysrunner.label.StepType" /></td>
				<td><form:select id="stepStartType"
						path="batStepInfo.stepStartType" onchange="changeStepType(this.value)">
						<form:option value=""></form:option>
						<form:options items="${BMG_STEP_TYPE}" />
					</form:select></td>
				<td class="label_td">&nbsp&nbsp&nbsp<spring:message
						code="sysrunner.label.StepStartDay" /></td>
				<td><form:input id="stepStartDay"
						path="batStepInfo.stepStartDay" /></td>

			</tr>
			<tr>
				<td class="label_td">&nbsp&nbsp&nbsp <spring:message
						code="sysrunner.label.StepSubNumber" /></td>
				<td><form:input id="subStepNum" path="batStepInfo.subStepNum"
						class=".input-large" /></td>
				<td class="label_td">&nbsp&nbsp&nbsp <spring:message
						code="sysrunner.label.StepPriority" /></td>
				<td><form:input id="priority" path="batStepInfo.priority"
						class=".input-large" /></td>
			</tr>
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="sysrunner.label.StepEnable" /></td>
				<td><form:select id="stepEnable" path="batStepInfo.stepEnable">
						<form:option value=""></form:option>
						<form:options items="${BMG_FLAG}" />
					</form:select></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="sysrunner.label.StepSkip" /></td>
				<td><form:select id="allowSkip" path="batStepInfo.allowSkip">
						<form:option value=""></form:option>
						<form:options items="${BMG_FLAG}" />
					</form:select></td>
			</tr>

			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="sysrunner.label.StepCommand" /></td>
				<td colspan="3"><form:input id="stepCommand"
						path="batStepInfo.stepCommand" class="input-xxlarge" /></td>
			</tr>

			<tr>
				<td class="label_td"><font color="red">*</font>
				<spring:message code="sysrunner.label.StepAutoSkip" /></td>
				<td colspan="3"><form:select id="autoSkip"
						path="batStepInfo.autoSkip">
						<form:option value=""></form:option>
						<form:options items="${BMG_FLAG}" />
					</form:select></td>
			</tr>

			<tr>
				<td class="label_td">&nbsp&nbsp&nbsp <spring:message
						code="sysrunner.label.CfgType" /></td>
				<td><form:input id="dpCfgType" path="batStepInfo.dpCfgType"
						class=".input-large" type="hidden" /> <input type="text"
					id="dpCfgTypeShow" readonly="readonly"></td>
				<td class="label_td">&nbsp&nbsp&nbsp <spring:message
						code="sysrunner.label.CfgName" /></td>
				<td><form:input id="dpCfgId" path="batStepInfo.dpCfgId"
						class=".input-large" type="hidden" /> <form:input id="dpCfgName"
						path="batStepInfo.dpCfgName" class=".input-large" readonly="true" />
					<input type="button" class="btn btn-small" onclick="queryCfg()"
					value="<spring:message code="button.label.Search"/>"></td>
			</tr>

			<tr>
				<td class="label_td" colspan="1"><spring:message
						code="sysrunner.label.StepDependencyList" /></td>
				<td colspan="3">
					<table class="tbl_search">
						<tr>
							<td align="left">
								<fieldset style="width: 98%; padding: 2px;">
									<table>
										<tr height="20px">
											<td align="left" valign="bottom">&nbsp;&nbsp;<spring:message
													code="sysrunner.label.StepUnDependency" /></td>
											<td>&nbsp;</td>
											<td align="left" valign="bottom">&nbsp;&nbsp;<spring:message
													code="sysrunner.label.StepDependency" /></td>
										</tr>
										<tr>
											<td><select multiple="multiple"
												name="unDependencyStepList" id="unDependencyStepList"
												style="width: 250px; margin: 2px;">
													<c:forEach var="dto"
														items="${batchManageForm.unDependencyStepListShow}"
														varStatus="i">
														<option value="${dto.stepId}">${dto.taskName}-${dto.stepName}</option>
													</c:forEach>
											</select></td>
											<td align="center"><input type="button" value="&gt&gt"
												class="btn" onclick="addStep()" /> <input type="button"
												value="&lt&lt" class="btn" onclick="delStep()" /></td>
											<td><select multiple="multiple"
												name="dependencyStepList" id="dependencyStepList"
												style="width: 250px; margin: 2px;">
													<c:forEach var="dto2"
														items="${batchManageForm.dependencyStepListShow}"
														varStatus="i">
														<option value="${dto2.stepId}">${dto2.taskName}-${dto2.stepName}</option>
													</c:forEach>
											</select></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td class="label_td" colspan="1"><spring:message
						code="sysrunner.label.StepExcludeList" /></td>
				<td colspan="3">
					<table class="tbl_search">
						<tr>
							<td align="left">
								<fieldset style="width: 98%; padding: 2px;">
									<table>
										<tr height="20px">
											<td align="left" valign="bottom">&nbsp;&nbsp;<spring:message
													code="sysrunner.label.StepUnExclude" /></td>
											<td>&nbsp;</td>
											<td align="left" valign="bottom">&nbsp;&nbsp;<spring:message
													code="sysrunner.label.StepExclude" /></td>
										</tr>
										<tr>
											<td><select multiple="multiple" name="unExcludeStepList"
												id="unExcludeStepList" style="width: 250px; margin: 2px;">
													<c:forEach var="dto"
														items="${batchManageForm.unExcludeStepListShow}"
														varStatus="i">
														<option value="${dto.stepId}">${dto.taskName}-${dto.stepName}</option>
													</c:forEach>
											</select></td>
											<td align="center"><input type="button" value="&gt&gt"
												class="btn" onclick="addExclStep()" /> <input type="button"
												value="&lt&lt" class="btn" onclick="delExclStep()" /></td>
											<td><select multiple="multiple" name="excludeStepList"
												id="excludeStepList" style="width: 250px; margin: 2px;">
													<c:forEach var="dto2"
														items="${batchManageForm.excludeStepListShow}"
														varStatus="i">
														<option value="${dto2.stepId}">${dto2.taskName}-${dto2.stepName}</option>
													</c:forEach>
											</select></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</div>



<div class="row" style="margin-bottom: 40px;">
	<div class="navbar navbar-fixed-bottom text-right" id="footer"
		style="margin-bottom: 0px; line-height: 30px; background-color: #eee; opacity: 0.9;">
		<input id="update" type="button" class="btn btn-primary"
			onclick="updateStep();"
			value="<spring:message code="button.lable.Submit"/>"> <input
			type="button" class="btn btn-primary"
			onclick="javascript:window.close();"
			value="<spring:message code="button.lable.close"/>">
	</div>
</div>