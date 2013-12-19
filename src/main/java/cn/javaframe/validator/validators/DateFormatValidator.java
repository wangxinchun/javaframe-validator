package cn.javaframe.validator.validators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.RuleVO;
import cn.javaframe.validator.bean.ValidateResult;

/**
 * 时间格式校验   <br/>
 * 逻辑：先进行非空校验，然后进行patter校验
 * @author xinchun.wang
 *
 */
public class DateFormatValidator extends AbstractValidator {
	@Override
	public ValidateResult validate(RuleVO validator, Map<String, ?> params) {
			try {
				String pattern = validator.getRule();
				if(pattern == null || pattern.isEmpty()){
					pattern = "yyyy-MM-dd";
				}
				Object propertyValue = params.get(validator.getProperty());
				if(propertyValue == null){
					ValidateResult.errorInstance(validator.getTip());
				}
				if(propertyValue instanceof Date){
					return ValidateResult.SUCCESS;
				}else if(propertyValue instanceof Long){
					if((Long)propertyValue > 0){
						return ValidateResult.SUCCESS;
					} else{
						return ValidateResult.errorInstance(validator.getTip());
					}
				}else {
					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
					sdf.setLenient(false);
					sdf.parse(params.get(validator.getProperty()).toString());
					if(pattern.length() == StringUtils.length(params.get(validator.getProperty()).toString())){
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
