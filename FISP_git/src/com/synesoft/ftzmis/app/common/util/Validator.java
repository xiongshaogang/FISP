package com.synesoft.ftzmis.app.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.synesoft.fisp.domain.model.OrgInf;
import com.synesoft.fisp.domain.repository.sm.OrgInfRepository;
import com.synesoft.ftzmis.app.common.constants.CommonConst;

/**
 * @author yyw
 *
 */
public class Validator {
	
	/**
	 * 校验国别代码为CHN时，国内代码是否为空
	 * @param countryCode - 国别代码
	 * @param districtCode - 国内代码
	 * @return
	 * <p>&nbsp;&nbsp;TRUE - 校验通过，不为空</p>
	 * <p>&nbsp;&nbsp;FALSE - 校验不通过，为空</p>
	 */
	public static Boolean CheckDistrictCode(String countryCode, String districtCode) {
		if (null == countryCode || countryCode.trim().length() == 0) {
			return false;
		} else {
			if (CommonConst.VALID_COUNTRY_CODE_DEFAULT.equals(countryCode)) {
				if (null == districtCode || districtCode.trim().length() == 0) 
					return false;
				else 
					return true;
			} else {
				return true;
			}
		}
	}

	/**
	 * 校验所属机构代码是否为本行机构
	 * @param accOrgCode - 所属机构代码
	 * @return
	 * <p>&nbsp;&nbsp;TRUE - 校验通过，是</p>
	 * <p>&nbsp;&nbsp;FALSE - 校验不通过，否</p>
	 */
	public static Boolean CheckAccOrgCode(OrgInfRepository orgInfRepository, String accOrgCode) {
		if (null == accOrgCode || accOrgCode.trim().equals("")) {
			return false;
		} else {
			OrgInf orgInfo = orgInfRepository.query(accOrgCode);
			if (null == orgInfo) 
				return false;
			else 
				return true;
		}
	}
	
	/**
	 * 匹配金额，符号1位，整数22位，小数2位
	 * @param amount
	 * @return
	 * <p>&nbsp;&nbsp;TRUE - 校验通过，是</p>
	 * <p>&nbsp;&nbsp;FALSE - 校验不通过，否</p>
	 */
	public static Boolean CheckAmount(BigDecimal amount) {
		Pattern pattern = Pattern.compile("^-?[0-9]{1,22}(.[0-9]{2})?$");
		Matcher matcher = pattern.matcher(amount.toString());
		return matcher.find();
	}
}