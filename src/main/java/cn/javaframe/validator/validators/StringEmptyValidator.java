package cn.javaframe.validator.validators;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;


/**
 * 字符串空校验器
 * @author xinchun.wang
 *
 */
public class StringEmptyValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {
		if(isEmpty(params.get(validator.getProperty()))){
			return ValidateResult.SUCCESS;
		}
		return ValidateResult.errorInstance(validator.getTip());
	}
    
	protected boolean isEmpty(String target){
		return StringUtils.isEmpty(target);
	}
	
}
