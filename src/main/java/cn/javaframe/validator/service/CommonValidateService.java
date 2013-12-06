package cn.javaframe.validator.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.javaframe.validator.annotation.ConclusionItem;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.util.ClassHelper;

/**
 * 通用规则验证器 <br>
 * 
 * 根据cls 检索其字段上的注解，解析注解，然后校验params的信息。
 * @author xinchun.wang
 * 
 */
public class CommonValidateService extends AbstractValidateService {
	final private static ConcurrentHashMap<String, Map<Field, Rules>> cacheMap = new ConcurrentHashMap<String, Map<Field, Rules>>();
	
	/*
	 * (non-Javadoc)m
	 * 
	 * @see
	 * com.qunar.flight.tts.policy.client.validator.impl.AbstractValidateServiceImpl
	 * #validate(java.util.Map)
	 */
	@Override
	public ValidateResult validate(Map<String, String> params,Class<?> cls) {
		Map<Field, Rules> fieldRuleMap = cacheMap.get(cls.getName());
		if (fieldRuleMap == null) {
			fieldRuleMap = ClassHelper.getFieldsAndRules(cls);
			cacheMap.putIfAbsent(cls.getName(), fieldRuleMap);
		}

		for (Map.Entry<Field, Rules> item : fieldRuleMap.entrySet()) {
			Field itemField = item.getKey();
			String name = itemField.getName();
			Rules rules = item.getValue();
			if(rules == null){
				continue;
			}
			ConclusionItem[] ruleList = rules.conclusionList();
			if(ruleList == null || ruleList.length <=0){
				continue;
			}
			ValidateResult result = processRules(rules, name, params);
			if(!result.isSuccess()){
				return result;
			}
		}
		return new ValidateResult(true,null);
	}
}
