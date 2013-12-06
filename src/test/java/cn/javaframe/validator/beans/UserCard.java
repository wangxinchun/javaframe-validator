package cn.javaframe.validator.beans;

import cn.javaframe.validator.EnumConstants.LogicType;
import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConclusionItem;
import cn.javaframe.validator.annotation.ConditionItem;
import cn.javaframe.validator.annotation.LogicItem;
import cn.javaframe.validator.annotation.Rules;

public class UserCard {
	@Rules(
			conditionList = {
					@ConditionItem(id = "A",type = RuleType.empty)
			},
			conclusionList = {
					@ConclusionItem(id = "B",type = RuleType.not_empty,tip = "不能为空"),
					@ConclusionItem(id = "C",type = RuleType.string_length_limit,value = "[14,14]",tip = "必须为14个字符"),
					@ConclusionItem(id = "D",type = RuleType.string_length_limit,value = "[16,16]",tip = "必须为16个字符")
			},
			logicList = {
					@LogicItem(conclusion = "A",successNextStep = NextStepType.returnSuccess),
					@LogicItem(condition = "B",type = LogicType.deduce,conclusion = "C||D", tip = "",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.returnFail)
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
