package cn.javaframe.validator.bean;

import cn.javaframe.validator.EnumConstants.NextStepType;

/**
 * 逻辑验证结果
 * @author wangxinchun1988@163.com
 *
 */
public class LogicValidateResult {

	/** 验证结果*/
	private boolean success;

	/** 提醒信息，如果失败会有提醒信息，成功提醒为null*/
	private String message;
	
	/** 逻辑验证成功的下一步执行逻辑  <br/>
	 *  1、NextStepType.goNext 默认进行下一个校验规则的验证。（如果词条为最后一个逻辑项，那么等同于NextStepType.returnSuccess）<br/>
	 *  2、NextStepType.returnSuccess 表示此验证完成之后，不再进行下一个校验规则的验证 ，直接返回校验成功
	 * */
	private NextStepType successNextStep = NextStepType.goNext;
	/** 
	 * 逻辑校验失败后下一步执行逻辑
	 * 1、NextStepType.returnFail 默认校验失败时，直接返回校验失败
	 * 2、NextStepType.goNext 校验继续下一个词条的校验（如果词条为最后一个逻辑项，那么等同于NextStepType.returnSuccess）
	 * */
	private NextStepType failNextStep = NextStepType.returnFail;
	
	/** 
	 * 如果条件校验失败，那么下一步的执行逻辑 <br>
	 * 1、NextStepType.goNext 默认条件校验失败，进入下一个逻辑词条的校验（如果词条为最后一个逻辑项，那么等同于NextStepType.returnSuccess）<br>
	 * 2、NextStepType.returnFail 不进行下一个词条的校验，直接返回校验失败 <br>
	 * 3、NextStepType.returnSuccess 不进行下一个词条的校验，直接返回校验成功
	 * */
	private NextStepType conditionFailNextStep = NextStepType.goNext;
	
	
	public static final LogicValidateResult SUCCESS = new LogicValidateResult(true, null);
	public static final LogicValidateResult FAIL = new LogicValidateResult(false, null);
	
	public static final LogicValidateResult errorInstance(String message){
		return new LogicValidateResult(false,message);
	}
	
	public static final LogicValidateResult successInstance(){
		return new LogicValidateResult(true,null);
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
