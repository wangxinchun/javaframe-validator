package cn.javaframe.validator;

/**
 * 模块相关常量
 * @author xinchun.wang
 *
 */
abstract public class EnumConstants {
	
	/**
	 * 规则名字枚举 
	 */
    public enum RuleType{
    	/** 必填项*/
    	empty,
    	not_empty,
    	
    	/** 正则验证*/
    	string_regex,
    	
    	/** 格式校验*/
    	number_format,
    	date_format,
    	
    	/** 和当前时间比较  <br>
    	 *  规则：<br>
    	 *  1、必须有,号分隔 <br>
    	 *  2、必须有比较字符
    	 * eg: value = ">=,yyyy-MM-dd"
    	 * */
    	date_compare_now,
    	
    	/** 字符串长度限制
    	 * 规则：
    	 * 1、开始第一个字符必须为[ 或在( ,最后一个字符必须为 ]或者)
    	 * 2、中间必须有',' 字符
    	 * eg:
    	 *  1、value = "[10,20]"   value = "(10,20)"  
    	 *  2、value = "[10,)" 
    	 *  3、value = "[,20]" 
    	 * */
    	string_length_limit, 
    	
    	/** 值区间限制      
    	 * eg: value = "[2,12]", 
    	 *     value = "(2,12)",
    	 *     value = "[2,12)"
    	 *     value = "(2,12)"
    	 * */
    	number_value_limit,
    	
    	/** 取模限制 value = "5,3"*/
    	number_value_mod,
    	
    	
    	
    	/** 可取值范围限制   列表用',' 分割<br>
    	 * eg: value = "0,1,5" 那么仅能取值 0或在1或5 才能通过
    	 *  */
    	values_collection_limit,
    	
    	
    	/** 二元 依赖限制*/
    	
    	
    	/** 
    	 * eg: value = "referProperty,>="
    	 * */
    	number_compare_refer,
    	/** 数据参考比较 <br>
    	 * eg： <br>
    	 *     value = "startDate,>,yyyy-MM-dd"  <br>
    	 *     value = "startDate,>=,yyyy-Mm-dd" <br>
    	 *     value = "startDate,=,yyyy-MM-dd"
    	 * */
    	date_compare_refer,
    	
    	/** 本地自定义验证器*/
    	local_type
    }
    

	public enum BoundryType{
		equals,
		bigAndEqual,
		big,
		less,
		lessAndEqual
	}
	
	/** 提醒类型
	 */
	public enum TipType {
		/** 紧提醒rule上的规则*/
		just_rule,
		/** 会使用rules上的字段文本+rule上的规则文本*/
		combine
	}
	
	
	/** 规则校验结果*/
    public enum NextStepType {
    	returnSuccess,
    	goNext,
    	returnFail
    }
	
}
