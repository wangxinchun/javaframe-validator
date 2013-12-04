package cn.javaframe.validator.logic.adapter;

import java.util.Map;

import cn.javaframe.validator.bean.LogicVO;
import cn.javaframe.validator.bean.LogicValidateResult;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.exception.ValidatorConfigException;
import cn.javaframe.validator.logic.LogicGroup;

/**
 * 原子校验组
 * 一个原子校验组拥有一个校验器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:04:48
 */
public class AtomicLogicGroup implements LogicGroup {
	private LogicVO logic;
	public AtomicLogicGroup(final LogicVO logic) {
		this.logic = logic;
	}
	
	@Override
	public LogicValidateResult executeLogic(Map<String, String> params) {
		LogicValidateResult logicResult = LogicValidateResult.successInstance();
		if(logic == null){
			throw new ValidatorConfigException();
		}

		ValidateResult result = logic.getConclusionGroup().executeCondition(params);
		//结论逻辑成功，那么设置成功的下一步
		if(result.isSuccess()){
			logicResult.setSuccessNextStep(logic.getSuccessNextStep());
		}else {//如果失败，那么激烈失败的下一步，并且设置失败原因
			logicResult.setFailNextStep(logic.getFailNextStep());
			if(logic.getTip()== null || logic.getTip().isEmpty()){
				logicResult.setMessage(result.getMessage());
			}else{
				logicResult.setMessage(logic.getTip());
			}
		}
		return logicResult;
	}

}
