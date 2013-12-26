package cn.javaframe.validator.util;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import cn.javaframe.validator.beans.Skill;

/**
 * BeanHelper test case
 * @author wangxinchun1988@163.com
 * @date 2013-12-26下午8:45:37
 */
public class BeanHelperTest {

	@Test
	public void testBean2Map() {
		Skill skill = new Skill();
		skill.setName("java");
		skill.setUseYear(10);
		skill.setNote("java is a popular language !");
		Map<String, ?> beanMap = BeanHelper.bean2Map(skill);
		long l = System.currentTimeMillis();
		for(int i=0;i<300000;i++){
			beanMap = BeanHelper.bean2Map(skill);
		}
		System.out.println(System.currentTimeMillis() - l);
		System.out.println(beanMap);
		Assert.assertTrue(!beanMap.isEmpty());
	}
}
