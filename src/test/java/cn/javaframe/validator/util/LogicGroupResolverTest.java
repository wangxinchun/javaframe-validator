package cn.javaframe.validator.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.javaframe.validator.bean.LogicVO;
import cn.javaframe.validator.logic.LogicGroup;
import cn.javaframe.validator.logic.LogicGroupResolver;

public class LogicGroupResolverTest {

	@Test
	public void testTrimLogic() {
		Map<String, LogicVO> ruleMap = new HashMap<String, LogicVO>();
		ruleMap.put("a", new LogicVO());
		ruleMap.put("b", new LogicVO());
		ruleMap.put("c", new LogicVO());
		LogicGroup group = LogicGroupResolver.resolve("(((a||b)&&(b&&c)))", ruleMap);
		Assert.assertTrue(group != null);
	}
	
	
	@Test
	public void testTrimLogic1() {
		String result = LogicGroupResolver.trimLogic("((a||b))");
		System.out.println(result);
		Assert.assertTrue(result.equals("a||b"));
	}
	
	@Test
	public void testTrimLogic2() {
		String result = LogicGroupResolver.trimLogic("a||b");
		System.out.println(result);
		Assert.assertTrue(result.equals("a||b"));
	}
	
	@Test
	public void testTrimLogic3() {
		String result = LogicGroupResolver.trimLogic("(((a||b)||(b&&c)))");
		Assert.assertTrue(result.equals("(a||b)||(b&&c)"));
	}
	

}
