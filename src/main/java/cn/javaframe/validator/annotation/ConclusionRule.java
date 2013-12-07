package cn.javaframe.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.EnumConstants.TipType;

/**
 * 推导结果项注解
 * @author xinchun.wang
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConclusionRule {

	/** 规则项的位唯一id属性*/
	public String id();
	
	/**
	 * 验证规则名字
	 */
	public RuleType type() default RuleType.local_type;

	/**
	 * 验证规则值
	 */
	public String value() default "";

	/**
	 * 此验证失败后的提醒信息，如果没有配置那么从ConclusionItem 取tip信息
	 */
	public String tip() default "";
	
	/**
	 * 提醒类型
	 * @return
	 */
	public TipType tipType() default TipType.combine;
	
	/**
	 * 扩展本地校验规则
	 * @return
	 */
	public String local() default "";
	
}
