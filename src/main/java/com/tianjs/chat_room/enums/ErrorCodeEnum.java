package com.tianjs.chat_room.enums;

/**
 * 错误码
 */
public enum ErrorCodeEnum {
	
	/**
	 * 系统异常
	 */
	systemError(-1, "系统繁忙"),
	emptyError(1, "不能为空"),
	nonlicetError(2, "非法请求"),
	mustLoginError(1000, "请先登录"),
	loginTimeOutError(1001, "登录超时，请重新登录"),
	accountExistsError(1002, "手机号码已经注册过 了，请核实后再提交"),
	PwdError(1010, "账号或 密码错误！"),
	HandPwdError(1003, "手势密码错误"),
	sendCodeError(1004, "验证码发送失败"),
	accountLockError(1005, "您的账号被锁定"),
	realNameAuthError(1006, "实名认证不通过，请核实后再提交"),
	notPhoneError(1007,"账号必须为手机号码"),
	notIdCardError(1008,"输入的不是身份证号码"),
	phoneCodeError(1009,"请输入正确的手机号码"),
	payError(2000, "支付失败"),
	notBindCardError(2001, "请先绑定银行卡"),
	bindedCardError(2002, "不能重复绑卡"),
	cardBindError(2005, "已经绑定过同一张银行卡"),
	raiseEndError(2003, "资产募集已结束，请关注其他产品"),
	cardbinError(2004, "获取卡bin失败"),
	amountOverrunError(2006, "支付超限额，请调整金额"),
	decryptError(3000, "解密失败"),
	encryptError(3003,"加密失败"),
	newHandError(3002,"您已不是新手用户,无法购买新手产品"),
	checkSignError(3001,"验签失败"),
	notConfirmError(3004,"此客户还没授权，请引导客户授权"),
	;

	// code值
	private final int value;
	// 话术
	private final String message;

	ErrorCodeEnum(int value, String message) {
		this.value = value;
		this.message = message;
	}

	public int getValue() {
		return this.value;
	}

	public String getMessage() {
		return this.message;
	}
}
