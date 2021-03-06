package com.synesoft.fisp.app.sm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.fw.common.exception.BusinessException;
import org.terasoluna.fw.common.message.ResultMessage;
import org.terasoluna.fw.common.message.ResultMessages;

import com.synesoft.fisp.app.common.constants.CommonConst;
import com.synesoft.fisp.app.common.utils.StringUtil;
import com.synesoft.fisp.app.sm.model.UserInfTmpForm;
import com.synesoft.fisp.domain.model.OrgInf;
import com.synesoft.fisp.domain.model.RoleInf;
import com.synesoft.fisp.domain.model.SysUserRole;
import com.synesoft.fisp.domain.model.UserOrgInfTmp;
import com.synesoft.fisp.domain.model.UserRoleInfTmp;
import com.synesoft.fisp.domain.service.sm.OrgInfMaintenanceService;
import com.synesoft.fisp.domain.service.sm.RoleInfService;
import com.synesoft.fisp.domain.service.sm.SysUserRoleInfService;
import com.synesoft.fisp.domain.service.sm.UserInfService;
import com.synesoft.fisp.domain.service.sm.UserOrgInfService;
import com.synesoft.fisp.domain.service.sm.UserRoleInfMaintenanceService;

/**
 * 操作员信息审核(SM_03_06)
 * @author yyw
 * @date 2014-01-02
 */
@Controller
@RequestMapping(value = "sm03")
public class SM_03_06Controller {
	private static final Logger logger = LoggerFactory.getLogger(SM_03_06Controller.class);
	
	@ModelAttribute
	public UserInfTmpForm setInfoUpForm() {
		return new UserInfTmpForm();
	}

