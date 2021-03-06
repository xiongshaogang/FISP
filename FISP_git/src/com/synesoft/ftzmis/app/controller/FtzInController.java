package com.synesoft.ftzmis.app.controller;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.terasoluna.fw.common.message.ResultMessages;

import com.synesoft.ftzmis.app.common.constants.CommonConst;
import com.synesoft.ftzmis.app.common.msgproc.FtzMsgProcService;
import com.synesoft.ftzmis.app.common.util.DateUtil;
import com.synesoft.ftzmis.app.model.FTZINForm;
import com.synesoft.ftzmis.domain.model.FtzInMsgCtl;
import com.synesoft.ftzmis.domain.model.FtzInTxnDtl;
import com.synesoft.ftzmis.domain.service.FTZ210101Service;
import com.synesoft.ftzmis.domain.service.FTZInCommonService;

/**
 * @author 李峰
 * @date 2013-12-31 下午3:16:23
 * @version 1.0
 * @description 
 * @system FTZMIS
 * @company 上海恩梯梯数据晋恒软件有限公司
 */
@Controller
@RequestMapping("FTZ2102")
public class FtzInController {

	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FTZ210101Service ftz210101Service;
	
	@RequestMapping("Qry")
	public String query(Model model, FTZINForm form,
			@PageableDefaults Pageable pageable){
		// trans form to queryObject
		FtzInMsgCtl query_FtzInMsgCtl = new FtzInMsgCtl();
		query_FtzInMsgCtl.setMsgId(form.getQuery_msgId());
		query_FtzInMsgCtl.setBranchId(form.getQuery_branchId().trim());
		query_FtzInMsgCtl.setRsv1(form.getQuery_workDate_start());
		query_FtzInMsgCtl.setRsv2(form.getQuery_workDate_end());
		query_FtzInMsgCtl.setMsgStatus(form.getQuery_msgStatus());
		query_FtzInMsgCtl.setMsgNo(form.getQuery_msgNo());
		
		//Query
		// query DpMppCfg page list
		Page<FtzInMsgCtl> page = ftz210101Service.queryFtzInMsgCtlPage(pageable,
				query_FtzInMsgCtl);

		if (page.getContent().size() > 0) {
			List<FtzInMsgCtl> ftzInMsgCtls = page.getContent();
			for (FtzInMsgCtl ftzInMsgCtl : ftzInMsgCtls) {
				ftzInMsgCtl.setSubmitDate(DateUtil
						.getFormatDateAddSprit(ftzInMsgCtl.getSubmitDate()));
			}
			model.addAttribute("page", page);
		} else {
			model.addAttribute(ResultMessages.info().add("w.sm.0001"));
			logger.info("表外查询结束...");
			return "ftzmis/FTZIn_Qry";
		}
				
		return "ftzmis/FTZIn_Qry";
	}
	
