/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.constant;

/**
 * 应答码列表
 * @author ryan
 * @version $Id: aaa.java, v 0.1 2016年9月6日 下午3:57:51 ryan Exp $
 */
public enum ResponseCode {
	
	RES_000000("000000","交易成功"),
	RES_ERR922("ERR922",    "创建树节点失败"),
	RES_ERR923("ERR923",    "数据库操作失败"),
	RES_ERR924 ("ERR924",   "创建树失败"),
	RES_ERR925("ERR925",	"组上主机的包失败"),
	RES_ERR926("ERR926",	"调用hicall上主机失败"),
	RES_ERR927("ERR927",	"取消息SSC头失败"),
	RES_ERR928("ERR928",	"消息SSC头为失败(不为000000)"),
	RES_ERR929("ERR929",	"取xml节点失败"),
	RES_ERR930("ERR930",	"加xml节点失败"),
	RES_ERR931("ERR931",	"组去第三方的数据包失败"),
	RES_ERR932("ERR932",	"调用hicall去第三方失败"),
	RES_ERR933("ERR933",	"取消息包头失败"),
	RES_ERR934("ERR934",	"拷贝xml节点失败"),
	RES_ERR935("ERR935",	"交易码不存在"),
	RES_ERR936("ERR936",	"交易要素不全"),
	RES_ERR937("ERR937",	"取节点出错"),
	RES_ERR938("ERR938",	"增加时间出错"),
	RES_ERR939("ERR939",	"指向根节点出错"),
	RES_ERR940("ERR940",	"法律冻结帐户"),
	RES_ERR941("ERR941",	"帐户止付"),
	RES_ERR942("ERR942",	"帐户密码挂失"),
	RES_ERR943("ERR943",	"帐户存折已正式挂失"),
	RES_ERR944("ERR944",	"帐户存折已口头挂失"),
	RES_ERR945("ERR945",	"删除sdbuserdata数据错误"),
	RES_ERR946("ERR946",	"保存sdbuserdata数据错误"),
	RES_ERR947("ERR947",	"转换失败"),
	RES_ERR948("ERR948",	"多页返回失败"),
	RES_ERR949("ERR949",	"非同一网点柜员不可授权"),
	RES_ERR950("ERR950",	"柜员不可做自授权"),
	RES_ERR951("ERR951",	"授权主管级别小于4级"),
	RES_ERR952("ERR952",	"取交易码出错"),
	RES_ERR953("ERR953",	"将消息变成树失败"),
	RES_ERR954("ERR954",	"取树根出错"),
	RES_ERR955("ERR955",	"增加柜台返回包头出错"),
	RES_ERR956("ERR956",	"增加MSG头出错"),
	RES_ERR957("ERR957",	"交易异常,请稍候查询交易结果"),
	RES_ERR999("ERR999",	"前置服务发生错误"),
	RES_ERR020("ERR020",	"无符合条件记录"),
	RES_ERR021("ERR021",	"错误的功能码"),
	RES_ERR022("ERR022",	"结算帐户必须是11对公账号"),
	RES_ERR023("ERR023",	"结算账户已开户"),
	RES_ERR024("ERR024",	"结算账户未开户"),
	RES_ERR025("ERR025",	"主机返回错误码"),
	RES_ERR026("ERR026",	"输入交易网代码错误"),
	RES_ERR027("ERR027",	"输入上级监管账号错误"),
	RES_ERR028("ERR028",	"开挂账、手续费、利息子账户失败"),
	RES_ERR029("ERR029",	"取消止付标志和实时查询标志失败"),
	RES_ERR030("ERR030",	"请求错误"),
	RES_ERR031("ERR031",	"输入的子账户错误"),
	RES_ERR032("ERR032",	"监管类服务联行号不能为空"),
	RES_ERR033("ERR033",	"输入到出入金账户不存在或与子账号不匹配"),
	RES_ERR034("ERR034",	"输入的出入金账号已存在"),
	RES_ERR035("ERR035",	"输入的上级监管账号错误"),
	RES_ERR036("ERR036",	"不能在网银维护监管类服务子账号"),
	RES_ERR037("ERR037",	"只有监管类服务才允许开实体子账户"),
	RES_ERR038("ERR038",	"仍有子账户余额不为0，不能销户"),
	RES_ERR039("ERR039",	"该出入金账号已销户"),
	RES_ERR040("ERR040",	"找不到修改密码的记录，检查输入的监管账号和会员代码是否正确"),
	RES_ERR041("ERR041",	"该子账号不存在"),
	RES_ERR042("ERR042",	"金额不能小于或等于0"),
	RES_ERR043("ERR043",	"监管类服务不允许进行此交易"),
	RES_ERR044("ERR044",	"可用余额不足"),
	RES_ERR045("ERR045",	"转入和转出子账户必须同属一个交易网"),
	RES_ERR046("ERR046",	"转账方式有误"),
	RES_ERR047("ERR047",	"打开文件错误"),
	RES_ERR048("ERR048",	"上传文件出错"),
	RES_ERR049("ERR049",	"下载文件出错"),
	RES_ERR050("ERR050",	"密码错误"),
	RES_ERR051("ERR051",	"主监管账号已存在"),
	RES_ERR052("ERR052",	"非开户网点不可操作"),
	RES_ERR053("ERR053",	"项目编号错误"),
	RES_ERR054("ERR054",	"监管账号可用余额与账面余额不符"),
	RES_ERR055("ERR055",	"输入账号错误"),
	RES_ERR056("ERR056",	"输入的交易网名称错误"),
	RES_ERR057("ERR057",	"该网银客户号已被使用，不能同时开监管户和会员户"),
	RES_ERR058("ERR058",	"保证金金额不能小于等于0"),
	RES_ERR059("ERR059",	"发起渠道有误"),
	RES_ERR060("ERR060",	"出金账号名称错误"),
	RES_ERR061("ERR061",	"计算手续费出错"),
	RES_ERR062("ERR062",	"交易网的IP和端口没配置"),
	RES_ERR064("ERR064",	"资金池余额已达设定的警戒数,无法再做交易"),
	RES_ERR065("ERR065",	"手续费子账号错误"),
	RES_ERR067("ERR067",	"买卖扎差数小于调出金额"),
	RES_ERR068("ERR068",	"此监管账号为主账号,仍有下级监管账号未销户"),
	RES_ERR069("ERR069",	"查询类服务,不允许开虚监管账号"),
	RES_ERR070("ERR070",	"查询类服务,不允许开虚监管账号"),
	RES_ERR071("ERR071",	"获取前置流水号失败"),
	RES_ERR072("ERR072",	"生成层次序列码失败"),
	RES_ERR073("ERR073",	"获取支付系统的IP为空"),
	RES_ERR074("ERR074",	"交易网返回失败描述"),
	RES_ERR075("ERR075",	"不落地转账失败或其他错误"),
	RES_ERR076("ERR076",	"支付系统返回交易异常,等待第二天对账结果"),
	RES_ERR077("ERR077",	"此监管账号下仍有实子账户未销户"),
	RES_ERR078("ERR078",	"银行前置与第三方系统通讯超时"),
	RES_ERR079("ERR079",	"流化XML文件失败"),
	RES_ERR080("ERR080",	"清算文件校验没通过"),
	RES_ERR081("ERR081",	"该交易网不支持实子账户的电子盘清算"),
	RES_ERR082("ERR082",	"清算头寸不足,停止清算"),
	RES_ERR083("ERR083",	"银行台帐不平,对账失败"),
	RES_ERR084("ERR084",	"上一次清算未完成,不允许再次触发清算"),
	RES_ERR085("ERR085",	"币种错误"),
	RES_ERR086("ERR086",	"输入的服务类型错误"),
	RES_ERR087("ERR087",	"不能重复签到"),
	RES_ERR088("ERR088",	"不能重复签退"),
	RES_ERR089("ERR089",	"签到/签退日期不符"),
	RES_ERR090("ERR090",	"非存管类服务不允许进行此交易"),
	RES_ERR091("ERR091",	"交易网未签到"),
	RES_ERR093("ERR093",	"交易网会员代码输入错误"),
	RES_ERR094("ERR094",	"交易网未签退,请签退后再进行清算"),
	RES_ERR095("ERR095",	"该会员当天有出入金或清算交易,不允许销户"),
	RES_ERR096("ERR096",	"非全量清算,停止清算"),
	RES_ERR097("ERR097",	"账号需为普通子账号或清收子账号"),
	RES_ERR098("ERR098",	"出金申请当日未审核，已失效"),
	RES_ERR099("ERR099",	"上一次对账正在进行，暂不允许发起对账"),
	RES_ERR100("ERR100",	"tp存取文件失败"),
	RES_ERR101("ERR101",	"当日清算之后不允许再次签到"),
	RES_ERR102("ERR102",	"清收头寸小于0，不允许签到"),
	RES_ERR176("ERR176",	"企业营业状态不正常，为吊销/注销"),
	RES_ERR178("ERR178",	"已开立过平安易宝，无须申请"),
	RES_ERR179("ERR179",	"重复申请平安易宝，请等待审批"),
	RES_ERR180("ERR180",	"企业照面信息不存在"),
	RES_ERR181("ERR181",	"查询企业照面信息与输入信息不符"),
	RES_ERR182("ERR182",	"小额鉴权转账失败，请稍后重试"),
	RES_ERR183("ERR183",   "请从交易平台办理出金业务"),
	RES_ERR184("ERR184",   "该业务必需从交易平台办理,谢谢!"),
	RES_ERR185("ERR185",   "执行RemoteCall出现程序执行异常,请查询日志"),
	RES_111111	("工商查询无记录","请柜员审核后签约");
	
	private String code;
	private String desc;

	private ResponseCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
