<script type="text/javascript">
	$(function() {
		if ($("#procType").val() == '3') {
			$("#express").removeAttr("disabled");
			$("#srcTable").removeAttr("disabled");
			$("#destTable").removeAttr("disabled");
			$("#customize_flag").removeAttr("disabled");
			if($("#customize_flag").val()=='1'){
				$("#procCfg").removeAttr("readonly");
			}else{
				$("#procCfg").attr("readonly", "readonly");
			}
			document.getElementById('tableTR').style.display = 'table-row';
			document.getElementById('Customize_div').style.display = 'block';
		} else {
			$("#express").attr("disabled", "disabled");
			$("#srcTable").attr("disabled", "disabled");
			$("#destTable").attr("disabled", "disabled");
			$("#customize_flag").attr("disabled", "disabled");
			document.getElementById('tableTR').style.display = 'none';
			document.getElementById('Customize_div').style.display = 'none';
		}
		var msg = "${resultMessages.type}";
		if (msg && msg == "SUCCESS") {
			$("input").prop("disabled", true);
			$("select").prop("disabled", true);
			$("textarea").prop("disabled", true);
		}
	});
	//input button
	function input() {
		var form = document.getElementById("form");
		form.action = "${pageContext.request.contextPath}/DP_Mpp_Add/SubmitCfg";
		var msg = $("#confirmMsg1").val() + $("#confirmBtn").val()
				+ $("#confirmMsg2").val();
		if (confirm(msg)) {
			form.submit();
		} else {
			return false;
		}
	};
	function procTypeChange(s) {
		if (s == '3') {
			$("#srcTable").removeAttr("disabled");
			$("#destTable").removeAttr("disabled");
			var expressButton = document.getElementById("express");
			expressButton.onclick = mppExpress;
			$("#express").attr("disabled", "disabled");
			$("#customize_flag").attr("disabled", "disabled");
			$("#express").val(
					'<spring:message
					code="dp.lable.Express" />');
			if ('' != $("#srcTable").val()) {
				$("#express").removeAttr("disabled");
				$("#customize_flag").removeAttr("disabled");
			}
			if(document.getElementById("customize_flag").checked==true){
				$("#procCfg").removeAttr("readonly");
			}else{
				$("#procCfg").attr("readonly", "readonly");
			}
			document.getElementById('tableTR').style.display = 'table-row';
			document.getElementById('procTypeDescTR').style.display = 'none';
			document.getElementById('procClassDescTR').style.display = 'none';
			document.getElementById('Customize_div').style.display = 'block';
		} else {
			if (s == '2') {
				var expressButton = document.getElementById("express");
				expressButton.onclick = SQLEdit;
				$("#express").val(
						'<spring:message
						code="dp.lable.MppSqlEdit" />');
				$("#express").removeAttr("disabled");
				document.getElementById('procTypeDescTR').style.display = 'table-row';
				document.getElementById('procClassDescTR').style.display = 'none';
			} else {
				var expressButton = document.getElementById("express");
				expressButton.onclick = mppExpress;
				$("#express").val(
						'<spring:message
						code="dp.lable.Express" />');
				$("#express").attr("disabled", "disabled");
			}
			if (s == '4') {
				document.getElementById('procClassDescTR').style.display = 'table-row';
				document.getElementById('procTypeDescTR').style.display = 'none';
			}
			$("#customize_flag").attr("disabled", "disabled");
			$("#srcTable").attr("disabled", "disabled");
			$("#destTable").attr("disabled", "disabled");
			$("#procCfg").removeAttr("readonly");
			document.getElementById('tableTR').style.display = 'none';
			document.getElementById('Customize_div').style.display = 'none';
		}
	};

	function queryOrg() {
		showSelOrg([ {
			"branchId" : "param1"
		} ]);
	};
	function querydestTable() {
		showSelTable([ {
			"destTable" : "param2"
		} ]);
	};
	function querysrcTable() {
		showSelTable([ {
			"srcTable" : "param2"
		} ]);
		if ('' != $("#srcTable").val()) {
			$("#express").removeAttr("disabled");
			$("#customize_flag").removeAttr("disabled");
		}
	};
	function SQLEdit() {
		var procCfg = $("#procCfg").val();
		var srcSQL = encrytorInf(procCfg);
		var rtnStr = window
				.showModalDialog(
						'${pageContext.request.contextPath}/DP_Mpp_Add/SQLEditInit?srcSQL='
								+ srcSQL,
						window,
						'dialogHeight:500px; dialogWidth: 1024px;edge: Raised; center: Yes; help: no; resizable: Yes; status: no;');
		if (typeof (rtnStr) == 'undefined') {
			rtnStr = window.ReturnValue;
		}
		if (rtnStr) {
			$("#procCfg").val(rtnStr);
		}
	};

	function mppExpress() {
		var srcTable = $("#srcTable").val();
		var procCfg = $("#procCfg").val();
		var procCfg_key = $("#procCfg_key").val();
		var branchId = $("#branchId").val();
		var rtnStr = window
				.showModalDialog(
						'${pageContext.request.contextPath}/Dp_Exp/Init?srcTable='
								+ srcTable + '&oldExpress=' + procCfg +'&oldExpress_key='+procCfg_key+'&cfgId='+'&branchId=' +'&dpType=2',
						window,
						'dialogHeight:500px; dialogWidth: 1024px;edge: Raised; center: Yes; help: no; resizable: Yes; status: no;');
		if (typeof (rtnStr) == 'undefined') {
			rtnStr = window.ReturnValue;
		}
		if (rtnStr) {
			$("#procCfg_key").val(rtnStr.expressKey);
			$("#procCfg").val(rtnStr.expressVal);
		}
	};
	function checkSrcVal() {
		var srcTable = $("#srcTable").val();
		if ('' == srcTable) {
			$("#express").attr("disabled", "disabled");
			$("#customize_flag").attr("disabled", "disabled");
		} else {
			$("#express").removeAttr("disabled");
			$("#customize_flag").removeAttr("disabled");
		}

	};

	function getOrgInf(obj) {
		orgid = $(obj).val();
		var orgExist = checkOrgInf(obj);
		if (false == orgExist) {
			var msg = $("#alertMsg1").val();
			document.getElementById("errorMsg").style.display = "block";
			document.getElementById("errorMsg").innerHTML = msg;
			showMsg("id_showMsg");
			$(obj).val("");
		}
	};

	function changeCustomize() {
		var customize_flag = document.getElementById("customize_flag").checked;
		if (null == customize_flag || customize_flag == false) {
			var procCfg = $("#procCfg").val();
			if ('' != procCfg) {
				var msg = "编辑表达式时，原自定义配置信息将清空！";
				if (confirm(msg)) {
					$("#procCfg").val("");
					$("#express").removeAttr("disabled");
					$("#procCfg").attr("readonly", "readonly");
				} else {
					document.getElementById("customize_flag").checked = true;
				}
			}else{
				$("#express").removeAttr("disabled");
				$("#procCfg").attr("readonly", "readonly");
			}
		} else {
			var procCfg = $("#procCfg").val();
			if ('' != procCfg) {
				var msg = "自定义时，原表达式配置信息将清空！";
				if (confirm(msg)) {
					$("#procCfg").val("");
					$("#express").attr("disabled", "disabled");
					$("#procCfg").removeAttr("readonly");
				} else {
					document.getElementById("customize_flag").checked = false;
				}
			}else{
				$("#express").attr("disabled", "disabled");
				$("#procCfg").removeAttr("readonly");
			}
		}
	};
</script>

<div id="id_showMsg" style="display: none">
	<br /> <br />
	<div class="alert alert-error" id="errorMsg" style="display: none"></div>
	<div id="id_result">
		<t:messagesPanel />
		<t:messagePanel messagesAttributeName="errmsg" messagesType="error" />
		<spring:hasBindErrors name="DP_Mpp_AddForm">
			<form:form commandName="DP_Mpp_AddForm">
				<div class="alert alert-error">
					<form:errors path="*" cssStyle="color:red"></form:errors>
				</div>
			</form:form>
		</spring:hasBindErrors>
	</div>
	<br />
</div>

<div class="page_title">
	<spring:message code="dp.title.MapAdd" />
</div>

<div class="row">
	<form:form id="form" method="post"
		action="${pageContext.request.contextPath}/DP_Mpp_Add/SubmitCfg"
		modelAttribute="DP_Mpp_AddForm" class="form-horizontal">
		<table class="tbl_search">
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="if.label.PROJID" /></td>
				<td><form:select id="projId" path="dpMppCfg.projId">
						<form:options items="${DP_0021}" />
					</form:select></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="if.label.BRANCHID" /></td>
				<td><form:input id="branchId" path="dpMppCfg.branchId"
						type="text" class="input-large" onblur="getOrgInf(this)"
						onkeyup="numberStringFormat(this);"
						onbeforepaste="numberStringFormatCopy(this);" /> <input
					type="button" class="btn btn-small" onclick="queryOrg()"
					value="<spring:message code="button.label.Search"/>"></td>

			</tr>
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="if.label.BATCHNO" /></td>
				<td><form:input id="batchNo" path="dpMppCfg.batchNo"
						type="text" class="input-large" maxLength="10"
						onkeyup="numberFormat(this);" /></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="if.label.SEQNO" /></td>
				<td><form:input id="seqNo" path="dpMppCfg.seqNo" type="text"
						class="input-large" maxLength="3" onkeyup="numberFormat(this);" /></td>

			</tr>
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="de.label.procType" /></td>
				<td><form:select id="procType" path="dpMppCfg.procType"
						onchange="procTypeChange(this.value);">
						<form:options items="${DP_0020}" />
					</form:select></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="dp.lable.MppName" /></td>
				<td><form:input id="mppName" path="dpMppCfg.mppName"
						type="text" class="input-large" /></td>
			</tr>
			<tr id="procTypeDescTR" style="display: none;">
				<td></td>
				<td><font color="red" style="font-size: 12px;">(<spring:message
							code="fisp.notice.mpp.notice1" />)
				</font></td>
				<td colspan="2"></td>

			</tr>
			<tr>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="dp.lable.DupProcType" /></td>
				<td><form:select id="dupProcType" path="dpMppCfg.dupProcType">
						<form:options items="${DP_0022}" />
					</form:select></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="dp.lable.ErrProcType" /></td>
				<td><form:select id="errProcType" path="dpMppCfg.errProcType">
						<form:options items="${DP_0023}" />
					</form:select></td>
			</tr>
			<tr id="tableTR" style="display: none;">
				<td class="label_td"><font color="red">*</font> <spring:message
						code="de.label.srcTable" /></td>
				<td><form:input id="srcTable" path="dpMppCfg.srcTable"
						type="text" class="input-large" onkeyup="checkSrcVal()"
						readonly="true" /> <input type="button" class="btn btn-small"
					onclick="querysrcTable()"
					value="<spring:message code="button.label.Search"/>"></td>
				<td class="label_td"><font color="red">*</font> <spring:message
						code="de.label.destTable" /></td>
				<td><form:input id="destTable" path="dpMppCfg.destTable"
						type="text" class="input-large" readonly="true" /> <input
					type="button" class="btn btn-small" onclick="querydestTable()"
					value="<spring:message code="button.label.Search"/>"></td>

			</tr>
			<tr>
				<td class="label_td"><spring:message code="de.label.procCfg" /></td>
				<td colspan="3">
				<form:input id="procCfg_key" path="dpMppCfg.procCfg_key" type="hidden" />
				<form:textarea id="procCfg" rows="2"
						path="dpMppCfg.procCfg"
						style="overflow-x: hidden; overflow-y: auto; width: 75%; resize: none;" />
					<input type="button" class="btn btn-primary" id="express"
					onclick="mppExpress()" disabled="disabled"
					value="<spring:message
						code="dp.lable.Express" />"><div id="Customize_div">
						<form:checkbox path="customize_flag" id="customize_flag" value="1"
							cssStyle="margin-right:10px;" onclick="changeCustomize()" disabled="disabled"/>
						<spring:message code="dp.lable.Customize" />
					</div></td>
			</tr>
			<tr id="procClassDescTR" style="display: none;">
				<td></td>
				<td><font color="red" style="font-size: 12px;">(<spring:message
							code="fisp.notice.mpp.notice2" />)
				</font></td>
				<td colspan="2"></td>

			</tr>
			<tr>
				<td class="label_td"><spring:message code="de.label.comments" /></td>
				<td colspan="3"><form:textarea path="dpMppCfg.comments"
						style="overflow-x: hidden; overflow-y: auto; width: 75%; height: 100px; resize: none;" />
				</td>
			</tr>
		</table>
	</form:form>
</div>

<div class="row">
	<div class="navbar navbar-fixed-bottom text-right" id="footer"
		style="margin-bottom: 0px; line-height: 30px; background-color: #eee; opacity: 0.9;">
		<input id="confirmBtn" class="btn btn-primary" type="button"
			onclick="input()"
			value="<spring:message code="button.lable.Submit"/>">
		<button class="btn btn-primary" onclick="javascript:window.close();">
			<spring:message code="button.lable.close" />
		</button>
	</div>
</div>
