package cn.javaframe.validator.validators;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import cn.javaframe.validator.EnumConstants.BoundryType;
import cn.javaframe.validator.bean.RuleVO;
import cn.javaframe.validator.bean.ValidateResult;

/**
 * 时间校验器 支持：>,>=,=,<=,< 五种方式
 * 
 * @author xinchun.wang
 * 
 */
public class DateReferCompareValidator extends AbstractReferValidator {
	
	@Override
	public ValidateResult validate(RuleVO validator, Map<String, ?> params) {
		try {
			String ruleValue = validator.getRule();
			String[] ruleValueArr = ruleValue.split(",");
			String referName = ruleValueArr[0];
			BoundryType type = resolveBoundryType(ruleValueArr[1]);
			String pattern = PATTERN_yyyy_MM_dd;
			if(ruleValueArr.length == 3){
				pattern = ruleValueArr[2];
			}
			
			long targetLong = -1;
			long referLong = -1;
			
			Object targetObject = params.get(validator.getProperty());
			Object referObject = params.get(referName);
			
			if(targetObject instanceof Long){
				targetLong = (Long)targetObject;
			}else if(targetObject instanceof Date){
				targetLong = ((Date)targetObject).getTime();
			} else{
				targetLong = DateUtils.parseDate(targetObject.toString(), new String[]{pattern,PATTERN_yyyy_MM_dd2}).getTime();
			}
			
			if(referObject instanceof Long){
				referLong = (Long)referObject;
			}else if(targetObject instanceof Date){
				referLong = ((Date)referObject).getTime();
			}else{
				referLong = DateUtils.parseDate(referObject.toString(), new String[]{pattern,PATTERN_yyyy_MM_dd2}).getTime();
			}
			
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
