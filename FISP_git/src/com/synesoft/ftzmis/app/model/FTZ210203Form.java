package com.synesoft.ftzmis.app.model;


import com.synesoft.ftzmis.domain.model.FtzInMsgCtl;
import com.synesoft.ftzmis.domain.model.FtzInTxnDtl;

public class FTZ210203Form {

	public static interface FTZ210203FormAddDtl {
	}

	public static interface FTZ210203FormAddDtlDtl {
	}

	private FtzInMsgCtl ftzInMsgCtl;

	private FtzInTxnDtl ftzInTxnDtl;

	private String query_branchId = "";

	private String query_submitDate_start = "";

	private String query_submitDate_end = "";

	private String query_msgId = "";

	private String query_msgStatus = "";

	private String query_accountNo = "";

	private String query_subAccountNo = "";

	private String query_accountName = "";

	private String query_msgNo = "";

	private String query_currency = "";

	private String selected_msgId = "";

	private String selected_seqNo;

	private String selected_msgNo = "";

	private String unAuthFlag = "";

	private String authStat = "";

	private String authFinishFlag = "";

	private String father_makTime = "";

	private String father_chkTime = "";

	private String input_flag = "";

	private String input_Dtl_flag = "";

	public FtzInMsgCtl getFtzInMsgCtl() {
		return ftzInMsgCtl;
	}

	public void setFtzInMsgCtl(FtzInMsgCtl ftzInMsgCtl) {
		this.ftzInMsgCtl = ftzInMsgCtl;
	}

	public String getQuery_branchId() {
		return query_branchId;
	}

	public void setQuery_branchId(String query_branchId) {
		this.query_branchId = query_branchId;
	}

	public String getQuery_submitDate_start() {
		return query_submitDate_start;
	}

	public void setQuery_submitDate_start(String query_submitDate_start) {
		this.query_submitDate_start = query_submitDate_start;
	}

	public String getQuery_submitDate_end() {
		return query_submitDate_end;
	}

	public void setQuery_submitDate_end(String query_submitDate_end) {
		this.query_submitDate_end = query_submitDate_end;
	}

	public String getQuery_msgId() {
		return query_msgId;
	}

	public void setQuery_msgId(String query_msgId) {
		this.query_msgId = query_msgId;
	}

	public String getQuery_msgStatus() {
		return query_msgStatus;
	}

	public void setQuery_msgStatus(String query_msgStatus) {
		this.query_msgStatus = query_msgStatus;
	}

	public String getQuery_accountNo() {
		return query_accountNo;
	}

	public void setQuery_accountNo(String query_accountNo) {
		this.query_accountNo = query_accountNo;
	}

	public String getQuery_subAccountNo() {
		return query_subAccountNo;
	}

	public void setQuery_subAccountNo(String query_subAccountNo) {
		this.query_subAccountNo = query_subAccountNo;
	}

	public String getQuery_accountName() {
		return query_accountName;
	}

	public void setQuery_accountName(String query_accountName) {
		this.query_accountName = query_accountName;
	}

	public String getSelected_msgId() {
		return selected_msgId;
	}

	public void setSelected_msgId(String selected_msgId) {
		this.selected_msgId = selected_msgId;
	}

	public String getSelected_seqNo() {
		return selected_seqNo;
	}

	public void setSelected_seqNo(String selected_seqNo) {
		this.selected_seqNo = selected_seqNo;
	}

	public FtzInTxnDtl getFtzInTxnDtl() {
		return ftzInTxnDtl;
	}

	public void setFtzInTxnDtl(FtzInTxnDtl ftzInTxnDtl) {
		this.ftzInTxnDtl = ftzInTxnDtl;
	}

	public String getUnAuthFlag() {
		return unAuthFlag;
	}

	public void setUnAuthFlag(String unAuthFlag) {
		this.unAuthFlag = unAuthFlag;
	}

	public String getAuthStat() {
		return authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}

	public String getAuthFinishFlag() {
		return authFinishFlag;
	}

	public void setAuthFinishFlag(String authFinishFlag) {
		this.authFinishFlag = authFinishFlag;
	}

	public String getQuery_msgNo() {
		return query_msgNo;
	}

	public void setQuery_msgNo(String query_msgNo) {
		this.query_msgNo = query_msgNo;
	}

	public String getSelected_msgNo() {
		return selected_msgNo;
	}

	public void setSelected_msgNo(String selected_msgNo) {
		this.selected_msgNo = selected_msgNo;
	}

	public String getFather_makTime() {
		return father_makTime;
	}

	public void setFather_makTime(String father_makTime) {
		this.father_makTime = father_makTime;
	}

	public String getFather_chkTime() {
		return father_chkTime;
	}

	public void setFather_chkTime(String father_chkTime) {
		this.father_chkTime = father_chkTime;
	}

	public String getQuery_currency() {
		return query_currency;
	}

	public void setQuery_currency(String query_currency) {
		this.query_currency = query_currency;
	}

	public String getInput_flag() {
		return input_flag;
	}

	public void setInput_flag(String input_flag) {
		this.input_flag = input_flag;
	}

	public String getInput_Dtl_flag() {
		return input_Dtl_flag;
	}

	public void setInput_Dtl_flag(String input_Dtl_flag) {
		this.input_Dtl_flag = input_Dtl_flag;
	}

}
