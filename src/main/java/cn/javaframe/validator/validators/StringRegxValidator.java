package cn.javaframe.validator.validators;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;

/**
 * 正则表达式验证器
 * @author xinchun.wang
 *
 */
public class StringRegxValidator extends AbstractValidator {

    private final ConcurrentHashMap<String, Pattern> cacheMap = new ConcurrentHashMap<String, Pattern>();
    
	@Override
	public ValidateResult validate(RuleVO validator, Map<String, ?> params) {
		String value = params.get(validator.getProperty()).toString();
		String ruleValue = validator.getRule();
		Pattern pattern = cacheMap.get(ruleValue);
		if(pattern == null){
			pattern = Pattern.compile(ruleValue);
			cacheMap.putIfAbsent(ruleValue, pattern);
		}
		
		boolean result=pattern.matcher(value).matches() ;
		if(result){
			return ValidateResult.SUCCESS;
		}else{
			logWarn(null, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
			return ValidateResult.errorInstance(validator.getTip());
		}
	}
}
