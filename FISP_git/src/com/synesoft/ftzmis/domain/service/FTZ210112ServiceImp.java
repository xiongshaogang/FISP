package com.synesoft.ftzmis.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.synesoft.fisp.app.common.constants.ContextConst;
import com.synesoft.fisp.app.common.utils.StringUtil;
import com.synesoft.fisp.app.common.utils.TlrLogPrint;
import com.synesoft.fisp.domain.model.OrgInf;
import com.synesoft.fisp.domain.model.UserInf;
import com.synesoft.ftzmis.app.common.constants.CommonConst;
import com.synesoft.ftzmis.app.common.msgproc.FtzMsgProcService;
import com.synesoft.ftzmis.app.common.util.DateUtil;
import com.synesoft.ftzmis.domain.model.FtzActMstr;
import com.synesoft.ftzmis.domain.model.FtzBankCode;
import com.synesoft.ftzmis.domain.model.FtzInMsgCtl;
import com.synesoft.ftzmis.domain.model.FtzInTxnDtl;
import com.synesoft.ftzmis.domain.repository.FTZ210112Repository;
import com.synesoft.ftzmis.domain.repository.FtzInTxnDtlRepository;

@Service
public class FTZ210112ServiceImp implements FTZ210112Service {
	protected static String funcId ="FTZ_Add_210112";
	@Override
	public FtzInMsgCtl queryFtzInMsgCtl(FtzInMsgCtl ftzInMsgCtl) {
		return ftz210112Repos.queryFtzInMsgCtl(ftzInMsgCtl);
	}

	@Override
	public Page<FtzInMsgCtl> queryFtzInMsgCtlPageInput(Pageable pageable,
			FtzInMsgCtl ftzInMsgCtl) {
		return ftz210112Repos.queryFtzInMsgCtlPageInput(pageable, ftzInMsgCtl);
	}

	@Override
	public Page<FtzInMsgCtl> queryFtzInMsgCtlPage(Pageable pageable,
			FtzInMsgCtl ftzInMsgCtl) {
		return ftz210112Repos.queryFtzInMsgCtlPage(pageable, ftzInMsgCtl);
	}

	@Override
	public Page<FtzInTxnDtl> queryFtzInTxnDtlPage(Pageable pageable,
			FtzInTxnDtl ftzInTxnDtl) {
		return ftz210112Repos.queryFtzInTxnDtlPage(pageable, ftzInTxnDtl);
	}

	@Override
	public FtzInTxnDtl queryFtzInTxnDtl(FtzInTxnDtl ftzInTxnDtl) {
		return ftz210112Repos.queryFtzInTxnDtl(ftzInTxnDtl);
	}

	@Override
	@Transactional
	public int deleteFtzInMsgCtl(FtzInMsgCtl ftzInMsgCtl) {
		FtzInTxnDtl ftzInTxnDtl = new FtzInTxnDtl();
		ftzInTxnDtl.setMsgId(ftzInMsgCtl.getMsgId());
		ftz210112Repos.deleteFtzInTxnDtls(ftzInTxnDtl);

		FtzInMsgCtl query_FtzInMsgCtl = new FtzInMsgCtl();
		query_FtzInMsgCtl.setMsgId(ftzInMsgCtl.getMsgId());
		FtzInMsgCtl ftzMsgCtl_tmp = this.queryFtzInMsgCtl(query_FtzInMsgCtl);
		
		BizLog(CommonConst.DATA_LOG_OPERTYPE_DELETE,ftzMsgCtl_tmp.toString(), "");
		int i = ftz210112Repos.deleteFtzInMsgCtl(ftzInMsgCtl);
		return i;
	}

	@Override
	@Transactional
	public int insertFtzInMsgCtlAndTxnDtl(FtzInMsgCtl ftzInMsgCtl,FtzInTxnDtl ftzInTxnDtl) {
		//插入交易明细
		BizLog(CommonConst.DATA_LOG_OPERTYPE_ADD,"",ftzInTxnDtl.toString());
		int retTxnDtl = ftz210112Repos.insertFtzInTxnDtl(ftzInTxnDtl);
		if(retTxnDtl > 0){
			//插入批量明细
			ftzInMsgCtl.setTotalCount(1);
			BizLog(CommonConst.DATA_LOG_OPERTYPE_ADD,"", ftzInMsgCtl.toString());
			return ftz210112Repos.insertFtzInMsgCtl(ftzInMsgCtl);
		}
		return retTxnDtl;
	}

	@Override
	@Transactional
	public int updateFtzInMsgCtlAndTxnDtl(FtzInMsgCtl ftzInMsgCtl,FtzInTxnDtl ftzInTxnDtl) {
		//修改交易明细
		FtzInTxnDtl query_FtzInTxnDtl = new FtzInTxnDtl();
		query_FtzInTxnDtl.setMsgId(ftzInTxnDtl.getMsgId());
		query_FtzInTxnDtl.setSeqNo(ftzInTxnDtl.getSeqNo());
		FtzInTxnDtl ftzInTxnDtl_tmp = this.queryFtzInTxnDtl(query_FtzInTxnDtl);
		int ret = ftz210112Repos.updateFtzInTxnDtlSelective(ftzInTxnDtl);
		if(ret > 0){
			FtzInMsgCtl query_FtzInMsgCtl = new FtzInMsgCtl();
			query_FtzInMsgCtl.setMsgId(ftzInMsgCtl.getMsgId());
			FtzInMsgCtl ftzMsgCtl_tmp = this.queryFtzInMsgCtl(query_FtzInMsgCtl);
			BizLog(CommonConst.DATA_LOG_OPERTYPE_MODIFY,ftzMsgCtl_tmp.toString(), ftzInMsgCtl.toString());
			return ftz210112Repos.updateFtzInMsgCtl(ftzInMsgCtl);
		}
		BizLog(CommonConst.DATA_LOG_OPERTYPE_MODIFY,ftzInTxnDtl_tmp.toString(),ftzInTxnDtl.toString());
		return ret;
	}
	
