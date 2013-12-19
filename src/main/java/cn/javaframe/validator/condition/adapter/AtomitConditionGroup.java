package cn.javaframe.validator.condition.adapter;

import java.util.Map;

import cn.javaframe.validator.IValidator;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;
import cn.javaframe.validator.condition.ConditionGroup;
import cn.javaframe.validator.exception.ValidatorConfigException;
import cn.javaframe.validator.validators.ValidatorFactory;

/**
 * 原子校验组
 * 一个原子校验组拥有一个校验器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:04:48
 */
public class AtomitConditionGroup implements ConditionGroup {
	private RuleVO ruleVo;
	public AtomitConditionGroup(final RuleVO ruleVo) {
		this.ruleVo = ruleVo;
	}
	
	@Override
	public ValidateResult executeCondition(Map<String, ?> params) {
		if(ruleVo == null){
			throw new ValidatorConfigException();
		}
		IValidator validator = null;
		/* 找到验证器*/
		if(RuleType.local_type == ruleVo.getRuleType()){
			String localRule = ruleVo.getLocal();
			validator = ValidatorFactory.getLocalValidator(localRule);
		} else {
			validator = ValidatorFactory.getCommonValidator(ruleVo.getRuleType());
		}
		if(validator == null){
			throw new IllegalStateException(ruleVo + "没有注册有效的验证器");
		}
		
		ValidateResult result = validator.validate(ruleVo, params);
		return result;
	}

	@Override
	public String toString() {
		return "AtomitConditionGroup [ruleVo=" + ruleVo + "]";
	}

}
