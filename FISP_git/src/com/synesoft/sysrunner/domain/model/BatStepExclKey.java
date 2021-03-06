package com.synesoft.sysrunner.domain.model;

import java.math.BigDecimal;

public class BatStepExclKey {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column BAT_STEP_EXCL.GROUP_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    private BigDecimal groupId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column BAT_STEP_EXCL.STEP_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    private String stepId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column BAT_STEP_EXCL.TASK_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    private String taskId;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column BAT_STEP_EXCL.GROUP_ID
     *
     * @return the value of BAT_STEP_EXCL.GROUP_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    public BigDecimal getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column BAT_STEP_EXCL.GROUP_ID
     *
     * @param groupId the value for BAT_STEP_EXCL.GROUP_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    public void setGroupId(BigDecimal groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column BAT_STEP_EXCL.STEP_ID
     *
     * @return the value of BAT_STEP_EXCL.STEP_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    public String getStepId() {
        return stepId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column BAT_STEP_EXCL.STEP_ID
     *
     * @param stepId the value for BAT_STEP_EXCL.STEP_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column BAT_STEP_EXCL.TASK_ID
     *
     * @return the value of BAT_STEP_EXCL.TASK_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column BAT_STEP_EXCL.TASK_ID
     *
     * @param taskId the value for BAT_STEP_EXCL.TASK_ID
     *
     * @abatorgenerated Thu Dec 05 17:02:48 CST 2013
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}