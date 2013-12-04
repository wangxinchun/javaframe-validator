package cn.javaframe.validator;

import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;

/**
 * 验证服务接口
 * @author xinchun.wang
 *
 */
public interface IValidateService {
	/**
	 * 校验params中的参数以及对应的值是否有效
	 * @param params
	 * @return
	 */
	ValidateResult validate(Map<String, String> params,Class<?> cls);
}
