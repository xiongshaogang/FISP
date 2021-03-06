package com.synesoft.ftzmis.domain.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.fw.common.exception.BusinessException;
import org.terasoluna.fw.common.message.ResultMessage;
import org.terasoluna.fw.common.message.ResultMessages;


import com.synesoft.fisp.app.common.utils.DateUtil;
import com.synesoft.fisp.app.common.utils.StringUtil;
import com.synesoft.fisp.app.common.utils.TlrLogPrint;
import com.synesoft.fisp.domain.model.OrgInf;
import com.synesoft.fisp.domain.model.UserInf;
import com.synesoft.ftzmis.app.common.constants.CommonConst;
import com.synesoft.ftzmis.app.common.util.Validator;
import com.synesoft.ftzmis.domain.model.FtzActMstr;
import com.synesoft.ftzmis.domain.model.FtzInMsgCtl;
import com.synesoft.ftzmis.domain.model.FtzInTxnDtl;



@Service("ftz210206Service")
public class FTZ210206ServiceImp extends FTZInCommonServiceImp {
	private static final Logger log = LoggerFactory.getLogger(FTZ210206ServiceImp.class);
	
	/* (non-Javadoc)
	 * @see com.synesoft.ftzmis.domain.service.FTZInCommonService#addMsgLogic(com.synesoft.ftzmis.domain.model.FtzInMsgCtl)
	 */
	public FTZ210206ServiceImp() {
		super.funcId = CommonConst.FTZ_Add_210206;
	}
	
	protected void addMsgLogic(FtzInMsgCtl ftzInMsgCtl) {
		log.debug("FTZ210206ServiceImp.addMsgLogic() start ...");

		super.funcId = CommonConst.FTZ_Add_210206;
	}

	/* (non-Javadoc)
	 * @see com.synesoft.ftzmis.domain.service.FTZInCommonServiceImp#addTxnLogic(com.synesoft.ftzmis.domain.model.FtzInTxnDtl)
	 */
	protected void addTxnLogic(FtzInTxnDtl ftzInTxnDtl) {
		log.debug("FTZ210206ServiceImp.addTxnLogic() start ...");

		super.funcId = CommonConst.FTZ_Add_210206;
		
		ResultMessages messages = ResultMessages.error();

//		ftzInTxnDtl.setReportCode(CommonConst.MSG_NO_210206);
//		ftzInTxnDtl.setSubmitDate(DateUtil.getFormatDateRemoveSprit(ftzInTxnDtl.getSubmitDate()));
	//	ftzInTxnDtl.setExpirationDate(DateUtil.getFormatDateRemoveSprit(ftzInTxnDtl.getExpireDate()));
		
		String countryCode = ftzInTxnDtl.getCountryCode();
		//String districtCode = ftzInTxnDtl.getDistrictCode();
		if (true) {
//			
		}
	}

	/* (non-Javadoc)
	 * @see com.synesoft.ftzmis.domain.service.FTZInCommonServiceImp#updateTxnLogic(com.synesoft.ftzmis.domain.model.FtzInTxnDtl)
	 */
	protected void updateTxnLogic(FtzInTxnDtl ftzInTxnDtl) {
		log.debug("FTZ210206ServiceImp.updateTxnLogic() start ...");

		super.funcId = CommonConst.FTZ_Add_210206;
		
		ResultMessages messages = ResultMessages.error();

		String countryCode = ftzInTxnDtl.getCountryCode();
		//String districtCode = ftzInTxnDtl.getDistrictCode();
		if (true) {
//			if (!StringUtil.isNotTrimEmpty(districtCode)) {
//				log.error("[e.ftzmis.2103.0010] Upate TxnDtl information failure!"); 
//				messages.add("e.ftzmis.2103.0010");								
//				throw new BusinessException(messages);
//			}
		}
		
//		ftzInTxnDtl.setMakDatetime(DateUtil.getFormatDateTimeRemoveSpritAndColon(ftzInTxnDtl.getMakDatetime()));
//		ftzInTxnDtl.setChkDatetime(DateUtil.getFormatDateTimeRemoveSpritAndColon(ftzInTxnDtl.getChkDatetime()));
	}

