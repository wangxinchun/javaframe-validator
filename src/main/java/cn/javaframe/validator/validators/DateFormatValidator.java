package cn.javaframe.validator.validators;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;

/**
 * 时间格式校验   <br/>
 * 逻辑：先进行非空校验，然后进行patter校验
 * @author xinchun.wang
 *
 */
public class DateFormatValidator extends AbstractValidator {
	@Override
	public ValidateResult validate(RuleVO validator, Map<String, String> params) {
			try {
				String pattern = validator.getRule();
				if(pattern == null || pattern.isEmpty()){
					pattern = "yyyy-MM-dd";
				}
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				sdf.setLenient(false);
				sdf.parse(params.get(validator.getProperty()));
				if(pattern.length() == StringUtils.length(params.get(validator.getProperty()))){
					return ValidateResult.SUCCESS;
				}else {
					return ValidateResult.errorInstance(validator.getTip());
				}
			} catch (Exception e) {
				logWarn(e, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
				return ValidateResult.errorInstance(validator.getTip());
			}
	}
}
