package cn.javaframe.validator.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
		CommonValidateService service = new CommonValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("cardID", "23435");
		ValidateResult result = service.validate(params, UserCard.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	

}
