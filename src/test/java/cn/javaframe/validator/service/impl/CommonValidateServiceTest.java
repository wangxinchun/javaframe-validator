package cn.javaframe.validator.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.javaframe.validator.annotation.RuleItem;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.beans.User;
import cn.javaframe.validator.service.CommonValidateService;

public class CommonValidateServiceTest {

	@Test
	public void testValidate() {
		
	}
	
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
	public void testValidateUserAnno() {
		User u = new User();
		User u2 = new User();
		Field[] userFields = u.getClass().getDeclaredFields();
		Field[] userFields2 = u2.getClass().getDeclaredFields();

		Rules u2Rules = userFields2[2].getAnnotation(Rules.class);
		Rules rules =  userFields[2].getAnnotation(Rules.class);
		
		System.out.println(u2Rules == rules);
		System.out.println(u2Rules.equals(rules));
	}
	
	
	@Test
	public void testValidateUserAnno2() {
		User u = new User();
		User u2 = new User();
		Field[] userFields = u.getClass().getDeclaredFields();
		Field[] userFields2 = u2.getClass().getDeclaredFields();

		RuleItem u2Rules = userFields2[1].getAnnotation(RuleItem.class);
		RuleItem rules =  userFields[0].getAnnotation(RuleItem.class);
		
		System.out.println(u2Rules.equals(rules));
	}

}
