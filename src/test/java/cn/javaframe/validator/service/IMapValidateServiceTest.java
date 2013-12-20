package cn.javaframe.validator.service;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.beans.DateVO;
import cn.javaframe.validator.beans.UserCard;

/**
 * Map Test Case
 * 
 * @author wangxinchun1988@163.com
 * @date 2013-12-20下午3:31:30
 */
public class IMapValidateServiceTest {

	/**
	 * not pass case
	 */
	@Test
	public void testValidateDate() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("begin", "2013-12-09");
		params.put("end", "2013-12-08");
		ValidateResult result = service.validate(params, DateVO.class);
		Assert.assertEquals(result.isSuccess(), false); 
	}
	
	/**
	 * pass case
	 */
	@Test
	public void testValidateDate2() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("begin", "2013-12-09");
		params.put("end", "2013-12-10");
		ValidateResult result = service.validate(params, DateVO.class);
		Assert.assertEquals(result.isSuccess(), true);
	}
	
	/**
	 * pass case
	 */
	@Test
	public void testValidateStringLength() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("cardID", "41272519880673829X");
		ValidateResult result = service.validate(params, UserCard.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
		Assert.assertEquals(result.isSuccess(), true);
	}
	
	/**
	 * not pass case 
	 */
	@Test
	public void testValidateStringLength2() {
		MapValidateService service = new MapValidateService();
		Map<String,String> params = new HashMap<String,String>();
		params.put("cardID", "41272519880673829");
		ValidateResult result = service.validate(params, UserCard.class);
		System.out.println(result.isSuccess());
		System.out.println(result.getMessage());
		Assert.assertEquals(result.isSuccess(), false);
	}
	
	
	

}
