package cn.javaframe.validator.util;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import cn.javaframe.validator.beans.Skill;

public class BeanHelperTest {

	@Test
	public void testBean2Map() {
		Skill skill = new Skill();
		skill.setName("java");
		skill.setUseYear(10);
		skill.setNote("java is a popular language !");
		Map<String, ?> beanMap = BeanHelper.bean2Map(skill);
		long l = System.currentTimeMillis();
		for(int i=0;i<10000000;i++){
			beanMap = BeanHelper.bean2Map(skill);
		}
		System.out.println(System.currentTimeMillis() - l);
		System.out.println(beanMap);
		Assert.assertTrue(!beanMap.isEmpty());
	}
}
