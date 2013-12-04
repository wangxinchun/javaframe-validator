package cn.javaframe.validator.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;

import cn.javaframe.validator.annotation.Rules;

/**
 * Class相关工具类
 * 
 * @author xinchun.wang
 * 
 */
public class ClassHelper {

	/**
	 * 检索cls类的所有Field字段以及其上的验证信息
	 * 
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<Field, Rules> getFieldsAndRules(Class<?> cls) {
		if (cls == null) {
			return Collections.EMPTY_MAP;
		}

		final Field[] fields = cls.getDeclaredFields();
		if (fields == null) {
			return Collections.EMPTY_MAP;
		}
		
		Map<Field, Rules> fieldRulesMap = new TreeMap<Field, Rules>(
				new Comparator<Field>() {
					@Override
					public int compare(Field o1, Field o2) {
						Rules rules1 = o1.getAnnotation(Rules.class);
						Rules rules2 = o2.getAnnotation(Rules.class);
						if (rules1.order() != Integer.MIN_VALUE && rules2.order() != Integer.MIN_VALUE) { //如果两个都有配置顺序
							if(rules1.order() == rules2.order()) { //都配置，但是配置的order顺序相等
								int index1 = ArrayUtils.indexOf(fields,o1);
								int index2 = ArrayUtils.indexOf(fields,o2);
								return index1 - index2;
							}
							return rules1.order() - rules2.order(); //都配置，order小的排在前面
						} else if (rules1.order() == Integer.MIN_VALUE) { //o1 没有配置，o2配置了
							return 1;
						} else if (rules2.order() == Integer.MIN_VALUE) { //o1 配置了，o2没有配置了
							return -1;
						}else {
							int index1 = ArrayUtils.indexOf(fields,o1);
							int index2 = ArrayUtils.indexOf(fields,o2);
							return index1 - index2;
						}
					}
				});

		for (Field item : fields) {
			Rules rules = item.getAnnotation(Rules.class);
			if (rules == null) {
				continue;
			}
			fieldRulesMap.put(item,rules);
		}
		return fieldRulesMap;
	}
}
