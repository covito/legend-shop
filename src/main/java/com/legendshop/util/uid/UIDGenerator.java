package com.legendshop.util.uid;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Properties;
import org.hibernate.Hibernate;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

public class UIDGenerator extends AbstractUUIDGenerator implements Configurable {
	private static long _$5 = System.currentTimeMillis();
	private static short _$4 = -32768;
	private static Object _$3 = new Object();
	private static long _$2 = 1000L;
	private String _$1 = "";

	public Serializable generate(SessionImplementor paramSessionImplementor,
			Object paramObject) {

		return null;
	}

	public Serializable generate_old(
			SessionImplementor paramSessionImplementor, Object paramObject) {
		String str = paramObject.getClass().getName();
		return 64 + str.substring(str.lastIndexOf(46) + 1) + this._$1
				+ (short) getIP() + this._$1 + Math.abs((short) getJVM())
				+ this._$1 + getCount();
	}

	private static int _$1() {
		return new Object().hashCode();
	}

	public void configure(Type paramType, Properties paramProperties,
			Dialect paramDialect) {
		this._$1 = PropertiesHelper.getString("separator", paramProperties, "");
	}

	public static void main(String[] paramArrayOfString) throws Exception {
		Properties localProperties = new Properties();
		localProperties.setProperty("separator", "");
		UIDGenerator localUIDGenerator1 = new UIDGenerator();
		((Configurable) localUIDGenerator1).configure(Hibernate.STRING,
				localProperties, null);
		UIDGenerator localUIDGenerator2 = new UIDGenerator();
		((Configurable) localUIDGenerator2).configure(Hibernate.STRING,
				localProperties, null);
		for (int i = 0; i < 10; ++i) {
			String str1 = (String) localUIDGenerator1.generate(null,
					localUIDGenerator1);
			System.out.println(str1);
			String str2 = (String) localUIDGenerator2.generate(null,
					localUIDGenerator2);
			System.out.println(str2);
		}
	}
}