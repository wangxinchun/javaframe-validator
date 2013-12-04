package cn.javaframe.validator.annotation;

import cn.javaframe.validator.EnumConstants.LogicType;
import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.TipType;

/**
 * 逻辑项注解
 * @author wangxinchun1988@163.com
 * @date 2013-12-2下午1:37:47
 */
public @interface LogicItem {
	/** 逻辑类型，有简单逻辑和推导逻辑*/
	public LogicType type() default LogicType.simple;

	/** 条件：逻辑条件表达式。<BR>
	 *  tips：简单逻辑没有条件的，推导逻辑才有条件*/
	public String condition() default "";

	/** 结论：要验证的结论表达式 <br>
	 * eg:(A&&B)||C 
	 * */
	public String conclusion();
	
	/** 校验成功后下一步的返回类型*/
	public NextStepType successNextStep() default NextStepType.goNext;
	
	/** 校验失败后下一步的返回类型*/
	public NextStepType failNextStep() default NextStepType.goNext;
	
	/** 条件验证失败的下一步返回类型*/
	public NextStepType conditionFailNextStep() default NextStepType.returnSuccess;
	
	/**
	 * 验证失败后的提醒信息
	 */
	public String tip() default "";
	
	/**
	 * 提醒类型
	 */
	public TipType tipType() default TipType.combine;
}
