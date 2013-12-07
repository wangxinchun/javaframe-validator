package cn.javaframe.validator.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.beans.DateVO;
import cn.javaframe.validator.beans.UserCard;
import cn.javaframe.validator.service.CommonValidateService;

public class CommonValidateServiceTest {
	@Test
	public void testValidate() {
		CommonValidateService service = new CommonValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("cardID", "12345678901234d");
		ValidateResult result = service.validate(params, UserCard.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	
	
	
	@Test
	public void testValidateDate() {
		CommonValidateService service = new CommonValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("begin", "2013-12-09");
		params.put("end", "2013-12-08");
		ValidateResult result = service.validate(params, DateVO.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}

}
