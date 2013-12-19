package cn.javaframe.validator.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaframe.validator.ConvertMapAble;
import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.EnumConstants.TargetType;
import cn.javaframe.validator.annotation.ConclusionRule;
import cn.javaframe.validator.annotation.LogicRule;
import cn.javaframe.validator.annotation.Rules;
import cn.javaframe.validator.annotation.TargetBean;

@TargetBean
public class User implements ConvertMapAble{
	@Rules(targetType = TargetType.complex)
	UserCard userCard;
	
	@Rules(targetType = TargetType.complex)
	private List<Skill> list ;
	
	@Rules(
			conclusionList = {
					@ConclusionRule(id = "A",type = RuleType.not_empty,tip = "不能为空")
			},
			logicList = {
					@LogicRule(conclusion = "A",successNextStep = NextStepType.returnSuccess)
					},
			text = "用户名")
	private String username;
	
	private String province;
	
	private String city;

	
	public UserCard getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCard userCard) {
		this.userCard = userCard;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public Map<String, ?> toMap() {
		Map<String,Object> tempMap = new HashMap<String,Object>();
		tempMap.put("username", username);
		tempMap.put("userCard", userCard.toMap());
		tempMap.put("list", list);
		return tempMap;
	}

	public List<Skill> getList() {
		return list;
	}

	public void setList(List<Skill> list) {
		this.list = list;
	}
	
	
	
}
