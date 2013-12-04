package cn.javaframe.validator.validators;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import cn.javaframe.validator.EnumConstants.BoundryType;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;

/**
 * 时间校验器 支持：>,>=,=,<=,< 五种方式
 * 
 * @author xinchun.wang
 * 
 */
public class DateReferCompareValidator extends AbstractReferValidator {
	
	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {
		try {
			String ruleValue = validator.getRule();
			String[] ruleValueArr = ruleValue.split(",");
			String referName = ruleValueArr[0];
			BoundryType type = resolveBoundryType(ruleValueArr[1]);
			String pattern = PATTERN_yyyy_MM_dd;
			if(ruleValueArr.length == 3){
				pattern = ruleValueArr[2];
			}
			String targerStrValue = params.get(validator.getProperty());
			String referStrValue = params.get(referName);

			long targetLong = DateUtils.parseDate(targerStrValue, new String[]{pattern,PATTERN_yyyy_MM_dd2}).getTime();
			long referLong = DateUtils.parseDate(referStrValue, new String[]{pattern,PATTERN_yyyy_MM_dd2}).getTime();

			boolean result = numberCompare(BigDecimal.valueOf(targetLong), BigDecimal.valueOf(referLong), type);
			if (result) {
				return ValidateResult.SUCCESS;
			} else {
				return ValidateResult.errorInstance(validator.getTip());
			}
		} catch (Exception e) {
			logWarn(e, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
			return ValidateResult.errorInstance(validator.getTip());
		}
	}
}
