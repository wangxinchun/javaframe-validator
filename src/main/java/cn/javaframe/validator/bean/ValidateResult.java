package cn.javaframe.validator.bean;

/**
 * 条件或者结论规则的验证结果
 * @author xinchun.wang <br>
 *
 * 因为此验证不包含下一步继续的校验逻辑，所以比 LogicValidateResult 表达的内容少
 */
public class ValidateResult {

	/** 验证结果*/
	private boolean success;

	/** 提醒信息，如果失败会有提醒信息，成功无提醒*/
	private String message;

	public static final ValidateResult SUCCESS = new ValidateResult(true, null);
	public static final ValidateResult FAIL = new ValidateResult(false, null);
	
	public static final ValidateResult errorInstance(String message){
		return new ValidateResult(false,message);
	}
	
	public ValidateResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
