package cn.javaframe.validator.condition;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.javaframe.validator.bean.RuleVO;

public class ConditionGroupResolverTest {

	@Test
	public void testTrimLogic() {
		Map<String, RuleVO> ruleMap = new HashMap<String, RuleVO>();
		ruleMap.put("a", new RuleVO());
		ruleMap.put("b", new RuleVO());
		ruleMap.put("c", new RuleVO());
		ConditionGroup group = ConditionGroupResolver.resolve("(((a||b)&&(b&&c)))", ruleMap);
		Assert.assertTrue(group != null);
	}
	
	
	@Test
	public void testTrimLogic1() {
		String result = ConditionGroupResolver.trimLogic("((a||b))");
		Assert.assertTrue(result.equals("a||b"));
	}
	
	@Test
	public void testTrimLogic2() {
		String result = ConditionGroupResolver.trimLogic("a||b");
		Assert.assertTrue(result.equals("a||b"));
	}
	
	@Test
	public void testTrimLogic3() {
		String result = ConditionGroupResolver.trimLogic("(((a||b)||(b&&c)))");
		Assert.assertTrue(result.equals("(a||b)||(b&&c)"));
	}
	

	@Test
	public void test1(){
		int[] i = new int[10];
		System.out.print(i.getClass().isArray());
	}
}
