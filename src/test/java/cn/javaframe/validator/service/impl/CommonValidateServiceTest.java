package cn.javaframe.validator.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.beans.DateVO;
import cn.javaframe.validator.beans.User;
import cn.javaframe.validator.beans.UserCard;
import cn.javaframe.validator.service.BeanValidateService;
import cn.javaframe.validator.service.MapValidateService;

/**
 * Test Case
 * @author wangxinchun1988@163.com
 * @date 2013-12-19下午8:18:56
 */
public class CommonValidateServiceTest {
	@Test
	public void testValidate() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("cardID", "12345678901234d");
		ValidateResult result = service.validate(params, UserCard.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	
	
	
	@Test
	public void testValidateDate() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("begin", "2013-12-09");
		params.put("end", "2013-12-08");
		ValidateResult result = service.validate(params, DateVO.class);
		Assert.assertEquals(result.isSuccess(), false);
	}
	
	@Test
	public void testValidateDate2() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("begin", "2013-12-09");
		params.put("end", "2013-12-10");
		ValidateResult result = service.validate(params, DateVO.class);
		Assert.assertEquals(result.isSuccess(), true);
	}

	@Test
	public void testValidateDate3() {
		BeanValidateService service = new BeanValidateService();
		DateVO vo = new DateVO();
		vo.setBegin("2014-12-14");
		vo.setEnd("2014-12-10");
		ValidateResult result = service.validate(vo);
		println(result);
		Assert.assertEquals(result.isSuccess(), false);
	}
	
	
	@Test
	public void testValidateDate4() throws Exception {
		BeanValidateService service = new BeanValidateService();
		UserCard userCard = new UserCard();
		userCard.setCardID("41272519880673829X");
		userCard.setCreateDate(DateUtils.parseDate("2011-12-09", new String[]{"yyyy-MM-dd"}));
		userCard.setEndDate(new Date(System.currentTimeMillis()+(long)(1000*60*60*24)));
		ValidateResult result = service.validate(userCard);
		println(result);
		Assert.assertEquals(result.isSuccess(), true);
	}
	
	@Test
	public void testValidateDate5() throws Exception {
		BeanValidateService service = new BeanValidateService();
		User user = new User();
		user.setUsername("xinchun.wang");
		UserCard userCard = new UserCard();
		userCard.setCardID("41272519880673829X");
		userCard.setCreateDate(DateUtils.parseDate("2013-12-09", new String[]{"yyyy-MM-dd"}));
		userCard.setEndDate(new Date(System.currentTimeMillis()+(long)(1000*60*60*24)));
		user.setUserCard(userCard);
		ValidateResult result = service.validate(user);
		println(result);
		Assert.assertEquals(result.isSuccess(), true);
	}
	private void println(ValidateResult result){
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	
	
	
}
