package cn.javaframe.validator.condition.adapter;

import java.util.List;

import cn.javaframe.validator.condition.ConditionGroup;

/**
 * 逻辑组校验适配器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:06:08
 */
public abstract class ConditionGroupAdapter implements ConditionGroup {
	protected List<ConditionGroup> list;
	
}
