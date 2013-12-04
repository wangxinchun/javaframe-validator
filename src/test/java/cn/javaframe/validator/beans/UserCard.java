package cn.javaframe.validator.beans;

import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConditionItem;
import cn.javaframe.validator.annotation.LogicItem;
import cn.javaframe.validator.annotation.RuleItem;
import cn.javaframe.validator.annotation.Rules;

public class UserCard {
	@Rules(
			conditionList = {
					@ConditionItem(id = "A",type = RuleType.empty)
			},
			ruleList = {
					@RuleItem(id = "B",type = RuleType.not_empty,tip = "不能为空"),
					@RuleItem(id = "C",type = RuleType.string_length_limit,value = "[14,14]",tip = "字符长度不对"),
					@RuleItem(id = "D",type = RuleType.string_length_limit,value = "[16,16]",tip = "字符长度不对")
			},
			logicList = {
					@LogicItem(conclusion = "A",successNextStep = NextStepType.returnSuccess),
					@LogicItem(condition = "B",conclusion = "C",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.returnFail),
					@LogicItem(condition = "B",conclusion = "D",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.returnFail)
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
