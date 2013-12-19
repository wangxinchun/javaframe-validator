package cn.javaframe.validator;

import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;

/**
 * 校验器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:08:55
 */
public interface IValidator {
	/**
	 * 校验器统一校验接口
	 * @param rule 校验规则
	 * @param name 参数名字
	 * @param params 待校验的参数集合
	 * @return 返回此验证结果
	 */
	public ValidateResult validate(RuleVO validator, Map<String, ?> params);

}
