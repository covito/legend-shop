package com.legendshop.spi.constants;

import com.legendshop.model.entity.PayType;
import com.legendshop.util.constant.StringEnum;

public enum PayTypeEnum implements StringEnum {
	ALI_DIRECT_PAY, ALI_PAY, PAY_AT_GOODS_ARRIVED, TENPAY, TEN_DIRECT_PAY, PAYPAL, CHINABANK, KQ_PAY;

	private final String value = name();

	public String value() {
		return this.value;
	}

	public static boolean isAlipay(PayType payType) {
		if (payType == null) {
			return false;
		}

		return ((ALI_DIRECT_PAY.value().equals(payType.getPayTypeId())) || (ALI_PAY
				.value().equals(payType.getPayTypeId())));
	}
}