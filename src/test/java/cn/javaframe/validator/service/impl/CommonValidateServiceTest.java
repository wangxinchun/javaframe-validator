package cn.javaframe.validator.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.beans.DateVO;
import cn.javaframe.validator.beans.Skill;
import cn.javaframe.validator.beans.User;
import cn.javaframe.validator.beans.UserCard;
import cn.javaframe.validator.service.BeanValidateService;

/**
 * Test Case
 * @author wangxinchun1988@163.com
 * @date 2013-12-19下午8:18:56
 */
public class CommonValidateServiceTest {
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
	public void testValidateDate33() {
		BeanValidateService service = new BeanValidateService();
		Skill skill = new Skill();
		skill.setName("java");
		skill.setUseYear(20);
		skill.setNote("I love");
		ValidateResult result = service.validate(skill);
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
		List<Skill> list = new ArrayList<Skill>();
		
		Skill skill = new Skill();
		skill.setName("java");
		skill.setUseYear(23);
		skill.setNote("I love Java");
		list.add(skill);
		
		skill = new Skill();
		skill.setName("C#");
		skill.setUseYear(30);
		skill.setNote("I love Java");
		list.add(skill);
		
		user.setList(list);
		
		ValidateResult result = service.validate(user);
		println(result);
		Assert.assertEquals(result.isSuccess(), true);
	}
	private void println(ValidateResult result){
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}
	
	
	
}
