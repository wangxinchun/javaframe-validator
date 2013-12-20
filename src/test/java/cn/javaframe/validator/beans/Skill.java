package cn.javaframe.validator.beans;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.javaframe.validator.ConvertMapAble;
import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConclusionRule;
import cn.javaframe.validator.annotation.LogicRule;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.annotation.TargetBean;

@TargetBean
public class Skill  implements ConvertMapAble{
	
	@Rules(
			conclusionList = {
					@ConclusionRule(id = "A",type = RuleType.not_empty,tip = "不能为空")
			},
			logicList = {
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess)
					},
			text = "技能")
	private String name;
	
	@Rules(
			conclusionList = {
					@ConclusionRule(id = "A",type = RuleType.not_empty,tip = "不能为空"),
					@ConclusionRule(id = "B",type = RuleType.number_format,value = "int" ,tip = "必须为数字"),
					@ConclusionRule(id = "C",type = RuleType.number_value_limit,value = "[0,30]" ,tip = "使用年限范围不对"),
			},
			logicList = {
					@LogicRule(conclusion = "A&&B&&C",successNextStep = NextStepType.returnSuccess)
					},
			text = "使用年限")
	private int useYear;
	
	@Rules(
			conclusionList = {
					@ConclusionRule(id = "A",type = RuleType.empty, tip = "为空"),
					@ConclusionRule(id = "B",type = RuleType.string_length_limit,value = "[10,500]",tip = "必须在10到500个字符之间"),
			},
			logicList = {
					@LogicRule(conclusion = "A||B",successNextStep = NextStepType.returnSuccess)
					},
			text = "技能描述")
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getUseYear() {
		return useYear;
	}

	public void setUseYear(int useYear) {
		this.useYear = useYear;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public Map<String, ?> toMap() {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("name", name);
		map.put("useYear", useYear);
		map.put("note",note);
		return map;
	}
	
	

}
