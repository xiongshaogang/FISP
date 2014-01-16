package com.synesoft.fisp.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.synesoft.fisp.app.sm.model.SM_Prm_QryForm.SM_Prm_QryFormAdd;

public class SysParam implements IMemoryResource{
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.PARAM_INFO
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String paramInfo;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.PARAM_VAL
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    /**
	 * 系统参数属性值 
	 */
    @NotEmpty(groups = { SM_Prm_QryFormAdd.class}, message ="{e.sm.7006}")
    private String paramVal;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.PARAM_DESC
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String paramDesc;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.RSV1
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String rsv1;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.RSV2
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String rsv2;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.RSV3
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String rsv3;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.RSV4
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String rsv4;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column SYS_PARAM.RSV5
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    private String rsv5;
    
    /**
	 * 系统参数组号 
	 */
    @NotEmpty(groups = { SM_Prm_QryFormAdd.class}, message ="{e.sm.7001}")
	private String paramGroup;
	
	/**
	 * 系统参数编号
	 */
    @NotEmpty(groups = { SM_Prm_QryFormAdd.class}, message ="{e.sm.7002}")
	private String paramCode;
	
	
	private String paramName;
	
	private String createTime;
	
	private String createUser;
	
	private String updateTime;
	
	private String updateUser;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.PARAM_INFO
     *
     * @return the value of SYS_PARAM.PARAM_INFO
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getParamInfo() {
        return paramInfo;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.PARAM_INFO
     *
     * @param paramInfo the value for SYS_PARAM.PARAM_INFO
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setParamInfo(String paramInfo) {
        this.paramInfo = paramInfo;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.PARAM_VAL
     *
     * @return the value of SYS_PARAM.PARAM_VAL
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getParamVal() {
        return paramVal;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.PARAM_VAL
     *
     * @param paramVal the value for SYS_PARAM.PARAM_VAL
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.PARAM_DESC
     *
     * @return the value of SYS_PARAM.PARAM_DESC
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getParamDesc() {
        return paramDesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.PARAM_DESC
     *
     * @param paramDesc the value for SYS_PARAM.PARAM_DESC
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.RSV1
     *
     * @return the value of SYS_PARAM.RSV1
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getRsv1() {
        return rsv1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.RSV1
     *
     * @param rsv1 the value for SYS_PARAM.RSV1
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setRsv1(String rsv1) {
        this.rsv1 = rsv1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.RSV2
     *
     * @return the value of SYS_PARAM.RSV2
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getRsv2() {
        return rsv2;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.RSV2
     *
     * @param rsv2 the value for SYS_PARAM.RSV2
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setRsv2(String rsv2) {
        this.rsv2 = rsv2;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.RSV3
     *
     * @return the value of SYS_PARAM.RSV3
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getRsv3() {
        return rsv3;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.RSV3
     *
     * @param rsv3 the value for SYS_PARAM.RSV3
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setRsv3(String rsv3) {
        this.rsv3 = rsv3;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.RSV4
     *
     * @return the value of SYS_PARAM.RSV4
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getRsv4() {
        return rsv4;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.RSV4
     *
     * @param rsv4 the value for SYS_PARAM.RSV4
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setRsv4(String rsv4) {
        this.rsv4 = rsv4;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column SYS_PARAM.RSV5
     *
     * @return the value of SYS_PARAM.RSV5
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public String getRsv5() {
        return rsv5;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column SYS_PARAM.RSV5
     *
     * @param rsv5 the value for SYS_PARAM.RSV5
     *
     * @abatorgenerated Fri Oct 18 14:05:12 CST 2013
     */
    public void setRsv5(String rsv5) {
        this.rsv5 = rsv5;
    }

	/**
	 * @return the paramGroup
	 */
	public String getParamGroup() {
		return paramGroup;
	}

	/**
	 * @param paramGroup the paramGroup to set
	 */
	public void setParamGroup(String paramGroup) {
		this.paramGroup = paramGroup;
	}

	/**
	 * @return the paramCode
	 */
	public String getParamCode() {
		return paramCode;
	}

	/**
	 * @param paramCode the paramCode to set
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public boolean searchByCode(String paramCode) {
		if (paramCode.equals(this.paramCode.trim()))
			return true;
		else
			return false;
	}

	@Override
	public boolean searchByName(String paramGroup) {
		if (paramGroup.equals(this.paramGroup.trim()))
			return true;
		else
			return false;
	}
}