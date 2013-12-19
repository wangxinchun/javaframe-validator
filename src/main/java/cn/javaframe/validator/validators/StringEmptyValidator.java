package cn.javaframe.validator.validators;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;


/**
 * 字符串空校验器
 * @author xinchun.wang
 *
 */
public class StringEmptyValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(RuleVO validator, Map<String, ?> params) {
		if(isEmpty(params.get(validator.getProperty()))){
			return ValidateResult.SUCCESS;
		}
		return ValidateResult.errorInstance(validator.getTip());
	}
    
	protected boolean isEmpty(Object target){
		if(target == null){
			return true;
		}
		return StringUtils.isEmpty(target.toString());
	}
	
}