	/* (non-Javadoc)
	 * @see com.synesoft.ftzmis.domain.service.FTZInCommonServiceImp#validateTxn(com.synesoft.ftzmis.domain.model.FtzInTxnDtl)
	 */
	protected void validateTxn(FtzInTxnDtl ftzInTxnDtl) {
		log.debug("FTZ210206ServiceImp.validateTxn() start ...");

		ResultMessages messages = ResultMessages.error();
		
		//出/入账标志不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getCdFlag())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0008");
			messages.addAll(msg);
		}
		
		// 记帐日期不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getTranDate())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0009");
			messages.addAll(msg);
		}
			
		//原始交易日期
		if(ftzInTxnDtl.getCdFlag().equals("3")||ftzInTxnDtl.getCdFlag().equals("4"))
		{
			if(!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getOrgTranDate()))
			{
				ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0009");
				messages.addAll(msg);
			}
			if(DateUtil.compareDateString(DateUtil.getFormatdateAddSplit(ftzInTxnDtl.getOrgTranDate()),DateUtil.getFormatdateAddSplit(ftzInTxnDtl.getTranDate())))
			{
				ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0016");
				messages.addAll(msg);
			}
		}
		
		// 金额不能为空
		if (ftzInTxnDtl.getAmount() == new BigDecimal(0)||ftzInTxnDtl.getAmount() ==null) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0010");
			messages.addAll(msg);
		}
		else{
			if (!Validator.CheckAmount(ftzInTxnDtl.getAmount())) {
				ResultMessage msg = ResultMessage
						.fromCode("e.ftzmis.210303.0013");
				messages.addAll(msg);
			}
		}
		
		
			
		// 国别代码不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getCountryCode())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0011");
			messages.addAll(msg);
		}
		
		// 国内地区码不能为空
//		if (!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getDisitrictCode())) {
//			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0012");
//			messages.addAll(msg);
//		}
		// 起息日不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getValueDate())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0013");
			messages.addAll(msg);
		}
		// 年化利率不能为空
		if (ftzInTxnDtl.getInterestRate() == new BigDecimal(0)||ftzInTxnDtl.getInterestRate()==null) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0014");
			messages.addAll(msg);
		}else{
			if (!Validator.CheckRate(ftzInTxnDtl.getInterestRate())) {
				ResultMessage msg = ResultMessage
						.fromCode("e.ftzmis.210101.0041");
				messages.addAll(msg);
			}
		}
		// 交易性质不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInTxnDtl.getTranType())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0015");
			messages.addAll(msg);
		}
		
		if (messages.isNotEmpty()) {
			throw new BusinessException(messages);
		}
	}

	/* (non-Javadoc)
	 * @see com.synesoft.ftzmis.domain.service.FTZInCommonServiceImp#validateMsg(com.synesoft.ftzmis.domain.model.FtzInMsgCtl)
	 */
	protected void validateMsg(FtzInMsgCtl ftzInMsgCtl) {
		log.debug("FTZ210206ServiceImp.validateTxn() start ...");

		ResultMessages messages = ResultMessages.error();
		
		// 申报日期不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInMsgCtl.getSubmitDate())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0001");
			messages.addAll(msg);
		}
		
		// 账号不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInMsgCtl.getAccountNo())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0002");
			messages.addAll(msg);
		}
		
		// 户名不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInMsgCtl.getAccountName())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0003");
			messages.addAll(msg);
		}
		
		// 货币不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInMsgCtl.getCurrency())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0004");
			messages.addAll(msg);
		}
		
		// 日终余额不能为空
		if (ftzInMsgCtl.getBalance() == new BigDecimal(0)||ftzInMsgCtl.getBalance()==null) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0005");
			messages.addAll(msg);
		}
		else
		{
			if (!Validator.CheckAmount(ftzInMsgCtl.getBalance())) {
				ResultMessage msg = ResultMessage
						.fromCode("e.ftzmis.210303.0013");
				messages.addAll(msg);
			}
		}
		
		
		// 资产负债指标代码不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInMsgCtl.getBalanceCode())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0006");
			messages.addAll(msg);
		}
		
		// 所属机构代码不能为空
		if (!StringUtil.isNotTrimEmpty(ftzInMsgCtl.getAccOrgCode())) {
			ResultMessage msg = ResultMessage.fromCode("e.ftzmis.210206.0007");
			messages.addAll(msg);
		}
		
		
		
		if (messages.isNotEmpty()) {
			throw new BusinessException(messages);
		}
	}

	

	
}