	/**
	 * 操作员信息维护审核/操作 - 审核通过
	 * @param form
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("06/auth")
	public String auth(UserInfTmpForm form, BindingResult result, Model model, HttpServletRequest request){
		logger.info("start auth ...");
		
		queryInfo(form,result,model,request);
		
		if (result.hasErrors()) {
			return "sm/SM_03_06";
		}
		
		try {
			userInfService.transAuthByMode(form);
		} catch (BusinessException e) {
			model.addAttribute("errmsg", e.getResultMessages());
			return "sm/SM_03_06";
		}
		model.addAttribute("successmsg", ResultMessages
				.success().add(ResultMessage.fromCode("i.sm.0004")));
		return "sm/SM_03_06";
	}
	
	/**
	 * 操作员信息维护审核/操作 - 审核拒绝
	 * @param form
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("06/reject")
	public String reject(UserInfTmpForm form,
	BindingResult result, Model model, HttpServletRequest request) {
		logger.info("start reject ...");
		
		queryInfo(form,result,model,request);
		if (result.hasErrors()) {
			return "sm/SM_03_06";
		}
		
		try {
			userInfService.transRejectByMode(form);
		} catch (BusinessException e) {
			model.addAttribute("errmsg", e.getResultMessages());
			return "sm/SM_03_06";
		}
		model.addAttribute("successmsg", ResultMessages
				.success().add(ResultMessage.fromCode("i.sm.0005")));
		return "sm/SM_03_06";
	}

	/**
	 * 根据角色模式查询用户机构信息，用户机构角色信息或者用户角色信息，从主表和临时表
	 * @param form
	 * @param result
	 * @param model
	 * @param request
	 */
	private void queryInfo(UserInfTmpForm form, BindingResult result, Model model, HttpServletRequest request){
		// 1) Ready for data
		// 1.1) get orgnazition information from table and temporary
		UserOrgInfTmp userOrgInfTmp = new UserOrgInfTmp();
		userOrgInfTmp.setUserid(form.getUserInfTmp().getUserid().trim());
		userOrgInfTmp.setOptstatus(CommonConst.OPTSTATUS_WAITAUDIT);
		List<UserOrgInfTmp> userOrgList = userOrgInfService.transQueryUserOrgInfMerge(userOrgInfTmp);
		
		// init - orgnazition information: availabledOrgList + selectedOrgList = all orgnazition
		// get availabled orgnazition and selected orgnazition		
		List<List<OrgInf>> orgList = orgInfMaintenanceService.transQueryAvailabledAndSelectedList3(userOrgList);
		model.addAttribute("AvailabledOrg", orgList.get(1));				// all availabed org object
		model.addAttribute("SelectedOrg", userOrgList);						// selected org array

		// 1.2) 获取所有机构对应的角色
		HashMap<String, ArrayList<RoleInf>> roleOrgAllMap = new HashMap<String, ArrayList<RoleInf>>();
		List<OrgInf> orgAlllist = orgInfMaintenanceService.transQueryOrgInfList();
		for(int i = 0 ;i<orgAlllist.size();i++){
			String orgId = StringUtil.trim(orgAlllist.get(i).getOrgid());
			ArrayList<RoleInf> roleList=(ArrayList<RoleInf>) roleInfService.queryRolesByOrgRec(orgId);
			roleOrgAllMap.put(orgId, roleList);
		}
		request.setAttribute("AllOrgRoleMap", roleOrgAllMap);

		// 2) data process in different way accroding to RoleMode
		String roleMode = form.getRoleMode();
		
		// 2.1) 机构与角色无关 - RoleMode == 1(ROLE_MODE_UNBIND_ORG)
		if (roleMode.equals(CommonConst.ROLE_MODE_UNBIND_ORG)) {
			// get all role - all field of roleInf is null 
			RoleInf roleInf = new RoleInf();
			List<RoleInf> allRoleList = roleInfService.queryRolesByOrg(roleInf);

			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserid(form.getUserInfTmp().getUserid().trim());
			sysUserRole.setOptstatus(CommonConst.OPTSTATUS_WAITAUDIT);
			String[] roleArray = sysUserRoleInfService.queryRoleListMerge(sysUserRole);
			
			request.setAttribute("AvailabledRole", allRoleList);				// all availabed role object
			request.setAttribute("SelectedRole", roleArray);			// selected role array

		// 2.2) 机构与角色有关 - RoleMode == 0(ROLE_MODE_BIND_ORG)	
		} else if (roleMode.equals(CommonConst.ROLE_MODE_BIND_ORG)) {
			List<OrgInf> allOrgList = orgInfMaintenanceService.transQueryOrgInfList();						// get all orgnazition
			HashMap<String, ArrayList<RoleInf>> allOrgRoleMap = new HashMap<String, ArrayList<RoleInf>>();	// org-role Map object
			if (allOrgList.size() > 0 ) {
				// 获取所有机构对应的角色
				for (int i = 0; i < allOrgList.size(); i++) {
					String orgId = StringUtil.trim(allOrgList.get(i).getOrgid());
					ArrayList<RoleInf> roleList = (ArrayList<RoleInf>) roleInfService.queryRolesByOrgRec(orgId);
					allOrgRoleMap.put(orgId, roleList);
				}
			} else {
				model.addAttribute("infomsg", ResultMessages.info().add(ResultMessage.fromCode("w.sm.0001")));
			}
			
			// get org-role information from table and temporary
			UserRoleInfTmp userRoleInfTmp = new UserRoleInfTmp();
			userRoleInfTmp.setUserid(form.getUserInfTmp().getUserid().trim());
			userRoleInfTmp.setOptstatus(CommonConst.OPTSTATUS_WAITAUDIT);
			List<UserRoleInfTmp> mergeRoleList = userRoleInfMaintenanceService.transQueryRoleListMerge(userRoleInfTmp);
			
			// get selected org-role information, format: orgId_roleId
			List<String> selectedOrgRole = new ArrayList<String>();
			for (int i = 0; i < mergeRoleList.size(); i++) {
				UserRoleInfTmp inf = mergeRoleList.get(i);
				String str = inf.getOrgid().trim() + "_" + inf.getRoleid().trim();
				selectedOrgRole.add(str);
			}
			
			request.setAttribute("AllOrgRoleMap", allOrgRoleMap);				// all org-role map
			request.setAttribute("SelectedOrgRole", selectedOrgRole);			// selected org-role
		} else {
			
		}

		request.setAttribute("RoleMode", form.getRoleMode());
		model.addAttribute("form", form);
	}
	
	@Autowired
	private UserInfService userInfService;
	@Autowired
	private OrgInfMaintenanceService orgInfMaintenanceService;
	@Autowired
	private UserOrgInfService userOrgInfService;
	@Autowired
	private RoleInfService roleInfService;
	@Autowired
	private UserRoleInfMaintenanceService userRoleInfMaintenanceService;
	@Autowired
	private SysUserRoleInfService sysUserRoleInfService;

}
