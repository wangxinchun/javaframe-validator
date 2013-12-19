package cn.javaframe.validator.service;

import cn.javaframe.validator.bean.ValidateResult;

/**
 * Bean校验服务接口
 * @author wangxinchun1988@163.com
 * @date 2013-12-19下午7:17:51
 */
public interface IBeanValidatorService {
	/**
	 * 校验服务接口
	 * @param bean 待校验的对象Bean
	 * @return
	 */
	ValidateResult validate(Object bean);
}
