package cn.javaframe.validator.service;

import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;

/**
 * Map验证服务接口
 * @author xinchun.wang
 *
 */
public interface IMapValidateService {
	/**
	 * 校验params中的参数以及对应的值是否有效
	 * @param params
	 * @return
	 */
	ValidateResult validate(Map<String, ?> params,Class<?> cls);
}
