package cn.javaframe.validator.service;

import java.lang.reflect.Field;
import java.util.Map;

import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.bean.ValidateResult;

/**
 * 通用规则验证器 <br>
 * 
 * 根据cls 检索其字段上的注解，解析注解，然后校验params的信息。
 * @author xinchun.wang
 * 
 */
public class MapValidateService extends AbstractValidateService   implements IMapValidateService{
	
	/*
	 * (non-Javadoc)m
	 * 
	 * @see
	 * com.qunar.flight.tts.policy.client.validator.impl.AbstractValidateServiceImpl
	 * #validate(java.util.Map)
	 */
	@Override
	public ValidateResult validate(Map<String, ?> params,Class<?> cls) {
		Map<Field, Rules> fieldRuleMap = getRuleMap(cls);
		for (Map.Entry<Field, Rules> item : fieldRuleMap.entrySet()) {
			Field itemField = item.getKey();
			Rules rules = item.getValue();
			if(rules == null){
				continue;
			}
			ValidateResult result = processRules(itemField,rules,params);
			if(!result.isSuccess()){
				return result;
			}
		}
		return new ValidateResult(true,null);
	}
}