	@RequestMapping("AuthQry")
	public String authQuery(Model model, FTZINForm form,
			@PageableDefaults Pageable pageable){
		// trans form to queryObject
	
		
		FtzInMsgCtl query_FtzInMsgCtl = new FtzInMsgCtl();
		query_FtzInMsgCtl.setMsgId(form.getQuery_msgId());
		query_FtzInMsgCtl.setBranchId(form.getQuery_branchId().trim());
		query_FtzInMsgCtl.setRsv1(DateUtil
				.getFormatDateRemoveSprit(form.getQuery_workDate_start()));
		query_FtzInMsgCtl.setRsv2(DateUtil
				.getFormatDateRemoveSprit(form.getQuery_workDate_end()));
		query_FtzInMsgCtl.setMsgStatuss(new String[] {
				CommonConst.FTZ_MSG_STATUS_INPUT_COMPLETED,
				CommonConst.FTZ_MSG_STATUS_INPUTING,
				CommonConst.FTZ_MSG_STATUS_AUTH_FAIL});
		query_FtzInMsgCtl.setMsgNo(form.getQuery_msgNo());
		
		query_FtzInMsgCtl.setMsgNos(new String[] { CommonConst.MSG_NO_210201,
				CommonConst.MSG_NO_210202, CommonConst.MSG_NO_210203,
				CommonConst.MSG_NO_210204, CommonConst.MSG_NO_210205,
				CommonConst.MSG_NO_210206, CommonConst.MSG_NO_210207,
				CommonConst.MSG_NO_210208, CommonConst.MSG_NO_210209,
				CommonConst.MSG_NO_210210, CommonConst.MSG_NO_210211,
				CommonConst.MSG_NO_210212 });
		
		//Query
		// query DpMppCfg page list
		Page<FtzInMsgCtl> page = ftz210101Service.queryFtzInMsgCtlPage(pageable,
				query_FtzInMsgCtl);

		if (page.getContent().size() > 0) {
			List<FtzInMsgCtl> ftzInMsgCtls = page.getContent();
			for (FtzInMsgCtl ftzInMsgCtl : ftzInMsgCtls) {
				ftzInMsgCtl.setSubmitDate(DateUtil
						.getFormatDateAddSprit(ftzInMsgCtl.getSubmitDate()));
				
				FtzInTxnDtl query_FtzInTxnDtl = new FtzInTxnDtl();
				query_FtzInTxnDtl.setMsgId(ftzInMsgCtl.getMsgId());
				List<FtzInTxnDtl> ftzInTxnDtls = ftz210101Serv
						.queryFtzInTxnDtlList(query_FtzInTxnDtl);
				int count = 0;
				if (null == ftzInTxnDtls || ftzInTxnDtls.size() < 1) {
					ftzInMsgCtl.setRsv1(ftzInMsgCtl.getTotalCount() + "");
				} else {
					for (FtzInTxnDtl ftzInTxnDtl : ftzInTxnDtls) {
						if (ftzInTxnDtl.getChkStatus().equals(
								CommonConst.FTZ_MSG_STATUS_AUTH_SUCC)
								|| ftzInTxnDtl.getChkStatus().equals(
										CommonConst.FTZ_MSG_STATUS_AUTH_FAIL)) {
							count++;
						}
					}
					ftzInMsgCtl.setRsv1((ftzInMsgCtl.getTotalCount() - count)
							+ "");
				}
			}
			model.addAttribute("page", page);
		} else {
			model.addAttribute(ResultMessages.info().add("w.sm.0001"));
			logger.info("表外查询结束...");
			return "ftzmis/FTZIn_Auth_Qry";
		}
				
		return "ftzmis/FTZIn_Auth_Qry";
	}
	
