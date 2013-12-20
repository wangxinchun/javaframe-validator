package cn.javaframe.validator.beans;

import java.util.Date;
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
 * 身份证信息
 * @author wangxinchun1988@163.com
 * @date 2013-12-18下午3:53:06
 * 
 * 
 * * 1、createDate 可以为空，endDate 也可以为空，如果不为空，那么他们必须是yyyy-MM-dd的时间格式
 *   2、如果endDate不为空，那么endDate的时间必须大于当前时间
 *   3、如果createDate不为空，并且endDate不为空，endDate 必须大于createDate的时间
 *   4、cardID身份证号，不能为空，并且长度必须为14 或者 18 个字符
 */
@TargetBean
public class UserCard implements ConvertMapAble{
	@Rules(
			conditionList = {
					@ConditionRule(id = "A",type = RuleType.empty)
			},
			conclusionList = {
					@ConclusionRule(id = "B",type = RuleType.not_empty,tip = "不能为空"),
					@ConclusionRule(id = "C",type = RuleType.string_length_limit,value = "[14,14]",tip = "必须为14个字符"),
					@ConclusionRule(id = "D",type = RuleType.string_length_limit,value = "[18,18]",tip = "必须为16个字符")
			},
			logicList = {
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.goNext),
					@LogicRule(condition = "B",conclusion = "C||D", tip = "",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.returnFail)
					},
			text = "用户身份账号")
	private String cardID;

	
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
	private Date createDate;
	
	@Rules(
			conditionList = {
					@ConditionRule(id = "A",type = RuleType.empty),
					//@ConditionRule(id = "B",type = RuleType.not_empty),
					@ConditionRule(id = "C",type = RuleType.not_empty,dependProperty = "createDate")
				},
			conclusionList = {
					@ConclusionRule(id = "D",type = RuleType.date_format,value = "yyyy-MM-dd",tip = "格式错误"),
					@ConclusionRule(id = "E",type = RuleType.date_compare_now,value = ">=,yyyy-MM-dd",tip = "必须大于当前时间"),
					@ConclusionRule(id = "F",type = RuleType.date_compare_refer,value = "createDate,>=,yyyy-MM-dd",tip = "结束时间必须大于开始时间")
			},
			logicList = {
					//如果为空，那么直接返回，如果不为空，那么直接进入下一个校验
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess,failNextStep = NextStepType.goNext),
					@LogicRule(conclusion = "D&&E"),// 此次的验证可以分开也可以合并
					@LogicRule(condition= "C", conclusion = "F") //依赖验证
			},
			text = "结束时间")
	private Date endDate;

	
	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public Map<String, ?> toMap() {
		Map<String,Object> tempMap = new HashMap<String,Object>();
		tempMap.put("cardID", cardID);
		tempMap.put("createDate", createDate);
		tempMap.put("endDate", endDate);
		return tempMap;
	}
	
	
}
