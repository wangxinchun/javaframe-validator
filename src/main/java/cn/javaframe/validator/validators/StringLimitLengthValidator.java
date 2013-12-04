package cn.javaframe.validator.validators;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.javaframe.validator.EnumConstants.BoundryType;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.ValidatorVO;
import cn.javaframe.validator.exception.ValidatorConfigException;

/**
 * 字符串长度范围限制校验器
 * 
 * @author xinchun.wang
 * 
 *         规则： <br>
 *         1、开始第一个字符必须为[ 或在(  <br>
 *         2、,最后一个字符必须为 ]或者) <br>
 *         3、中间必须有',' 字符 <br>
 *         eg: <br>
 *         1、value ="[10,20]" 10 到20个字符之间   value = "(10,20)" <br>
 *         2、value = "[10,)"  大于10个字符 包括 10 <br>
 *         3、value = "[,20]"  小于20个字符，包括20 <br>
 */
public class StringLimitLengthValidator extends AbstractValidator {

	@Override
	public ValidateResult validate(ValidatorVO validator, Map<String, String> params) {
		if(validator.getRule() == null){
			throw new ValidatorConfigException("StringLimitLengthValidator value 配置错误 ,必须以[,( 开头,以),] 结尾 ");
		}
		String ruleValue = validator.getRule().trim();
		char leftFlag =  ruleValue.charAt(0);
		char rightFlag = ruleValue.charAt(ruleValue.length()-1);
		
		if(!isCorrectRange(leftFlag, rightFlag)){
			throw new ValidatorConfigException("StringLimitLengthValidator value 配置错误 ,必须以[,( 开头,以),] 结尾 ");
		}
		
		BoundryType leftBoundry = resolveBoundryType(leftFlag+"");
		BoundryType rightBoundry = resolveBoundryType(rightFlag+"");
		
		ruleValue = removeRangeFlag(ruleValue);
		boolean veryLittleFlag = false;
		boolean veryBigFlag = false;
		if(ruleValue.startsWith(",")){
			veryLittleFlag = true;
		}
		if(ruleValue.endsWith(",")){
			veryBigFlag = true;
		}
		
		int paramValueLength = StringUtils.length(params.get(validator.getProperty()));
		//[a,b] ,(a,b),[a,b),=(a,b]
		if(veryLittleFlag == false && veryBigFlag == false) {
			String[] limitArr = ruleValue.split(",");
			String minLength = limitArr[0];
			String maxLength = limitArr[1];
			if(numberCompare(new BigDecimal(minLength),new BigDecimal(paramValueLength),leftBoundry)
					&& numberCompare(new BigDecimal(maxLength),new BigDecimal(paramValueLength),rightBoundry)){
				return ValidateResult.SUCCESS;
			}
		} else if(veryLittleFlag == true && veryBigFlag  == false){//处理无穷小的情况
			ruleValue = ruleValue.replaceAll(",", "");
			if(numberCompare(new BigDecimal(ruleValue),new BigDecimal(paramValueLength),rightBoundry)){
				return ValidateResult.SUCCESS;
			}
		}else if(veryLittleFlag == false && veryBigFlag  == true){//处理无穷大的情况
			ruleValue = ruleValue.replaceAll(",", "");
			if(numberCompare(new BigDecimal(ruleValue),new BigDecimal(paramValueLength),leftBoundry)){
				return ValidateResult.SUCCESS;
			}
		}else{
			throw new ValidatorConfigException("StringLimitLengthValidator value 配置错误 ,必须以[,( 开头,以),] 结尾 ");
		}
		return ValidateResult.errorInstance(validator.getTip());
	}
	
}
