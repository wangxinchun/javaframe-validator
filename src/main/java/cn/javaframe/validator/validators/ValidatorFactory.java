package cn.javaframe.validator.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.IValidator;

/**
 * 验证器工厂
 * @author xinchun.wang
 *
 */
public class ValidatorFactory {
	/**
	 * 保存通用验证器缓存*/
	private static final Map<RuleType, IValidator> commonValidatorCacheMap = new HashMap<RuleType, IValidator>();
	/**
	 * 本地验证器缓存*/
	private static final ConcurrentHashMap<String, IValidator> localValidatorCacheMap = new ConcurrentHashMap<String, IValidator>();
	
	/**
	 * 通用验证器
	 */
	private static StringNotEmptyValidator notEmptyValidator = new StringNotEmptyValidator();
    private static StringEmptyValidator emptyValidator = new StringEmptyValidator();
    private static  StringRegxValidator stringRegxValidator = new StringRegxValidator();
    
    
    private static StringLimitLengthValidator stringLimitLengthValidator = new StringLimitLengthValidator();
    
    /** 格式型验证*/
	private static DateFormatValidator dateFormatValidator = new DateFormatValidator();
    private static NumberFormatValidator numberFormatValidator =new NumberFormatValidator();
    
    private static NumberModValidator numberModValidator = new NumberModValidator();
    private static NumberLimitValidator numberLimitValidator = new NumberLimitValidator();
    
    /** 参考型验证*/
    private static NumberReferCompareValidator numberReferCompareValidator = new NumberReferCompareValidator();
    private static DateReferCompareValidator dateReferCompareValidator = new DateReferCompareValidator();
    private static DateCompareNowValidator dateCompareNowValidator = new DateCompareNowValidator();
    
    private static ValuesLimitValidator valuesLimitValidator = new ValuesLimitValidator();
    
    
    
    /**
     * 业务相关的验证器
     */
    private static PolicyWeekDaysValidator policyWeekDaysValidator = new PolicyWeekDaysValidator();
    
    static {
    	/** 通用验证器的注册*/
		commonValidatorCacheMap.put(RuleType.empty, notEmptyValidator);
		commonValidatorCacheMap.put(RuleType.not_empty, emptyValidator);
		commonValidatorCacheMap.put(RuleType.string_regex, stringRegxValidator);
		
		commonValidatorCacheMap.put(RuleType.number_format, numberFormatValidator);
		commonValidatorCacheMap.put(RuleType.date_format, dateFormatValidator);
		
		commonValidatorCacheMap.put(RuleType.string_length_limit, stringLimitLengthValidator);
		
		commonValidatorCacheMap.put(RuleType.number_value_limit, numberLimitValidator);
		commonValidatorCacheMap.put(RuleType.number_value_mod, numberModValidator);
		
		commonValidatorCacheMap.put(RuleType.number_compare_refer, numberReferCompareValidator);
		commonValidatorCacheMap.put(RuleType.date_compare_refer, dateReferCompareValidator);
		commonValidatorCacheMap.put(RuleType.date_compare_now, dateCompareNowValidator);
		
		commonValidatorCacheMap.put(RuleType.values_collection_limit, valuesLimitValidator);
		
		
		
		/**
		 * 业务验证器的注册 此处先简单放到这里处理
		 */
		localValidatorCacheMap.put("week_day", policyWeekDaysValidator);
	}
    

	public static IValidator getCommonValidator(RuleType ruleName) {
		return commonValidatorCacheMap.get(ruleName);
	}
	
	/**
	 * 返回本地自定义的验证器
	 */
	public static IValidator getLocalValidator(String name){
		return localValidatorCacheMap.get(name);
	}

	/** 注册自定义验证器
	 */
	public void registerLocalValidator(String name,IValidator validator){
		localValidatorCacheMap.putIfAbsent(name, validator);
	}
}
