package cn.javaframe.validator.validators;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.javaframe.validator.EnumConstants.BoundryType;
import cn.javaframe.validator.exception.ValidatorConfigException;
import cn.javaframe.validator.IValidator;


/**
 * 抽象验证器
 * 
 * @author xinchun.wang
 * 
 * @param <T>
 */
public abstract class AbstractValidator implements IValidator {
	protected static final Logger logger=LoggerFactory.getLogger(AbstractValidator.class);
	
	protected static final char leftFlag = '(';
	protected static final char leftEqualFlag = '[';
	protected static final char rightFlag = ')';
	protected static final char rightEqualFlag = ']';

	protected static final String rangeRegex = "\\[|\\]|\\(|\\)";
	protected final String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
	protected final String PATTERN_yyyy_MM_dd2 = "yyyy/MM/dd";
	
	final boolean isCorrectRange(char left,char right){
		if(left != leftFlag && left != leftEqualFlag){
			return false;
		} else if(right != rightFlag && right != rightEqualFlag){
			return false;
		}
		return true;
	}
	
	final String removeRangeFlag (String target) {
		if(target == null){
			return "";
		}
		return target.replaceAll(rangeRegex, "");
	}
	
	final  boolean numberCompare(BigDecimal target,BigDecimal refer,BoundryType referBoundry){
		if(target == null || refer == null){
			return false;
		}
		if(referBoundry == BoundryType.equals){
			return target.equals(refer);
		}else if(referBoundry == BoundryType.bigAndEqual){
			if (target.compareTo(refer) >= 0) {
				return true;
			} 
		}else if(referBoundry == BoundryType.big){
			if (target.compareTo(refer) > 0) {
				return true;
			} 
		}else if(referBoundry == BoundryType.less){
			if (target.compareTo(refer) < 0) {
				return true;
			} 
		}else if(referBoundry == BoundryType.lessAndEqual){
			if (target.compareTo(refer) <= 0) {
				return true;
			} 
		}
		return false;
	}
	
	
	final  boolean numberCompare(long target,long refer,BoundryType referBoundry){
		if(referBoundry == BoundryType.equals){
			return target==refer;
		}else if(referBoundry == BoundryType.bigAndEqual){
			if (target >= refer) {
				return true;
			} 
		}else if(referBoundry == BoundryType.big){
			if (target > refer) {
				return true;
			} 
		}else if(referBoundry == BoundryType.less){
			if (target < refer) {
				return true;
			} 
		}else if(referBoundry == BoundryType.lessAndEqual){
			if (target <= refer) {
				return true;
			} 
		}
		return false;
	}
	
	final BoundryType resolveBoundryType(String ruleBoundry) { 
		BoundryType type = null;
		if(ruleBoundry.equals("<") || ruleBoundry.equals("(")){
			type = BoundryType.less;
		} else if(ruleBoundry.equals("<=") || ruleBoundry.equals("[")){
			type = BoundryType.lessAndEqual;
		} else if(ruleBoundry.equals(">") || ruleBoundry.equals(")")){
			type = BoundryType.big;
		} else if(ruleBoundry.equals(">=") || ruleBoundry.equals("]")){
			type = BoundryType.bigAndEqual;
		} else if(ruleBoundry.equals("=")){
			type = BoundryType.equals;
		} else{
			throw new ValidatorConfigException("边界配置错误");
		}
		return type;
	}
	
	
	final void logWarn(Exception e,Object ... messages) {
		if(e != null){
			logger.warn("", e);
		}
		if(messages != null && messages.length >0){
			logger.warn("Exception details: " + StringUtils.repeat("{}; ", messages.length) , messages);
		}
	}
	final void logInfo(Exception e,Object ... messages) {
		if(e != null){
			logger.info("", e);
		}
		if(messages != null && messages.length >0){
			logger.info("Exception details: " + StringUtils.repeat("{}; ", messages.length) , messages);
		}
	}
}
