package cn.javaframe.validator.bean;

import cn.javaframe.validator.EnumConstants.RuleType;


/**
 * 校验规则传递VO
 * 
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:15:28
 */
public class RuleVO {
	/** 校验规则*/
	private String rule;
	
	/** 待校验的字段*/
	private String property;  
	
	/**
	 * 验证规则的类型*/
	private RuleType ruleType;
	
	/** 扩展本地的校验器名称*/
	private String local;
	
	/** 校验失败提醒信息*/
	private String tip; 
	
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "RuleVO [rule=" + rule + ", property=" + property
				+ ", ruleType=" + ruleType + ", local=" + local + ", tip="
				+ tip + "]";
	}

	
	
}
