package cn.javaframe.validator.logic.adapter;

import java.util.List;
import java.util.Map;

import cn.javaframe.validator.bean.LogicValidateResult;
import cn.javaframe.validator.logic.LogicGroup;

/**
 * 逻辑组校验OR类型的集成适配器
 * @author wangxinchun1988@163.com
 * @date 2013-12-1下午1:05:36
 */
public class OrLogicGroupAdapter extends LogicGroupAdapter {

	public OrLogicGroupAdapter(List<LogicGroup> list) {
		this.list = list;
	}
	@Override
	public LogicValidateResult executeLogic(Map<String, String> params) {
		if(list == null || list.size() <= 0){
			return LogicValidateResult.SUCCESS;
		}else {
			StringBuilder failBuilder = new StringBuilder();
			for(LogicGroup item : list){
				LogicValidateResult result = item.executeLogic(params);
				//如果验证通过
				if(result.isSuccess()){
					return result;
				}else {
					failBuilder.append(result.getMessage() + (item.equals(list.get(list.size()-1))? "":"或者"));
				}
			}
			LogicValidateResult failResult = LogicValidateResult.errorInstance(failBuilder.toString());
			return failResult;
		}
	}
}
