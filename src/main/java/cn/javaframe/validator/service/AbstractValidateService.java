package cn.javaframe.validator.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.EnumConstants.TipType;
import cn.javaframe.validator.IValidateService;
import cn.javaframe.validator.IValidator;
import cn.javaframe.validator.annotation.ConclusionRule;
import cn.javaframe.validator.annotation.ConditionRule;
import cn.javaframe.validator.annotation.LogicRule;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.bean.LogicRuleVO;
import cn.javaframe.validator.bean.LogicValidateResult;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;
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
	protected static final Logger logger= LoggerFactory.getLogger(AbstractValidateService.class);
	
	/**持有Rules到其上所有校验器的缓存。结构：  Rules ->(id,ValidatorVO)的映射*/
	final private static ConcurrentHashMap<Rules,Map<String,RuleVO> > rulesCacheMap = new ConcurrentHashMap<Rules, Map<String,RuleVO>>();
	/** 持有Rules上逻辑校验逻辑的缓存。结构：Rules->LogicGroup 的映射*/
	final private static ConcurrentHashMap<Rules,LogicGroup > rulesLogicGroupCacheMap = new ConcurrentHashMap<Rules, LogicGroup>();

	/**
	 * 规则处理集合接口
	 * 
	 * @param rule   规则
	 * @param value  参数值
	 * @param params 依赖参考对象
	 * @return
	 */
	final protected ValidateResult processRules(Rules rules, String name, Map<String, String> params) {
		LogicRule[] logicArr = rules.logicList();
		if(logicArr == null || logicArr.length <=0 ){
			return ValidateResult.SUCCESS; //如果没有配置验证逻辑项，默认返回success
		}
		LogicGroup execute = rulesLogicGroupCacheMap.get(rules);
		if(execute == null){
			for(LogicRule item : logicArr) {
				String conclusion = item.conclusion();//逻辑校验规则的结论表达式
				String condition = item.condition();
				if(condition == null || condition.trim().isEmpty()){ //如果是简单逻辑，只有结论没有条件
					if(conclusion == null || conclusion.trim().isEmpty()){
						throw new LogicConfigException("没有配置conclusion逻辑" + item);
					}
					LogicRuleVO logic = initLogicVO(rules,item ,name);
					LogicGroup atomicLogicGroup = new AtomicLogicGroup(logic);
					if(execute == null){
						execute = atomicLogicGroup;
					}else {
						execute = new AndLogicGroupAdapter(Arrays.asList(execute, atomicLogicGroup));
					}
				}else {//推导验证逻辑项
					if(conclusion != null && !conclusion.trim().isEmpty() && condition != null && !condition.trim().isEmpty()){
						/* 没有缓存的逻辑组*/
						LogicRuleVO logic = initLogicVO(rules,item,name);
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
	
	/**
	 * 初始化逻辑VO
	 * @param item
	 * @param validatorMap
	 * @return
	 */
	private LogicRuleVO initLogicVO(Rules rules,LogicRule item,String name) {
		LogicRuleVO logic = new LogicRuleVO();
		Map<String, RuleVO> validatorMap = rulesCacheMap.get(rules);
		if (validatorMap == null) {
			validatorMap = resolveValidatorMapByRules(rules, name);
			rulesCacheMap.putIfAbsent(rules, validatorMap);
		}
		String conclusion = item.conclusion();
		if(conclusion != null && !conclusion.trim().isEmpty()){
			ConditionGroup conclusionGroup = ConditionGroupResolver.resolve(conclusion,validatorMap);
			logic.setConclusionGroup(conclusionGroup);
		}
		String condition = item.condition();
		if(condition != null && !condition.trim().isEmpty()){
			ConditionGroup conditionGroup = ConditionGroupResolver.resolve(condition,validatorMap);
			logic.setConditionGroup(conditionGroup);
		}
		if(item.tipType() == TipType.just_rule){
			logic.setTip(item.tip());
		}else{
			if(item.tip() != null && !item.tip().isEmpty()){
				logic.setTip(rules.text() + item.tip());
			}
		}
		
		logic.setFailNextStep(item.failNextStep());
		logic.setSuccessNextStep(item.successNextStep());
		logic.setConditionFailNextStep(item.conditionFailNextStep());
		return logic;
	}
	
	/**
	 * 解析rules上所有的校验器
	 * @param rules
	 * @param name
	 * @return
	 */
	private Map<String,RuleVO> resolveValidatorMapByRules(Rules rules ,String name){
		Map<String,RuleVO> ruleMap = new HashMap<String,RuleVO>();
		for(ConclusionRule item : rules.conclusionList()){
			RuleVO vo = new RuleVO();
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
			vo.setRuleType(item.type());
			vo.setRule(item.value());
			ruleMap.put(item.id(), vo);
		}
		
		for(ConditionRule item : rules.conditionList()){
			RuleVO vo = new RuleVO();
			if(item.dependProperty()== null || item.dependProperty().isEmpty()){
				vo.setProperty(name);
			}else {
				vo.setProperty(item.dependProperty());
			}
			vo.setRuleType(item.type());
			vo.setRule(item.value());
			ruleMap.put(item.id(), vo);
		}
		return ruleMap;
	}
	
}
