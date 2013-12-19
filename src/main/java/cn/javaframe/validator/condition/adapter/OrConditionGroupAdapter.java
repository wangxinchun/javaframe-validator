package cn.javaframe.validator.condition.adapter;

import java.util.List;
import java.util.Map;

import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.condition.ConditionGroup;

/**
 * 逻辑组校验OR类型的集成适配器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:05:36
 */
public class OrConditionGroupAdapter extends ConditionGroupAdapter {

	public OrConditionGroupAdapter(List<ConditionGroup> list) {
		this.list = list;
	}
	@Override
	public ValidateResult executeCondition(Map<String, ?> params) {
		if(list == null || list.size() <= 0){
			return ValidateResult.SUCCESS;
		}else {
			StringBuilder failBuilder = new StringBuilder();
			for(ConditionGroup item : list){
				ValidateResult result = item.executeCondition(params);
				if(result.isSuccess()){
					return ValidateResult.SUCCESS;
				}else {
					failBuilder.append(result.getMessage()).append(item.equals(list.get(list.size()-1))? "":",或者");
				}
			}
			return ValidateResult.errorInstance(failBuilder.toString());
		}
	}
	
	@Override
	public String toString() {
		return "OrConditionGroupAdapter [list=" + list + "]";
	}
	
}
