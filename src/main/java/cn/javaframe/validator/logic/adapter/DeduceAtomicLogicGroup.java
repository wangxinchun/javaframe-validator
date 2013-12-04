package cn.javaframe.validator.logic.adapter;

import java.util.Map;

import cn.javaframe.validator.EnumConstants.NextStepType;
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
public class DeduceAtomicLogicGroup implements LogicGroup {
	private LogicVO logic;
	public DeduceAtomicLogicGroup(final LogicVO logic) {
		this.logic = logic;
	}
	
	@Override
	public LogicValidateResult executeLogic(Map<String, String> params) {
		if(logic == null){
			throw new ValidatorConfigException();
		}
		LogicValidateResult logicResult = null;
		ValidateResult conditionResult = logic.getConditionGroup().executeCondition(params);
		//条件验证成功，那么验证结论逻辑
		if(conditionResult.isSuccess()){
			ValidateResult conclusionResult = logic.getConclusionGroup().executeCondition(params);
			//结论逻辑成功，那么设置成功的下一步
			if(conclusionResult.isSuccess()){
				logicResult = LogicValidateResult.successInstance(); 
				logicResult.setSuccessNextStep(logic.getSuccessNextStep());
			}else {//如果失败，那么激烈失败的下一步，并且设置失败原因
				logicResult = LogicValidateResult.errorInstance(logic.getTip()); //TODO
				logicResult.setFailNextStep(logic.getFailNextStep());
				if(logic.getTip()== null || logic.getTip().isEmpty()){
					logicResult.setMessage(conditionResult.getMessage());
				}else{
					logicResult.setMessage(logic.getTip());
				}
			}
		}else { //如果条件失败，那么判断条件失败的下一步
			if(logic.getConditionFailNextStep() == NextStepType.goNext){
				logicResult = LogicValidateResult.successInstance(); 
				logicResult.setFailNextStep(NextStepType.goNext);
			}else if(logic.getConditionFailNextStep() == NextStepType.returnFail){
				//如果条件失败，那么返回此逻辑验证的失败message
				logicResult = LogicValidateResult.errorInstance(logic.getTip());
				logicResult.setFailNextStep(NextStepType.returnFail);
			}
		}
		return logicResult;
	}

}
