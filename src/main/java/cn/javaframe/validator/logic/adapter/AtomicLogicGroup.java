package cn.javaframe.validator.logic.adapter;

import java.util.Map;

import cn.javaframe.validator.bean.LogicRuleVO;
import cn.javaframe.validator.bean.LogicValidateResult;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.exception.ValidatorConfigException;
import cn.javaframe.validator.logic.LogicGroup;

/**
 * 逻辑原子校验组
 * 一个原子校验组对应一个逻辑校验规则
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:04:48
 */
public class AtomicLogicGroup implements LogicGroup {
	private LogicRuleVO logic;
	public AtomicLogicGroup(final LogicRuleVO logic) {
		this.logic = logic;
	}
	
	@Override
	public LogicValidateResult executeLogic(Map<String, String> params) {
		if(logic == null){
			throw new ValidatorConfigException();
		}

		LogicValidateResult logicResult = null;
		ValidateResult result = logic.getConclusionGroup().executeCondition(params);
		//结论逻辑成功，那么设置成功的下一步
		if(result.isSuccess()){
			logicResult = LogicValidateResult.successInstance();
			logicResult.setSuccessNextStep(logic.getSuccessNextStep());
		}else {//如果失败，那么继续失败的下一步，并且设置失败原因
			logicResult = LogicValidateResult.errorInstance(logic.getTip());
			logicResult.setFailNextStep(logic.getFailNextStep());
			logicResult.setConditionFailNextStep(logic.getConditionFailNextStep());
			if(logic.getTip()== null || logic.getTip().isEmpty()){
				logicResult.setMessage(result.getMessage());
			}
		}
		return logicResult;
	}

}
