package cn.javaframe.validator.validators;

import java.math.BigDecimal;
import java.util.Map;

import cn.javaframe.validator.EnumConstants.BoundryType;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;

/**
 * 数字类型的参考比较器
 * 
 * @author xinchun.wang
 */
public class NumberReferCompareValidator extends AbstractReferValidator {

	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {

		try {
			String ruleValue = validator.getRule();
			String[] ruleValueArr = ruleValue.split(",");
			BoundryType type = resolveBoundryType(ruleValueArr[1]);
			BigDecimal refer = new BigDecimal(params.get(ruleValueArr[0]));
			BigDecimal target = new BigDecimal(params.get(validator.getProperty()));
			boolean result = numberCompare(target, refer, type);
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
