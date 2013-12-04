package cn.javaframe.validator.exception;

/**
 * 逻辑规则配置错误
 * @author wangxinchun1988@163.com
 * @date 2013-12-2下午11:01:30
 */
public class LogicConfigException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicConfigException() {
	}

	public LogicConfigException(String message) {
		super(message);
	}
}
