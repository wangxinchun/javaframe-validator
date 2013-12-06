package cn.javaframe.validator.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.javaframe.validator.EnumConstants.LogicType;
import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConclusionItem;
import cn.javaframe.validator.annotation.ConditionItem;
import cn.javaframe.validator.annotation.LogicItem;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.beans.User;
import cn.javaframe.validator.beans.UserCard;
import cn.javaframe.validator.service.CommonValidateService;

public class CommonValidateServiceTest {

	
	@Test
	public void testValidateUser() {
		
		CommonValidateService service = new CommonValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("height", "110");
		params.put("age", "6");
		params.put("needGoSchool", "0");
		ValidateResult result = service.validate(params, User.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	
	
	@Test
	public void testValidate() {
		class UserCard {
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
		}
		
		CommonValidateService service = new CommonValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("cardID", "12345678901234");
		ValidateResult result = service.validate(params, UserCard.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	

}
