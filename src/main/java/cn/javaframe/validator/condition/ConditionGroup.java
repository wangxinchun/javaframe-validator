package cn.javaframe.validator.condition;

import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;

/**
 * 逻辑组校验接口
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:03:53
 */
public interface ConditionGroup {

	/**
	 * 逻辑校验方法 
	 * @param params
	 * @return
	 */
	public ValidateResult executeCondition(Map<String, String> params);

}
