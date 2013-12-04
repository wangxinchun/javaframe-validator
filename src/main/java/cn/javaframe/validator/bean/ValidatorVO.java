package cn.javaframe.validator.bean;

import cn.javaframe.validator.IValidator;


/**
 * 逻辑传递VO
 * 
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:15:28
 */
public class ValidatorVO {
	private String rule; // 校验规则
	private String property; // 待校验的字段
	private String tip; // 校验失败提醒信息

	private IValidator validator;
	
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

	public IValidator getValidator() {
		return validator;
	}

	public void setValidator(IValidator validator) {
		this.validator = validator;
	}
	
	
}
