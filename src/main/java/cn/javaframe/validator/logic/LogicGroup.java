package cn.javaframe.validator.logic;

import java.util.Map;

import cn.javaframe.validator.bean.LogicValidateResult;

/**
 * 逻辑组校验接口
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:03:53
 */
public interface LogicGroup {

	/**
	 * 逻辑校验方法 
	 * @param params
	 * @return
	 */
	public LogicValidateResult executeLogic(Map<String, ?> params);

}
