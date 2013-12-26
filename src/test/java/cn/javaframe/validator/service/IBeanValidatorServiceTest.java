package cn.javaframe.validator.service;

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

/**
 * Bean Test Case
 * 
 * @author wangxinchun1988@163.com
 * @date 2013-12-20下午3:31:30
 */
public class IBeanValidatorServiceTest {
	
	/**
	 * not pass
	 */
	@Test
	public void testValidateDate() {
		BeanValidateService service = new BeanValidateService();
		DateVO vo = new DateVO();
		vo.setBegin("2014-12-14");
		vo.setEnd("2014-12-10");
		ValidateResult result = service.validate(vo);
		println(result);
		Assert.assertEquals(result.isSuccess(), false);
	}

	/**
	 * pass
	 */
	@Test
	public void testValidateDate2() {
		BeanValidateService service = new BeanValidateService();
		DateVO vo = new DateVO();
		vo.setBegin("2014-12-14");
		vo.setEnd("2014-12-13");
		ValidateResult result = service.validate(vo);
		println(result);
		Assert.assertEquals(result.isSuccess(), true);
	}
	
	
	/**
	 * not pass  check: RuleType.string_length_limit RuleType.number_value_limit RuleType.not_empty
	 */
	@Test
	public void testValidateStringLength() {
		BeanValidateService service = new BeanValidateService();
		Skill skill = new Skill();
		skill.setName("java");
		skill.setUseYear(20);
		skill.setNote("I love");
		ValidateResult result = service.validate(skill);
		println(result);
		Assert.assertEquals(result.isSuccess(), false);
	}
	
	/**
	 * pass  check: RuleType.string_length_limit RuleType.number_value_limit RuleType.not_empty
	 */
	@Test
	public void testValidateStringLength2() {
		BeanValidateService service = new BeanValidateService();
		Skill skill = new Skill();
		skill.setName("java");
		skill.setUseYear(20);
		skill.setNote("I love java");
		ValidateResult result = service.validate(skill);
		println(result);
		Assert.assertEquals(result.isSuccess(), true);
	}

	/**
	 * pass check:RuleType.date_compare_now RuleType.date_compare_refer
	 */
	@Test
	public void testValidateDate4() throws Exception {
		BeanValidateService service = new BeanValidateService();
		UserCard userCard = new UserCard(); //RuleType.not_empty RuleType.string_length_limit
		userCard.setCardID("41272519880673829X");
		userCard.setCreateDate(DateUtils.parseDate("2011-12-09", new String[] { "yyyy-MM-dd" }));
		//RuleType.date_compare_now RuleType.date_compare_refer
		userCard.setEndDate(new Date(System.currentTimeMillis() + (long) (1000 * 60 * 60 * 24)));
		ValidateResult result = service.validate(userCard);
		println(result);
		Assert.assertEquals(result.isSuccess(), true);
	}

	/**
	 * Bean nest List<Bean>,Bean[] arr
	 * @throws Exception
	 */
	@Test
	public void testValidateDate5() throws Exception {
		BeanValidateService service = new BeanValidateService();
		User user = new User();
		user.setUsername("xinchun.wang"); //RuleType.not_empty
		UserCard userCard = new UserCard();
		userCard.setCardID("41272519880673829X");
		userCard.setCreateDate(DateUtils.parseDate("2013-12-09", new String[] { "yyyy-MM-dd" }));
		userCard.setEndDate(new Date(System.currentTimeMillis() + (long) (1000 * 60 * 60 * 24)));
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

	private void println(ValidateResult result) {
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
	}

}
