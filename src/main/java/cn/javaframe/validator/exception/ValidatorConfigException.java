package cn.javaframe.validator.exception;

/**
 * 校验器规则配置错误
 * 
 * @author xinchun.wang
 * 
 */
public class ValidatorConfigException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidatorConfigException() {
	}

	public ValidatorConfigException(String message) {
		super(message);
	}
}
