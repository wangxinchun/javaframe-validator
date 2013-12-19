package cn.javaframe.validator.logic.adapter;

import java.util.List;
import java.util.Map;

import cn.javaframe.validator.EnumConstants.NextStepType;
import cn.javaframe.validator.bean.LogicValidateResult;
import cn.javaframe.validator.logic.LogicGroup;

/**
 * 逻辑组校验AND类型的集成适配器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:06:55
 */
public  class AndLogicGroupAdapter extends LogicGroupAdapter {

	public AndLogicGroupAdapter(List<LogicGroup> list) {
		this.list = list;
	}
	@Override
	public LogicValidateResult executeLogic(Map<String, ?> params) {
		if(list == null || list.size() <= 0){
			return LogicValidateResult.SUCCESS;
		}else {
			for(LogicGroup item : list){
				LogicValidateResult result = item.executeLogic(params);
				if(!result.isSuccess()){ 
					//AND类型的逻辑的组合，如果第一个失败，并且 result.getConditionFailNextStep() == NextStepType.returnFail 直接返回
					if(result.getFailNextStep() == NextStepType.returnFail){
						return result;
					}else if(result.getFailNextStep() == NextStepType.goNext){ //如果goNext 那么判断下一个and逻辑组
						continue;
					}
				}else {
					//如果当前研究组合成功，那么
					if(result.getSuccessNextStep() == NextStepType.returnSuccess){
						return result;
					}
				}
			}
			return LogicValidateResult.SUCCESS;
		}
	}
	
	@Override
	public String toString() {
		return "AndLogicGroupAdapter [list=" + list + "]";
	}
	
	
}
