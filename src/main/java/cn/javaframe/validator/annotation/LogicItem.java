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
	/** 逻辑类型
	 * 1、有简单逻辑 condition 不必有值，录入也无效。
	 * 2、推导逻辑  condition 和 conclusion 必须有值*/
	public LogicType type() default LogicType.simple;

	/** 条件：逻辑条件表达式。<BR>
	 *  tips：简单逻辑没有条件的，推导逻辑才有条件*/
	public String condition() default "";

	/** 结论：要验证的结论表达式 <br>
	 * eg:(A&&B)||C 
	 * */
	public String conclusion();
	
	/** 逻辑验证成功的下一步执行逻辑  <br/>
	 *  1、NextStepType.goNext 默认进行下一个校验规则的验证。（如果词条为最后一个逻辑项，那么等同于NextStepType.returnSuccess）<br/>
	 *  2、NextStepType.returnSuccess 表示此验证完成之后，不再进行下一个校验规则的验证 ，直接返回校验成功
	 * */
	public NextStepType successNextStep() default NextStepType.goNext;
	
	/** 逻辑校验失败后下一步执行逻辑
	 * 1、NextStepType.returnFail 默认校验失败时，直接返回校验失败
	 * 2、NextStepType.goNext 校验继续下一个词条的校验（如果词条为最后一个逻辑项，那么等同于NextStepType.returnSuccess）
	 * */
	public NextStepType failNextStep() default NextStepType.returnFail;
	
	/** 条件验证失败的下一步返回类型 <br>
	 * 1、NextStepType.goNext 默认条件校验失败，进入下一个逻辑词条的校验（如果词条为最后一个逻辑项，那么等同于NextStepType.returnSuccess）<br>
	 * 2、NextStepType.returnFail 不进行下一个词条的校验，直接返回校验失败 <br>
	 * 3、NextStepType.returnSuccess 不进行下一个词条的校验，直接返回校验成功
	 * */
	public NextStepType conditionFailNextStep() default NextStepType.goNext;
	
	/**
	 * 验证失败后的提醒信息，此提醒信息优先级最高
	 */
	public String tip() default "";
	
	/**
	 * 提醒类型
	 */
	public TipType tipType() default TipType.combine;
}
