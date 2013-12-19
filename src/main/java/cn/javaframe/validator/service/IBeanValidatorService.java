package cn.javaframe.validator.service;

import cn.javaframe.validator.bean.ValidateResult;

public interface IBeanValidatorService {
	ValidateResult validate(Object bean);
}
