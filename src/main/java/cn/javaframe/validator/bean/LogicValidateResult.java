package cn.javaframe.validator.bean;

import cn.javaframe.validator.EnumConstants.NextStepType;

/**
 * 验证结果
 * @author xinchun.wang
 *
 */
public class LogicValidateResult {

	/** 验证结果*/
	private boolean success;

	/** 提醒信息，如果失败会有提醒信息，成功无提醒*/
	private String message;
	
	private NextStepType successNextStep = NextStepType.goNext;
	private NextStepType failNextStep = NextStepType.goNext;
	private NextStepType conditionFailNextStep = NextStepType.returnSuccess;
	
	
	

	public static final LogicValidateResult SUCCESS = new LogicValidateResult(true, null);
	public static final LogicValidateResult FAIL = new LogicValidateResult(false, null);
	
	public static final LogicValidateResult errorInstance(String message){
		return new LogicValidateResult(false,message);
	}
	
	public static final LogicValidateResult successInstance(){
		return new LogicValidateResult(false,null);
	}
	
	public LogicValidateResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NextStepType getSuccessNextStep() {
		return successNextStep;
	}

	public void setSuccessNextStep(NextStepType successNextStep) {
		this.successNextStep = successNextStep;
	}

	public NextStepType getFailNextStep() {
		return failNextStep;
	}

	public void setFailNextStep(NextStepType failNextStep) {
		this.failNextStep = failNextStep;
	}

	public NextStepType getConditionFailNextStep() {
		return conditionFailNextStep;
	}

	public void setConditionFailNextStep(NextStepType conditionFailNextStep) {
		this.conditionFailNextStep = conditionFailNextStep;
	}

	
}
