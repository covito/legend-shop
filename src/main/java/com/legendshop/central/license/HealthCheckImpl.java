package com.legendshop.central.license;

import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthCheckImpl implements HealthCheck {
	protected Logger log = LoggerFactory.getLogger(HealthCheckImpl.class);
	private int _$3 = 10800000;
	private final ServletContext _$2;
	private final boolean _$1 = true;

	public String check() {
		LSResponse localLSResponse;
		try {
			localLSResponse = LicenseHelper.checkLicense(this._$2);
			if (localLSResponse != null)
				return localLSResponse.getLicense();
			return LicenseEnum.FREE.name();
		} catch (Exception localException) {
		}
		return LicenseEnum.UNKNOWN.name();
	}

	public HealthCheckImpl(ServletContext paramServletContext) {
		this._$2 = paramServletContext;
	}

	public void run() {
		if (this._$2 != null)
			try {
				String str = check();
				if (LicenseEnum.instance(str))
					if ((LicenseEnum.UN_AUTH.name().equals(str))
							|| (LicenseEnum.EXPIRED.name().equals(str))) {
						this._$2.setAttribute("UN_AUTH_MSG",
								LicenseEnum.UN_AUTH.name());
					} else {
						this._$2.setAttribute("UN_AUTH_MSG", "NORMAL");
						this._$3 = 21600000;
					}
				Thread.sleep(this._$3);
			} catch (Exception localException) {
				this.log.error("HealthCheckImpl run", localException);
			}
	}
}