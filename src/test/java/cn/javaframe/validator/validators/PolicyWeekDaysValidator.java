package cn.javaframe.validator.validators;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;

/**
 * 班期限制验证器
 * @author xinchun.wang
 * 
 */
public class PolicyWeekDaysValidator extends AbstractValidator {
	private static final Set<String> weekdaySet = new HashSet<String>();
	static {
		weekdaySet.add("0");
		weekdaySet.add("1");
		weekdaySet.add("2");
		weekdaySet.add("3");
		weekdaySet.add("4");
		weekdaySet.add("5");
		weekdaySet.add("6");
	}

	@Override
	public ValidateResult validate(RuleVO validator, Map<String, String> params) {
		String value = params.get(validator.getProperty());
		if (StringUtils.isEmpty(value) || value.startsWith(",") || value.endsWith(",")) {
			return ValidateResult.errorInstance(validator.getTip());
		} else {
			try{
				String[] valueArr = value.split(",");
				Set<Integer> rememberSet = new HashSet<Integer>(8);
				for (String item : valueArr) {
					if (!weekdaySet.contains(item)) { //如果不包含，报错
						return ValidateResult.errorInstance(validator.getTip());
					}else {
						int temp = Integer.parseInt(item) ;
						if(!rememberSet.contains(temp)){
							rememberSet.add(temp); //如果没有记忆，那么清除
						}else {
							return ValidateResult.errorInstance(validator.getTip()); // 如果已经记忆过，报错
						}
					}
				}
			}catch(Exception e){
				logWarn(e, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
			}
		}
		return ValidateResult.SUCCESS;
	}

}