	@Override
	@Transactional
	public int updateFtzInMsgForAudit(FtzInMsgCtl ftzInMsgCtl,
			FtzInTxnDtl ftzInTxnDtl) {
		// 修改交易明细
		FtzInTxnDtl query_FtzInTxnDtl = new FtzInTxnDtl();
		query_FtzInTxnDtl.setMsgId(ftzInTxnDtl.getMsgId());
		query_FtzInTxnDtl.setSeqNo(ftzInTxnDtl.getSeqNo());
		FtzInTxnDtl ftzInTxnDtl_tmp = this.queryFtzInTxnDtl(query_FtzInTxnDtl);
		int ret = ftz210112Repos.updateFtzInTxnDtlSelective(ftzInTxnDtl);
		if (ret > 0) {
			FtzInMsgCtl query_FtzInMsgCtl = new FtzInMsgCtl();
			query_FtzInMsgCtl.setMsgId(ftzInMsgCtl.getMsgId());
			FtzInMsgCtl ftzMsgCtl_tmp = this
					.queryFtzInMsgCtl(query_FtzInMsgCtl);
			ret = ftz210112Repos.updateFtzInMsgCtl(ftzInMsgCtl);
			BizLog(CommonConst.DATA_LOG_OPERTYPE_MODIFY,
					ftzInTxnDtl_tmp.toString(), ftzInTxnDtl.toString());
			BizLog(CommonConst.DATA_LOG_OPERTYPE_MODIFY,
					ftzMsgCtl_tmp.toString(), ftzInMsgCtl.toString());
			// 提交报文信息
			ftzMsgProcService.submitMsg(ftzInMsgCtl.getMsgNo(),
					ftzInMsgCtl.getMsgId());
			return ret;
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synesoft.ftzmis.domain.service.FTZ210101Service#queryFtzActMstrs(
	 * com.synesoft.ftzmis.domain.model.FtzActMstr)
	 */
	@Override
	public List<FtzActMstr> queryFtzActMstrs(FtzActMstr ftzActMstr) {
		return ftz210112Repos.queryFtzActMstrs(ftzActMstr);
	}

	@Override
	public List<FtzInTxnDtl> queryFtzInTxnDtlList(FtzInTxnDtl ftzInTxnDtl) {
		return ftz210112Repos.queryFtzInTxnDtlList(ftzInTxnDtl);
	}


	@Override
	@Transactional
	public int updateFtzInMsgCtlForSubmit(FtzInMsgCtl ftzInMsgCtl) {
		ftzInMsgCtl.setMsgStatus(CommonConst.FTZ_MSG_STATUS_INPUT_COMPLETED);
		FtzInTxnDtl query_ftzInTxnDtl = new FtzInTxnDtl();
		query_ftzInTxnDtl.setMsgId(ftzInMsgCtl.getMsgId());
		query_ftzInTxnDtl.setSeqNo(StringUtil.addZeroForNum("1", 6));
		FtzInTxnDtl update_ftzInTxnDtl = queryFtzInTxnDtl(query_ftzInTxnDtl);
		update_ftzInTxnDtl.setChkStatus(CommonConst.FTZ_MSG_STATUS_INPUT_COMPLETED);
		return this.updateFtzInMsgCtlAndTxnDtl(ftzInMsgCtl,update_ftzInTxnDtl);
	}
	
	@Override
	public String queryTxnDtlMaxSeqNo(FtzInTxnDtl ftzInTxnDtl) {
		return ftzInTxnDtlRepository.queryTxnDtlMaxSeqNo(ftzInTxnDtl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synesoft.ftzmis.domain.service.FTZ210101Service#queryFtzBankCode(
	 * com.synesoft.ftzmis.domain.model.FtzBankCode)
	 */
	@Override
	public FtzBankCode queryFtzBankCode(FtzBankCode ftzBankCode) {
		return ftz210112Repos.queryFtzBankCode(ftzBankCode);
	}
	
	private void BizLog(String operType, String beforeData, String afterData) {
		OrgInf orgInfo = ContextConst.getOrgInfByUser();
		UserInf userInfo = ContextConst.getCurrentUser();
		TlrLogPrint.tlrBizLogPrint(funcId, orgInfo.getOrgid(), userInfo.getUserid(), userInfo.getUsername(), operType, 
				DateUtil.getNowInputDate(), DateUtil.getNowInputTime(), beforeData, afterData);
	}

	@Autowired
	protected FTZ210112Repository ftz210112Repos;
	@Autowired
	protected FtzInTxnDtlRepository ftzInTxnDtlRepository;
	@Autowired
	private FtzMsgProcService ftzMsgProcService;
}
