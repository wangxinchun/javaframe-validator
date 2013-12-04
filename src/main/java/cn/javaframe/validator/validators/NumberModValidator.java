package cn.javaframe.validator.validators;

import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;
import cn.javaframe.validator.exception.ValidatorConfigException;

/**
 * 
 * @author xinchun.wang
 * 
 * note: 
 */
public class NumberModValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {
			String ruleValue = validator.getRule();
			String[] ruleValueArr = ruleValue.split(",");
			if(ruleValueArr == null || ruleValueArr.length != 2){
				throw new ValidatorConfigException("NumberModValidator 配置错误 ");
			}
			try{
				int toDivide = Integer.parseInt(ruleValueArr[0]);
				int resultDivide = Integer.parseInt(ruleValueArr[1]);
				double doubleVal = Double.parseDouble(params.get(validator.getProperty()));
				boolean result = doubleVal % toDivide == resultDivide;
				if (result) {
					return ValidateResult.SUCCESS;
				}
			}catch(Exception e){
				logWarn(e, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
				throw new ValidatorConfigException("NumberModValidator 配置错误 ");
			}
			return ValidateResult.errorInstance(validator.getTip());
	}
	
}
