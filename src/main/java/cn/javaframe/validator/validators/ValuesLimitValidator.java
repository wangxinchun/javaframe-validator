package cn.javaframe.validator.validators;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;
import cn.javaframe.validator.exception.ValidatorConfigException;

/**
 * 取值范围限制验证器
 * 
 * @author xinchun.wang <br>
 * 
 *         可取值范围限制 列表用',' 分割 <br>
 *         eg: value = "0,1,5" 那么仅能取值 0或在1或5 才能通过
 */
public class ValuesLimitValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {

		String ruleValue = validator.getRule();
		if (StringUtils.isEmpty(ruleValue)) {
			throw new ValidatorConfigException("ValuesLimitValidator value 格式错误,value 列表值必须用',' 分割。name :" + validator.getProperty());
		}
		try {
			String[] limitArr = ruleValue.split(",");
			String paramValue = params.get(validator.getProperty());
			for (String item : limitArr) {
				if (item.equals(paramValue)) {
					return ValidateResult.SUCCESS;
				}
			}
		} catch (Exception e) {
			logWarn(e, validator.getProperty(), params.get(validator.getProperty()), validator.getRule(), this.getClass().getName());
		}
		return ValidateResult.errorInstance(validator.getTip());
	}
}
