package cn.javaframe.validator.condition;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.javaframe.validator.bean.ValidatorVO;

public class ConditionGroupResolverTest {

	@Test
	public void testTrimLogic() {
		Map<String, ValidatorVO> ruleMap = new HashMap<String, ValidatorVO>();
		ruleMap.put("a", new ValidatorVO());
		ruleMap.put("b", new ValidatorVO());
		ruleMap.put("c", new ValidatorVO());
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
	

}
