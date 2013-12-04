package cn.javaframe.validator.validators;

import java.math.BigDecimal;
import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;

/**
 * 数字范围校验校验
 * 
 * @author xinchun.wang
 *  eg: value = "[2,12]", 
    	 *     value = "(2,12)",
    	 *     value = "[2,12)"
    	 *     value = "(2,12)"
 */
public class NumberLimitValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {
		//校验name对应的值不能为空
			String paramValue = params.get(validator.getProperty());
			try {
				String ruleValue = validator.getRule();
				boolean leftContains = false;
				boolean rightContains = false;
				if(ruleValue.startsWith("[")){
					leftContains = true;
				}
				if(ruleValue.endsWith("]")){
					rightContains = true;
				}
				ruleValue = removeRangeFlag(ruleValue);
				String[] valueArr = ruleValue.split(",");
				BigDecimal min = new BigDecimal(valueArr[0].trim());
				BigDecimal max = new BigDecimal(valueArr[1].trim());
				BigDecimal paramDecimal = new BigDecimal(paramValue);
				if(leftContains == true && rightContains == true){
					if(min.compareTo(paramDecimal) <=0 &&  max.compareTo(paramDecimal) >=0){
						return ValidateResult.SUCCESS;
					}else {
						return ValidateResult.errorInstance(validator.getTip());
					}
				}else if(leftContains = true && rightContains == false){
					if(min.compareTo(paramDecimal) <=0 &&  max.compareTo(paramDecimal) >0){
						return ValidateResult.SUCCESS;
					}else {
						return ValidateResult.errorInstance(validator.getTip());
					}
				}else if(leftContains == false && rightContains == true){
					if(min.compareTo(paramDecimal) <0 &&  max.compareTo(paramDecimal) >=0){
						return ValidateResult.SUCCESS;
					}else {
						return ValidateResult.errorInstance(validator.getTip());
					}
				}else {
					if(min.compareTo(paramDecimal) <0 &&  max.compareTo(paramDecimal) >0){
						return ValidateResult.SUCCESS;
					}else {
						return ValidateResult.errorInstance(validator.getTip());
					}
				}

			} catch (Exception e) {
				logWarn(e, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
				return ValidateResult.errorInstance(validator.getTip());
			}

	}
}
