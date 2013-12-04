package cn.javaframe.validator.beans;

import java.io.Serializable;

/**
 * @author xinchun.wang 普通单程政策Bean
 * 
 *         TODO 字段类型待确认
 */
public class CommonOnewayPolicy  implements Serializable {/*

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	*//**
	 * 文件编号
	 *//*
	@Rules(value = {
			@RuleItem(name = RuleNameType.require,continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.string_length_limit,value = "[1,50]" ,tip = "最长50个字符")
	}, text = "文件编号",order = 1)
	private String policyCode = "";
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success), // 因为非必选项，所以为空的时候，直接返回success
			@RuleItem(name = RuleNameType.string_length_limit, value = "[,50]", tip = "最大50个字符") 
	        }, text = "外部政策ID",order = 2)	
	private String outPolicyId = "";
	
	*//**
	 * 航空公司
	 *//*
	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_regex, value = "\\w{2}", tip = "有且只有2个字符") 
	        }, text = "航空公司",order = 3)	
	private String carrier = "";
	
	*//**
	 * 出发地
	 *//*
	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_regex, value = "^\\w{3}", tip = "只能输入1个机场三字码")//,
			//@RuleItem(name = RuleNameType.local_type, local = "single_airport", tip = "根据三字码找不到起飞机场，请输入正确的起飞机场!") 
	        }, text = "起飞机场",order = 4)
	private String dep = ""; //出发地

	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_regex, value = "^\\w{3}([,]\\w{3}){0,99}$", tip = "最多100个机场三字码") //,
			//@RuleItem(name = RuleNameType.local_type, local = "multi_airort", tip = "根据三字码找不到起飞机场，请输入正确的起飞机场!") 
	        }, text = "到达机场",order = 5)
	private String arr = ""; //到达地
	
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.values_collection_limit, value = "0,1", tip = "取值范围错误") 
	},text = "是否包含中转",order = 6)
	private boolean containTrans = false;  //XXX 暂时不做。
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.values_collection_limit, value = "A,Y,N", tip = "取值范围错误") 
	},text = "中转机场限制",order = 7)
	private String transAirportType = ""; //XXX 暂时不做。
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.simple_values_depend, value = "transAirportType,in,Y&N", continueNext = NextStepType.stop_success) ,
			@RuleItem(name = RuleNameType.require,  tip = "不能为空")
	},text = "中转机场",order = 8) XXX 暂时不做。
	private String transAirport = "";
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.values_collection_limit, value = "A,Y,N", tip = "取值范围错误") 
	        }, text = "适用航班类型",order = 9)
	private String flightnoType = ""; // 航班号类型 A全部航班， Y适用航班，N不适用航班
	
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.simple_values_depend,value = "flightnoType,in,N&Y",continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_length_limit, value = "[1,700]", tip = "最大700个字符"),
			@RuleItem(name = RuleNameType.string_regex,value ="" , tip = "格式错误")
	        }, text = "航班号",order = 10)
	private String flightnos = ""; //航班号 
	
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误")
	        }, text = "旅行开始日期",order = 11)
	private Date flightStartDate; //旅行开始日期

	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误"),
			@RuleItem(name = RuleNameType.date_compare_now, value = ">=,yyyy-MM-dd", tip = "必须大于等于今天!"),
			@RuleItem(name = RuleNameType.date_compare_refer, value = "flightStartDate,>=,yyyy-MM-dd", tip = "旅行结束日期必须大于等于旅行开始日期!",tipType = TipType.just_rule) 
	        }, text = "旅行结束日期",order = 12)
	private Date flightEndDate; //旅行结束日期
	
	
	@Rules(value = { 
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.string_length_limit, value = "[1,200]", tip = "最大200个字符"),
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误") 
	},text = "旅行排除日期", order = 13)
	private String flightExcludeDate = "";
	
	
	@Rules(value = { 
			@RuleItem(name = RuleNameType.require, tip = "不能为空"),
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误")
			},text = "销售开始日期",order = 14)
	private Date saleStartDate;

	@Rules(value = {
			@RuleItem(name = RuleNameType.require, tip = "不能为空"),
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误"),
			@RuleItem(name = RuleNameType.date_compare_now, value = ">=,yyyy-MM-dd", tip = "必须大于等于今天!"),
			@RuleItem(name = RuleNameType.date_compare_refer, value = "flightEndDate,<=,yyyy-MM-dd", tip = "销售结束日期必须大于等于旅行结束日期",tipType = TipType.just_rule),
			@RuleItem(name = RuleNameType.date_compare_refer, value = "saleStartDate,>=,yyyy-MM-dd", tip = "销售结束日期必须大于等于销售开始日期",tipType = TipType.just_rule)
			},text = "销售结束日期", order =15)
	private Date saleEndDate;

	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, tip = "不能为空"),
			@RuleItem(name = RuleNameType.local_type, local = "week_day", tip = "格式错误") 
			},text = "班期限制",order = 16)
	private int weekdays; // 班期限制 0,1,2,3,4,5,6
	
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"),
			@RuleItem(name = RuleNameType.string_length_limit,value = "[1,200]" ,tip = "最长200个字符"),
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误") 
	        }, text = "适用舱位",order = 17)
	private String cabin = "";
	
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, tip = "不能为空"),
			@RuleItem(name = RuleNameType.values_collection_limit, value = "A,N,Y", tip = "取值范围错误") 
			},text = "代理费类型",order = 18)
	private String agencyfeeType = "";
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, tip = "不能为空"),
			@RuleItem(name = RuleNameType.values_collection_limit, value = "0,1", tip = "取值范围错误") 
			},text = "加点留钱规则",order = 19)
	private int addType; //  0:按舱位 1:按价格区间加点留钱
	
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.simple_values_depend,value = "addType,in,0",continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.require,tip = "不能为空"),
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "为大于等于0,小于100的整数或两位小数") 
	        }, text = "加点",order = 20)
	private double returnPercent; //加点

	
	@Rules(value = {
			@RuleItem(name = RuleNameType.simple_values_depend,value = "addType,in,0",continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.number_value_limit, value = "[-32768,32767]",tip = "取值范围[-32768,32767]"), 
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "必须为正负整数") 
	        }, text = "留钱", order = 21)
	private int addMoney; //留钱
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.simple_values_depend,value = "addType,in,1",continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.string_regex, value = "", tip = "格式错误") 
	}, text = "价格区间",order = 21)
	private String priceRange=""; //按价格区间返点留钱
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.number_value_limit, value = "[1,365]", tip = "取值范围错误") 
			},text = "提前出票时限",order = 22)
	private int beforeDays; //提前出票时间限制，以天为单位。
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success),
			@RuleItem(name = RuleNameType.number_value_limit, value = "[1,365]", tip = "取值范围错误"), 
			@RuleItem(name = RuleNameType.not_empty_depend, value = "beforeDays", continueNext = NextStepType.stop_success), 
			@RuleItem(name = RuleNameType.number_compare_refer, value = "beforeDays,>=", tip = "最晚出票时限应该大于等于提前出票时限") 
	},text = "最晚出票时限",order = 23)
	private int inDays;//最晚出票时间限制，以天为单位
	
	
	

	@Rules(value = {
			@RuleItem(name = RuleNameType.require,tip = "不能为空"), 
			@RuleItem(name = RuleNameType.values_collection_limit, value = "0,1", tip = "格式错误") 
	        }, text = "是否支持共享航班",order = 24)
	private boolean codeshareValid = false; // 1：支持共享航班，0：不支持共享航班
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success), // 因为非必选项，所以为空的时候，直接返回success
			@RuleItem(name = RuleNameType.string_length_limit, value = "[,300]", tip = "最大300个字符") 
	        }, text = "其他说明",order = 25)	
	private String addtionalDesc="";
	
	@Rules(value = {
			@RuleItem(name = RuleNameType.require, continueNext = NextStepType.stop_success), // 因为非必选项，所以为空的时候，直接返回success
			@RuleItem(name = RuleNameType.string_length_limit, value = "[,300]", tip = "最大300个字符") 
	        }, text = "政策备注", order = 26)	
	private String remark="";
	private Date createTime;
	private Date lastUpdate;
	
	private String batchNo="";

	private String policyMD5 = "";

	private String operator = "";

	private long id;

	private int status;
	

	public Date getSaleStartDate() {
		return saleStartDate;
	}

	public void setSaleStartDate(Date saleStartDate) {
		this.saleStartDate = saleStartDate;
	}

	public Date getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(Date saleEndDate) {
		this.saleEndDate = saleEndDate;
	}

	public String getFlightExcludeDate() {
		return flightExcludeDate;
	}

	public void setFlightExcludeDate(String flightExcludeDate) {
		this.flightExcludeDate = flightExcludeDate;
	}

	public int getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(int weekdays) {
		this.weekdays = weekdays;
	}

	public int getBeforeDays() {
		return beforeDays;
	}

	public void setBeforeDays(int beforeDays) {
		this.beforeDays = beforeDays;
	}

	public int getInDays() {
		return inDays;
	}

	public void setInDays(int inDays) {
		this.inDays = inDays;
	}

	

	public boolean isContainTrans() {
		return containTrans;
	}

	public void setContainTrans(int containTrans) {
		this.containTrans = containTrans == 1 ? true :  false;
	}

	public String getTransAirportType() {
		return transAirportType;
	}

	public void setTransAirportType(String transAirportType) {
		this.transAirportType = transAirportType;
	}

	public String getTransAirport() {
		return transAirport;
	}

	public void setTransAirport(String transAirport) {
		this.transAirport = transAirport;
	}

	public String getAgencyfeeType() {
		return agencyfeeType;
	}

	public void setAgencyfeeType(String agencyfeeType) {
		this.agencyfeeType = agencyfeeType;
	}

	public int getAddType() {
		return addType;
	}

	public void setAddType(int addType) {
		this.addType = addType;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getFlightnoType() {
		return flightnoType;
	}

	public void setFlightnoType(String flightnoType) {
		this.flightnoType = flightnoType;
	}


	public boolean isCodeshareValid() {
		return codeshareValid;
	}

	public void setCodeshareValid(int codeshareValid) {
		this.codeshareValid = codeshareValid == 1 ? true :false;
	}

	public String getAddtionalDesc() {
		return addtionalDesc;
	}

	public void setAddtionalDesc(String addtionalDesc) {
		this.addtionalDesc = addtionalDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getOutPolicyId() {
		return outPolicyId;
	}

	public void setOutPolicyId(String outPolicyId) {
		this.outPolicyId = outPolicyId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPolicyMD5() {
		return policyMD5;
	}
	
	

	public void setPolicyMD5(String policyMD5) {
		this.policyMD5 = policyMD5;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public String getFlightnos() {
		return flightnos;
	}

	public void setFlightnos(String flightnos) {
		this.flightnos = flightnos;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getFlightStartDate() {
		return flightStartDate;
	}

	public void setFlightStartDate(Date flightStartDate) {
		this.flightStartDate = flightStartDate;
	}

	public Date getFlightEndDate() {
		return flightEndDate;
	}

	public void setFlightEndDate(Date flightEndDate) {
		this.flightEndDate = flightEndDate;
	}

	public double getReturnPercent() {
		return returnPercent;
	}

	public void setReturnPercent(double returnPercent) {
		this.returnPercent = returnPercent;
	}

	public int getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(int addMoney) {
		this.addMoney = addMoney;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

*/}
