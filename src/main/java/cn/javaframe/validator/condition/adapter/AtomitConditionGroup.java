package cn.javaframe.validator.condition.adapter;

import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;
import cn.javaframe.validator.condition.ConditionGroup;
import cn.javaframe.validator.exception.ValidatorConfigException;

/**
 * 原子校验组
 * 一个原子校验组拥有一个校验器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:04:48
 */
public class AtomitConditionGroup implements ConditionGroup {
	private ValidatorVO validator;
	public AtomitConditionGroup(final ValidatorVO validator) {
		this.validator = validator;
	}
	
	@Override
	public ValidateResult executeCondition(Map<String, String> params) {
		if(validator == null){
			throw new ValidatorConfigException();
		}
		ValidateResult result = validator.getValidator().validate(validator, params);
		return result;
	}

}
