package cn.javaframe.validator.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.javaframe.validator.EnumConstants.LogicType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.EnumConstants.TipType;
import cn.javaframe.validator.IValidateService;
import cn.javaframe.validator.IValidator;
import cn.javaframe.validator.annotation.ConditionItem;
import cn.javaframe.validator.annotation.LogicItem;
import cn.javaframe.validator.annotation.RuleItem;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.bean.LogicVO;
import cn.javaframe.validator.bean.LogicValidateResult;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;
import cn.javaframe.validator.condition.ConditionGroup;
import cn.javaframe.validator.condition.ConditionGroupResolver;
import cn.javaframe.validator.exception.LogicConfigException;
import cn.javaframe.validator.logic.LogicGroup;
import cn.javaframe.validator.logic.adapter.AndLogicGroupAdapter;
import cn.javaframe.validator.logic.adapter.AtomicLogicGroup;
import cn.javaframe.validator.logic.adapter.DeduceAtomicLogicGroup;
import cn.javaframe.validator.validators.ValidatorFactory;

/**
 * 验证接口的抽象实现
 * 
 * @author xinchun.wang
 * 
 */
public abstract class AbstractValidateService implements IValidateService {
	final protected static ConcurrentHashMap<String, Map<Field, Rules>> cacheMap = new ConcurrentHashMap<String, Map<Field, Rules>>();
	final private static ConcurrentHashMap<Rules,Map<String,ValidatorVO> > rulesCacheMap = new ConcurrentHashMap<Rules, Map<String,ValidatorVO>>();
    
	
	final private static ConcurrentHashMap<Rules,LogicGroup > rulesLogicGroupCacheMap = new ConcurrentHashMap<Rules, LogicGroup>();
	protected static final Logger logger= LoggerFactory.getLogger(AbstractValidateService.class);

	/**
	 * 规则处理集合接口
	 * 
	 * @param rule   规则
	 * @param value  参数值
	 * @param params 依赖参考对象
	 * @return
	 */
	final protected ValidateResult processRules(Rules rules, String name, Map<String, String> params) {
		//必选验证逻辑项
		LogicItem[] logicArr = rules.logicList();
		
		if(logicArr == null || logicArr.length <=0 ){
			return ValidateResult.SUCCESS;
		}
		LogicGroup execute = rulesLogicGroupCacheMap.get(rules);
		if(execute == null){
			for(LogicItem item : logicArr) {
				String conclusion = item.conclusion();
				if(item.type() == LogicType.simple){
					//无推导逻辑
					Map<String,ValidatorVO> ruleMap = rulesCacheMap.get(rules);
					if(ruleMap == null){
						ruleMap = initValidatorMapByRules(rules,name);
						rulesCacheMap.putIfAbsent(rules, ruleMap);
					}
					ConditionGroup logicGroup = ConditionGroupResolver.resolve(conclusion,ruleMap);
					LogicVO logic = new LogicVO();
					logic.setConclusionGroup(logicGroup);
					LogicGroup atomicLogicGroup = new AtomicLogicGroup(logic);
					if(execute == null){
						execute = atomicLogicGroup;
					}else {
						execute = new AndLogicGroupAdapter(Arrays.asList(execute, atomicLogicGroup));
					}
				}else {//推导验证逻辑项
					//有推导逻辑
					String condition = item.condition();
					if(conclusion != null && !conclusion.trim().isEmpty() && condition != null && !condition.trim().isEmpty()){
						/* 没有缓存的逻辑组*/
						Map<String, ValidatorVO> ruleMap = rulesCacheMap.get(rules);
						if (ruleMap == null) {
							ruleMap = initValidatorMapByRules(rules, name);
							rulesCacheMap.putIfAbsent(rules, ruleMap);
						}
						ConditionGroup conditionGroup = ConditionGroupResolver.resolve(condition,ruleMap);
						ConditionGroup conclusionGroup = ConditionGroupResolver.resolve(conclusion,ruleMap);
						
						LogicVO logic = new LogicVO();
						logic.setConclusionGroup(conclusionGroup);
						logic.setConditionGroup(conditionGroup);
						logic.setFailNextStep(item.failNextStep());
						logic.setSuccessNextStep(item.successNextStep());
						logic.setConditionFailNextStep(item.conditionFailNextStep());
						LogicGroup deduceLogic = new DeduceAtomicLogicGroup(logic);
						if(execute == null) {
							execute = deduceLogic;
						}else{
							execute = new AndLogicGroupAdapter(Arrays.asList(execute,deduceLogic));
						}
					} else{
						throw new LogicConfigException(item+ " 推导逻辑配置错误 ");
					}
					
					
				}
			}
		}
		LogicValidateResult result = execute.executeLogic(params);
		if(result.isSuccess()){
			return ValidateResult.SUCCESS;
		}else{
			return ValidateResult.errorInstance(result.getMessage());
		}
	}
	
	
	private Map<String,ValidatorVO> initValidatorMapByRules(Rules rules ,String name){
		Map<String,ValidatorVO> ruleMap = new HashMap<String,ValidatorVO>();
		for(RuleItem item : rules.ruleList()){
			ValidatorVO vo = new ValidatorVO();
			IValidator validator = null;
			/* 找到验证器*/
			if(RuleType.local_type == item.type()){
				String localRule = item.local();
				validator = ValidatorFactory.getLocalValidator(localRule);
			} else {
				validator = ValidatorFactory.getCommonValidator(item.type());
			}
			if(validator == null){
				throw new IllegalStateException(item + "没有注册有效的验证器");
			}
			
			vo.setProperty(name);
			if(item.tipType() == TipType.combine){
				vo.setTip(rules.text()+item.tip());
			}else{
				vo.setTip(item.tip());
			}
			vo.setValidator(validator);
			vo.setRule(item.value());
			ruleMap.put(item.id(), vo);
		}
		
		for(ConditionItem item : rules.conditionList()){
			ValidatorVO vo = new ValidatorVO();
			IValidator validator = null;
			/* 找到验证器*/
			if(RuleType.local_type == item.type()){
				String localRule = item.local();
				validator = ValidatorFactory.getLocalValidator(localRule);
			} else {
				validator = ValidatorFactory.getCommonValidator(item.type());
			}
			if(validator == null){
				throw new IllegalStateException(item + "没有注册有效的验证器");
			}
			vo.setProperty(item.dependProperty());
			vo.setValidator(validator);
			vo.setRule(item.value());
			ruleMap.put(item.id(), vo);
		}
		return ruleMap;
	}
	
}
