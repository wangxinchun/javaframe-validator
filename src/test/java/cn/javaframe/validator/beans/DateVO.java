package cn.javaframe.validator.beans;

import java.util.HashMap;
import java.util.Map;

import cn.javaframe.validator.ConvertMapAble;
import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConclusionRule;
import cn.javaframe.validator.annotation.ConditionRule;
import cn.javaframe.validator.annotation.LogicRule;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.annotation.TargetBean;

/**
 * 验证要求：
 * 1、begin 可以为空，end 也可以为空，如果不为空，那么他们必须是yyyy-MM-dd的时间格式
 * 2、如果end不为空，那么end的时间必须大于当前时间
 * 3、如果begin不为空，并且end不为空，end 必须大于begin的时间
 * @author wangxinchun
 *
 */
@TargetBean
public class DateVO implements ConvertMapAble{
	@Rules(
			conditionList = {
				@ConditionRule(id = "A",type = RuleType.empty),
				@ConditionRule(id = "B",type = RuleType.not_empty)
			},
			conclusionList = {
					@ConclusionRule(id = "C",type =RuleType.date_format ,value = "yyyy-MM-dd",tip = "格式错误")
			},
			logicList = {
					 //如果为空，那么直接success，如果失败继续下一个规则的校验
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.goNext),
					@LogicRule(condition = "B", conclusion = "C") //此次的B配置可以去掉，因A成功，已经返回，所以B条件肯定成立
			},
			text = "开始时间")
	private String begin;
	
	@Rules(
			conditionList = {
					@ConditionRule(id = "A",type = RuleType.empty),
					//@ConditionRule(id = "B",type = RuleType.not_empty),
					@ConditionRule(id = "C",type = RuleType.not_empty,dependProperty = "begin")
				},
			conclusionList = {
					@ConclusionRule(id = "D",type = RuleType.date_format,value = "yyyy-MM-dd",tip = "格式错误"),
					@ConclusionRule(id = "E",type = RuleType.date_compare_now,value = ">=,yyyy-MM-dd",tip = "必须大于当前时间"),
					@ConclusionRule(id = "F",type = RuleType.date_compare_refer,value = "begin,>=,yyyy-MM-dd",tip = "结束时间必须大于开始时间")
			},
			logicList = {
					//如果为空，那么直接返回，如果不为空，那么直接进入下一个校验
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.goNext),
					@LogicRule(conclusion = "D&&E"),// 此次的验证可以分开也可以合并
					@LogicRule(condition= "C", conclusion = "F") //依赖验证
			},
			text = "结束时间")
	private String end;

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public Map<String, ?> toMap() {
	    Map<String,Object> tempMap = new HashMap<String,Object>();
	    tempMap.put("begin", begin);
	    tempMap.put("end", end);
		return tempMap;
	}

	

}
