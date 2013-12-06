package cn.javaframe.validator.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;
import cn.javaframe.validator.validators.PolicyWeekDaysValidator;
import cn.javaframe.validator.validators.ValidatorFactory;

public class CustomerValidatorTest {
	/**
     * 业务相关的验证器
     */
    private static PolicyWeekDaysValidator policyWeekDaysValidator = new PolicyWeekDaysValidator();
    static{
    	ValidatorFactory.registerLocalValidator("week_day", policyWeekDaysValidator);
    }
    
    @Test
    public void testCustomerValidator_true(){
    	ValidatorVO vo = new ValidatorVO();
    	vo.setProperty("weekLimit");
    	vo.setTip("星期设置错误");
    	
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("weekLimit", "1,3,5");
    	ValidateResult result = ValidatorFactory.getLocalValidator("week_day").validate(vo, params);
    	Assert.assertTrue(result.isSuccess());
    }
    
    @Test
    public void testCustomerValidator_fail(){
    	ValidatorVO vo = new ValidatorVO();
    	vo.setProperty("weekLimit");
    	vo.setTip("星期设置错误");
    	
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("weekLimit", "1,9,5");
    	ValidateResult result = ValidatorFactory.getLocalValidator("week_day").validate(vo, params);
    	Assert.assertTrue(!result.isSuccess());
    }
}
