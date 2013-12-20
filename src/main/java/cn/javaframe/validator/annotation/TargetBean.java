package cn.javaframe.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验目标Bean注解 <br>
 * note: 在bean上标注此注解，表明对象适用校验
 * @author wangxinchun1988@163.com
 * @date 2013-12-20下午3:55:18
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetBean {

}
