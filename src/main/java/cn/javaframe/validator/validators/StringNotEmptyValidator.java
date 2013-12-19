package cn.javaframe.validator.validators;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;


/**
 * 字符串非空校验器
 * @author xinchun.wang
 *
 */
public class StringNotEmptyValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(RuleVO validator, Map<String, ?> params) {
		if(isNotEmpty(params.get(validator.getProperty()))){
			return ValidateResult.SUCCESS;
		}
		return ValidateResult.errorInstance(validator.getTip());
	}
    
	protected boolean isNotEmpty(Object target){
		if(target == null){
			return false;
		}
		return StringUtils.isNotEmpty(target.toString());
	}
	
}
