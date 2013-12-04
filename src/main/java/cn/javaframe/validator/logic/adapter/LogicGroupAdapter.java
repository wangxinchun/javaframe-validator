package cn.javaframe.validator.logic.adapter;

import java.util.List;

import cn.javaframe.validator.logic.LogicGroup;

/**
 * 逻辑组校验适配器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:06:08
 */
public abstract class LogicGroupAdapter implements LogicGroup {
	protected List<LogicGroup> list;
	
}
