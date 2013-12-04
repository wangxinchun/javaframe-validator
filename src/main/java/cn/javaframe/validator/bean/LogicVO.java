package cn.javaframe.validator.bean;

import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.condition.ConditionGroup;

/**
 * 逻辑传递VO
 * 
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:15:28
 */
public class LogicVO {
	private ConditionGroup conditionGroup;
	private ConditionGroup conclusionGroup;
	private String tip; // 校验失败提醒信息
	private NextStepType successNextStep;
	private NextStepType failNextStep;
	private NextStepType conditionFailNextStep;


	public ConditionGroup getConclusionGroup() {
		return conclusionGroup;
	}

	public void setConclusionGroup(ConditionGroup conclusionGroup) {
		this.conclusionGroup = conclusionGroup;
	}

	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}

	public void setConditionGroup(ConditionGroup conditionGroup) {
		this.conditionGroup = conditionGroup;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
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
