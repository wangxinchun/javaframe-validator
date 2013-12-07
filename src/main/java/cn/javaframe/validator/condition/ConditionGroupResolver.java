package cn.javaframe.validator.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.javaframe.validator.bean.RuleVO;
import cn.javaframe.validator.condition.adapter.AndConditionGroupAdapter;
import cn.javaframe.validator.condition.adapter.AtomitConditionGroup;
import cn.javaframe.validator.condition.adapter.OrConditionGroupAdapter;
import cn.javaframe.validator.exception.LogicConfigException;

/**
 * 条件表达式解析器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午12:07:48
 */
public class ConditionGroupResolver {
	private static final char AND = '&';
	private static final String ANDAND = "&&";
	private static final char OR = '|';
	private static final String OROR = "||";

	private static final char left = '(';
	private static final char right = ')';

	/**
	 * 逻辑表达是的解析
	 * 
	 * @param logic  ((A||B)&&(C||D))||(F&&H)
	 * @param ruleMap 校验器id->ValidatorVO
	 * @return 返回逻辑表达式对应校验封装实现
	 */
	public static ConditionGroup resolve(String logic, Map<String, RuleVO> validatorMap) {
		logic = trimLogic(logic);
		if (logic == null || logic.trim().isEmpty()) {
			return null;
		}
		if (!logic.contains(ANDAND) && !logic.contains(OROR)) {
			RuleVO logicVO = validatorMap.get(logic);
			if (logicVO == null) {
				throw new LogicConfigException(logic + "没有对应的RuleItem");
			}
			return new AtomitConditionGroup(logicVO);
		}
		int leftCount = 0;
		int rightCount = 0;
		boolean andFlag = false;
		boolean orFlag = false;
		int lastSubIndex = 0;
		List<String> subLogicList = new ArrayList<String>();
		for (int i = 0; i < logic.length(); i++) {
			char tempChar = logic.charAt(i);
			if (tempChar == left) {
				leftCount++;
			} else if (tempChar == right) {
				rightCount++;
				if(i == logic.length()-1){
					subLogicList.add(logic.substring(lastSubIndex));
				}
			} else if (tempChar == AND && logic.charAt(i + 1) == AND) {
				if (leftCount == rightCount) {//保证操作的id不再括弧内
					andFlag = true;
					subLogicList.add(logic.substring(lastSubIndex, i));
					i++;
					lastSubIndex = i+1;
				}
			} else if (tempChar == OR && logic.charAt(i + 1) == OR) {
				if (leftCount == rightCount) { //保证操作的id不再括弧内
					orFlag = true;
					subLogicList.add(logic.substring(lastSubIndex, i));
					i++;
					lastSubIndex = i+1;
				}
			} else{
				if(i == logic.length()-1){
					subLogicList.add(logic.substring(lastSubIndex));
				}
			}
		}
		if(andFlag == orFlag){
			throw new LogicConfigException(logic+ "配置错误，最外层必须配置同一类型的逻辑分割符合");
		}
		List<ConditionGroup> listGroup = new ArrayList<ConditionGroup>();
		if (subLogicList.size() > 0) {
			for (String item : subLogicList) {
				ConditionGroup logicGroup = resolve(item, validatorMap);
				if (logicGroup != null) {
					listGroup.add(logicGroup);
				}
			}
		} else {
			throw new LogicConfigException(logic+ " ()配对不全或者 缺少逻辑符号||, && ");
		}
		ConditionGroup returnGroup;
		if (andFlag) {
			returnGroup = new AndConditionGroupAdapter(listGroup);
		} else {
			returnGroup = new OrConditionGroupAdapter(listGroup);
		}
		return returnGroup;
	}

	/**
	 * 过滤外括号
	 * @param logic
	 * @return
	 */
	public static String trimLogic(String logic) {
		if (logic == null || logic.trim().isEmpty()) {
			return null;
		}
		if (logic.charAt(0) != left || logic.charAt(logic.length() - 1) != right) {
			return logic;
		} else {
			int leftCount = 0;
			for (int i = 0; i < logic.length(); i++) {
				if (logic.charAt(i) == left) {
					leftCount++;
				} else if (logic.charAt(i) == right) {
					leftCount--;
					if (leftCount == 0 && i == logic.length() - 1) { //如果第一次和(匹配的是最后一个字符)，那么去除外括号
						return trimLogic(logic.substring(1, logic.length() - 1));
					}else if(leftCount == 0){ //如果第一次和第一个(匹配的不是最后一个字符)，那么直接返回
						return logic;
					}
				}
			}
			return logic;
		}
	}

}
