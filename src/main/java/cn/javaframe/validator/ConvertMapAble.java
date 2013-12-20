package cn.javaframe.validator;

import java.util.Map;

/**
 * 返回Bean对象的属性为Map结构
 * @author wangxinchun1988@163.com
 * @date 2013-12-20下午4:07:29
 */
public interface ConvertMapAble {
	public Map<String, ?> toMap ();
}
