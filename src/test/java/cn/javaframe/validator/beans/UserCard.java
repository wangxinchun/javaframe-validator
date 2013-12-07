package cn.javaframe.validator.beans;

import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConclusionRule;
import cn.javaframe.validator.annotation.ConditionRule;
import cn.javaframe.validator.annotation.LogicRule;
import cn.javaframe.validator.annotation.Rules;

public class UserCard {
	@Rules(
			conditionList = {
					@ConditionRule(id = "A",type = RuleType.empty)
			},
			conclusionList = {
					@ConclusionRule(id = "B",type = RuleType.not_empty,tip = "不能为空"),
					@ConclusionRule(id = "C",type = RuleType.string_length_limit,value = "[14,14]",tip = "必须为14个字符"),
					@ConclusionRule(id = "D",type = RuleType.string_length_limit,value = "[16,16]",tip = "必须为16个字符")
			},
			logicList = {
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess),
					@LogicRule(condition = "B",conclusion = "C||D", tip = "",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.returnFail)
					},
			text = "用户身份账号")
	private long cardID;

	public long getCardID() {
		return cardID;
	}

	public void setCardID(long cardID) {
		this.cardID = cardID;
	}
	
	
}
