package com.synesoft.fisp.app.dp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.fw.common.message.ResultMessage;
import org.terasoluna.fw.common.message.ResultMessages;

import com.synesoft.fisp.app.dp.model.LoanAmountForm;
import com.synesoft.fisp.domain.model.DmLoanAmount;
import com.synesoft.fisp.domain.service.dp.DmLoanAmountService;

/**
 * 
 * @author michelle.wang
 * 
 */

@Controller
@RequestMapping(value = "dp01")
public class DP_01_04Controller {
	private static final Logger logger = LoggerFactory
			.getLogger(DP_01_04Controller.class);

	@ModelAttribute
	public LoanAmountForm setUpForm() {
		return new LoanAmountForm();
	}
	@RequestMapping("04/init")
	public String init() {
		logger.info("init...");
		return "dp/DP_01_04";
	}

	@RequestMapping("04/search")
	public String search(LoanAmountForm listForm,
			@PageableDefaults Pageable pageable, Model model) {
		logger.info("start search ...");
		DmLoanAmount loanAmount=listForm.getLoanAmount();
		if(loanAmount==null){
			loanAmount=new DmLoanAmount();
		}
		if(loanAmount.getWorkDate()!=null&&!loanAmount.getWorkDate().equals("")){
			loanAmount.setWorkDate(loanAmount.getWorkDate().replace("-", ""));
		}
		Page<DmLoanAmount> page= dmLoanAmountService.transQueryDmLoanAmountAuthList(pageable, loanAmount);
		if(page.getContent().size()>0){
			model.addAttribute("page", page);
		}else{
			model.addAttribute(
					"infomsg",
					ResultMessages.info().add(
							ResultMessage.fromCode("w.dp.0001")));
		}
		return "dp/DP_01_04";
	}

	@Autowired
	private DmLoanAmountService dmLoanAmountService;

}
