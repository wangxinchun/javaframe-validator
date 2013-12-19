package cn.javaframe.validator.condition.adapter;

import java.util.List;
import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.condition.ConditionGroup;

/**
 * 逻辑组校验AND类型的集成适配器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:06:55
 */
public  class AndConditionGroupAdapter extends ConditionGroupAdapter {

	public AndConditionGroupAdapter(List<ConditionGroup> list) {
		this.list = list;
	}
	@Override
	public ValidateResult executeCondition(Map<String, ?> params) {
		if(list == null || list.size() <= 0){
			return ValidateResult.SUCCESS;
		}else {
			for(ConditionGroup item : list){
				ValidateResult result = item.executeCondition(params);
				if(!result.isSuccess()){
					return result;
				}
			}
			return ValidateResult.SUCCESS;
		}
	}
	
	@Override
	public String toString() {
		return "AndConditionGroupAdapter [list=" + list + "]";
	}
	
}
