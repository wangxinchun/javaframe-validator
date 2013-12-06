package cn.javaframe.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证规则集
 * 
 * @author xinchun.wang
 * 
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Rules {
	
	/**
	 * 验证规则集合
	 */
	ConclusionItem[] conclusionList();
	
	/**
	 * 校验规则依赖的条件
	 * @return
	 */
	ConditionItem[] conditionList() default {};
	
	/**
	 * 有依赖的逻辑验证规则
	 */
	LogicItem[] logicList() default {};
	
	/** 字段描述*/
	String text() default "";
	
	/** 校验顺序*/
	int order() default Integer.MIN_VALUE;
}
