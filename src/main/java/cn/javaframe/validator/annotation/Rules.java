package cn.javaframe.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.javaframe.validator.EnumConstants.LogicAssembleType;

/**
 * 验证规则集
 * @author wangxinchun1988@163.com  <br>
 * 配置在bean的成员上，代表一组校验规则集
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Rules {

	/**
	 * 验证规则集合 */
	ConclusionRule[] conclusionList();
	
	/**
	 * 校验规则依赖的条件 */
	ConditionRule[] conditionList() default {};
	
	/**
	 * 有依赖的逻辑验证规则  */
	LogicRule[] logicList() default {};
	
	/** 成员字段名称*/
	String text() default "";
	
	/** 校验顺序，默认同一order值，按照在bean中出现的先后顺序校验*/
	int order() default Integer.MIN_VALUE;
	
	/** LogicRule的组合模式，默认为AND组合*/
	LogicAssembleType assembleType() default LogicAssembleType.AND;
	
}