	/**
	 * 查看明细 分发
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping("QryRedirect")
	public String queryRedirect(Model model, FTZINForm form) {
		if (CommonConst.MSG_NO_210302.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210302/QryDtl?selected_msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210303.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210303/Qry/DtlMsg/Init?FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210206.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210206/Qry/DtlMsg/Init?FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else {
			return null;
		}

	}
	
	/**
	 * 查询待审核的
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping("QryRedirectAuth")
	public String queryRedirectAuth(Model model, FTZINForm form) {
		if (CommonConst.MSG_NO_210302.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210302/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=1";
		} else if (CommonConst.MSG_NO_210303.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210303/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210304.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210304/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210305.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210305/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210306.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210306/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210307.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210307/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210308.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210308/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210309.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210309/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210201.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210201/QryAuthDtl?selected_msgId=" + form.getSelected_msgId()+"&unAuthFlag=1";
		}else if (CommonConst.MSG_NO_210202.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210202/QryAuthDtl?selected_msgId=" + form.getSelected_msgId()+"&unAuthFlag=1";
		}else if (CommonConst.MSG_NO_210203.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210203/QryAuthDtl?selected_msgId=" + form.getSelected_msgId()+"&unAuthFlag=1";
		}else if (CommonConst.MSG_NO_210204.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210204/QryAuthDtl?selected_msgId="	+ form.getSelected_msgId()+"&unAuthFlag=1";
		}else if (CommonConst.MSG_NO_210205.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210205/QryAuthDtl?selected_msgId="	+ form.getSelected_msgId()+"&unAuthFlag=1";
		} else if (CommonConst.MSG_NO_210206.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210206/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else if (CommonConst.MSG_NO_210207.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210207/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else if (CommonConst.MSG_NO_210208.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210208/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else if (CommonConst.MSG_NO_210209.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210209/Auth/DtlMsg/Init?operFlag=2&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210210.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210210/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=1";
		}else if (CommonConst.MSG_NO_210211.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210211/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=1";
		}else if (CommonConst.MSG_NO_210212.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210212/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=1";
		}else {
			return null;
		}

	}
	
	/**
	 * 审核全部
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping("QryRedirectAuthAll")
	public String queryRedirectAuthAll(Model model, FTZINForm form) {
		if (CommonConst.MSG_NO_210302.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210302/QryAuthDtl?selected_msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210303.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210303/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210304.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210304/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210305.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210305/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210306.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210306/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210307.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210307/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210308.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210308/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210309.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210309/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210201.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210201/QryAuthDtl?selected_msgId="
			+ form.getSelected_msgId();
		} else if (CommonConst.MSG_NO_210202.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210202/QryAuthDtl?selected_msgId="
			+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210203.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210203/QryAuthDtl?selected_msgId="
			+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210204.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210204/QryAuthDtl?selected_msgId="
			+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210205.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210205/QryAuthDtl?selected_msgId="
			+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210206.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210206/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else if (CommonConst.MSG_NO_210207.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210207/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else if (CommonConst.MSG_NO_210208.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210208/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}
		else if (CommonConst.MSG_NO_210209.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210209/Auth/DtlMsg/Init?operFlag=1&FtzInMsgCtl.msgId="+ form.getSelected_msgId();
		}else if (CommonConst.MSG_NO_210210.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210210/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=2";
		}else if (CommonConst.MSG_NO_210211.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210211/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=2";
		}else if (CommonConst.MSG_NO_210212.equals(form.getSelected_msgNo())) {
			return "redirect:/FTZ210212/QryAuthDtl?selected_msgId="+ form.getSelected_msgId()+"&unAuthFlag=2";
		}
		else {
			return null;
		}

	}
	
	
	
	
	@RequestMapping("SendQry")
	public String sendQuery(Model model, FTZINForm form,
			@PageableDefaults Pageable pageable){
		// trans form to queryObject
	
		
		FtzInMsgCtl query_FtzInMsgCtl = new FtzInMsgCtl();
		query_FtzInMsgCtl.setMsgId(form.getQuery_msgId());
		query_FtzInMsgCtl.setBranchId(form.getQuery_branchId().trim());
		query_FtzInMsgCtl.setRsv1(DateUtil
				.getFormatDateRemoveSprit(form.getQuery_workDate_start()));
		query_FtzInMsgCtl.setRsv2(DateUtil
				.getFormatDateRemoveSprit(form.getQuery_workDate_end()));
		query_FtzInMsgCtl.setMsgStatuss(new String[] {
				CommonConst.FTZ_MSG_STATUS_AUTH_SUCC});
		query_FtzInMsgCtl.setMsgNo(form.getQuery_msgNo());
		
		query_FtzInMsgCtl.setMsgNos(new String[] { CommonConst.MSG_NO_210201,
				CommonConst.MSG_NO_210202, CommonConst.MSG_NO_210203,
				CommonConst.MSG_NO_210204, CommonConst.MSG_NO_210205,
				CommonConst.MSG_NO_210206, CommonConst.MSG_NO_210207,
				CommonConst.MSG_NO_210208, CommonConst.MSG_NO_210209,
				CommonConst.MSG_NO_210210, CommonConst.MSG_NO_210211,
				CommonConst.MSG_NO_210212 });
		
		//Query
		// query DpMppCfg page list
		Page<FtzInMsgCtl> page = ftz210101Service.queryFtzInMsgCtlPage(pageable,
				query_FtzInMsgCtl);

	if (page.getContent().size() > 0) {
//			List<FtzInMsgCtl> ftzInMsgCtls = page.getContent();
//			for (FtzInMsgCtl ftzInMsgCtl : ftzInMsgCtls) {
//				ftzInMsgCtl.setSubmitDate(DateUtil
//						.getFormatDateAddSprit(ftzInMsgCtl.getSubmitDate()));
//				
//				FtzInTxnDtl query_FtzInTxnDtl = new FtzInTxnDtl();
//				query_FtzInTxnDtl.setMsgId(ftzInMsgCtl.getMsgId());
//				List<FtzInTxnDtl> ftzInTxnDtls = ftz210101Serv
//						.queryFtzInTxnDtlList(query_FtzInTxnDtl);
//				int count = 0;
//				if (null == ftzInTxnDtls || ftzInTxnDtls.size() < 1) {
//					ftzInMsgCtl.setRsv1(ftzInMsgCtl.getTotalCount() + "");
//				} else {
//					for (FtzInTxnDtl ftzInTxnDtl : ftzInTxnDtls) {
//						if (ftzInTxnDtl.getChkStatus().equals(
//								CommonConst.FTZ_MSG_STATUS_AUTH_SUCC)
//								|| ftzInTxnDtl.getChkStatus().equals(
//										CommonConst.FTZ_MSG_STATUS_AUTH_FAIL)) {
//							count++;
//						}
//					}
//					ftzInMsgCtl.setRsv1((ftzInMsgCtl.getTotalCount() - count)
//							+ "");
//				}
//			}
			model.addAttribute("page", page);
		} else {
			model.addAttribute(ResultMessages.info().add("w.sm.0001"));
			logger.info("表外查询结束...");
			return "ftzmis/FTZIN_Send_Qry";
		}
				
		return "ftzmis/FTZIN_Send_Qry";
	}
	
	
	
	@RequestMapping("SendMsg")
	public @ResponseBody
	 String sendMsg(@RequestParam("msgNo") String msgNo){
		JSONObject jo = new JSONObject();
		try
		{
			int size=2*1024*1024;
			List<List<FtzInMsgCtl>> baowenList=new ArrayList<List<FtzInMsgCtl>>();
			FtzInMsgCtl ftzInMsgCtl = new FtzInMsgCtl();
			ftzInMsgCtl.setMsgNo(msgNo);
			//所有MsgCtl信息
			List<FtzInMsgCtl> msgList = ftz210206Service.queryFtzInMsgCtlList(ftzInMsgCtl);
			//一个报文所包含的MsgCtl
			List<FtzInMsgCtl> list =new ArrayList<FtzInMsgCtl>();
			size=size-350;
		
			
			

			for(int i=0;i<msgList.size();i++)
			{
				if(size>=700)
				{
					size=size-700;
					FtzInTxnDtl ftzInTxnDtl=new FtzInTxnDtl();
					ftzInTxnDtl.setMsgId(msgList.get(i).getMsgId());
					List<FtzInTxnDtl> txnList=ftz210206Service.queryFtzInTxnDtlList(ftzInTxnDtl);
					if(size>=txnList.size()*800)
					{
						size=size-txnList.size()*800;
						list.add(msgList.get(i));
					}
					else
					{
						baowenList.add(list);
						size=2*1024*1024;
						size=size-350;
						size=size-700;
						size=size-txnList.size()*800;
						list =new ArrayList<FtzInMsgCtl>();
						list.add(msgList.get(i));
					}
				}
				else
				{
						size=2*1024*1024;
						size=size-350;
						baowenList.add(list);
						list =new ArrayList<FtzInMsgCtl>();
						i--;
				}
			}

		
//		ftzMsgProcService.submitMsg("210206",
//				"123");
//		
//		
//		ftzMsgProcService.submitMsg("210205",
//		"123");
		  jo.put("result", "Success") ;
		  return jo.toString();
		}
		catch(Exception ex)
		{
		  jo.put("result", "Failure") ;
		  return jo.toString();
		}
	}
	
	@Resource
	private FtzMsgProcService ftzMsgProcService;
	
	@Resource
	protected FTZ210101Service ftz210101Serv;
	
	@Autowired
	private FTZInCommonService ftz210206Service;
}
