package com.legendshop.util.handler;

import com.legendshop.jdbc.DBManager;
import com.legendshop.plugins.Plugin;
import com.legendshop.plugins.PluginConfig;
import com.legendshop.plugins.PluginManager;
import com.legendshop.plugins.PluginRuntimeException;
import com.legendshop.plugins.PluginStatusEnum;
import com.legendshop.util.AppUtils;
import com.legendshop.util.EnvironmentConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginRepository implements PluginManager {
	private final Logger _$4 = LoggerFactory.getLogger(PluginRepository.class);
	private static PluginRepository _$3 = null;
	private List<PluginConfig> _$2 = null;
	private List<Plugin> _$1 = null;

	private PluginRepository() {
		if (!(isSystemInstalled()))
			return;
		if (this._$2 == null) {
			ArrayList localArrayList = new ArrayList();
			PreparedStatement localPreparedStatement = null;
			Connection localConnection = null;
			ResultSet localResultSet = null;
			String str = "select * from ls_plugin";
			if (this._$4.isDebugEnabled())
				this._$4.debug("Running SQL:\n" + str);
			try {
				localConnection = DBManager.getInstance().getConnection();
				localPreparedStatement = localConnection.prepareStatement(str);
				localResultSet = localPreparedStatement.executeQuery();
				while (localResultSet.next()) {
					PluginConfig localPluginConfig = _$1(localResultSet);
					localArrayList.add(localPluginConfig);
				}
			} catch (Exception localException) {
				this._$4.error(
						"SQLException in DBManager.exceuteQuery, sql is :\r\n"
								+ str, Integer.valueOf(2));
				this._$4.error("executeQuery", localException);
			} finally {
				DBManager.getInstance().cleanup(localConnection,
						localPreparedStatement, localResultSet);
			}
			this._$2 = localArrayList;
		}
	}

	public static PluginRepository getInstance() {
		if (_$3 == null)
			_$3 = new PluginRepository();
		return _$3;
	}

	public List<PluginConfig> getDbPluginConfigList() {
		return this._$2;
	}

	private PluginConfig _$1(ResultSet paramResultSet) throws SQLException {
		PluginConfig localPluginConfig = new PluginConfig();
		localPluginConfig.setPulginId(paramResultSet.getString("plugin_id"));
		localPluginConfig.setPulginVersion(paramResultSet
				.getString("plugin_version"));
		localPluginConfig.setProvider(paramResultSet.getString("provider"));
		try {
			PluginStatusEnum localPluginStatusEnum = PluginStatusEnum
					.valueOf(paramResultSet.getString("status"));
			localPluginConfig.setStatus(localPluginStatusEnum);
		} catch (Exception localException1) {
			localPluginConfig.setStatus(PluginStatusEnum.N);
		}
		Boolean localBoolean = Boolean.valueOf(false);
		try {
			localBoolean = Boolean.valueOf(paramResultSet
					.getString("is_required"));
		} catch (Exception localException2) {
		}
		localPluginConfig.setRequired(localBoolean.booleanValue());
		localPluginConfig.setDescription(paramResultSet
				.getString("description"));
		return localPluginConfig;
	}

	public void registerPlugin(Plugin paramPlugin) {
		if (this._$1 == null)
			this._$1 = new ArrayList();
		this._$1.add(paramPlugin);
	}

	public synchronized void startPlugins(ServletContext paramServletContext) {
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Plugin localPlugin = (Plugin) localIterator.next();
			if (localPlugin.getPluginConfig().getStatus()
					.equals(PluginStatusEnum.Y)) {
				this._$4.info("start to init plugin {}, version {}",
						localPlugin.getPluginConfig().getPulginId(),
						localPlugin.getPluginConfig().getPulginVersion());
				localPlugin.bind(paramServletContext);
			}
		}
	}

	public synchronized void registerPlugins(Plugin paramPlugin) {
		if ((PluginStatusEnum.S.equals(paramPlugin.getPluginConfig()
				.getStatus()))
				|| (PluginStatusEnum.Y.equals(paramPlugin.getPluginConfig()
						.getStatus())))
			if (!(this._$1.contains(paramPlugin)))
				this._$1.add(paramPlugin);
			else
				throw new PluginRuntimeException("Plugin '"
						+ paramPlugin.getClass().getSimpleName()
						+ "' had been registed");
	}

	public void stopPlugins(ServletContext paramServletContext) {
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Plugin localPlugin = (Plugin) localIterator.next();
			if (localPlugin.getPluginConfig().getStatus()
					.equals(PluginStatusEnum.Y)) {
				this._$4.info("start to destory plugin {}, version {}",
						localPlugin.getPluginConfig().getPulginId(),
						localPlugin.getPluginConfig().getPulginVersion());
				localPlugin.unbind(paramServletContext);
			}
		}
	}

	public synchronized String turnOn(String paramString) {
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Plugin localPlugin = (Plugin) localIterator.next();
			if (localPlugin.getPluginConfig().getPulginId().equals(paramString)) {
				this._$4.info("turnOn plugin {}, version {}", localPlugin
						.getPluginConfig().getPulginId(), localPlugin
						.getPluginConfig().getPulginVersion());
				localPlugin.getPluginConfig().setStatus(PluginStatusEnum.Y);
				updatePluginStatus(paramString, PluginStatusEnum.Y.name());
			}
		}
		return PluginStatusEnum.Y.name();
	}

	public synchronized String turnOff(String paramString) {
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Plugin localPlugin = (Plugin) localIterator.next();
			if (localPlugin.getPluginConfig().getPulginId().equals(paramString)) {
				this._$4.info("turnOn plugin {}, version {}", localPlugin
						.getPluginConfig().getPulginId(), localPlugin
						.getPluginConfig().getPulginVersion());
				localPlugin.getPluginConfig().setStatus(PluginStatusEnum.N);
				updatePluginStatus(paramString, PluginStatusEnum.N.name());
			}
		}
		return PluginStatusEnum.N.name();
	}

	public void savePlugin(PluginConfig paramPluginConfig) {
		if (!(isSystemInstalled()))
			return;
		PreparedStatement localPreparedStatement = null;
		Connection localConnection = null;
		ResultSet localResultSet = null;
		String str = "insert into ls_plugin(plugin_id, plugin_version,provider,status,is_required,description) values(?,?,?,?,?,?)";
		if (this._$4.isDebugEnabled())
			this._$4.debug(
					"Running SQL:\n" + str.replace("?", "{}"),
					new Object[] { paramPluginConfig.getPulginId(),
							paramPluginConfig.getPulginVersion(),
							paramPluginConfig.getProvider(),
							paramPluginConfig.getStatus().name(),
							Boolean.valueOf(paramPluginConfig.isRequired()),
							paramPluginConfig.getDescription() });
		try {
			localConnection = DBManager.getInstance().getConnection();
			localPreparedStatement = localConnection.prepareStatement(str);
			localPreparedStatement
					.setString(1, paramPluginConfig.getPulginId());
			localPreparedStatement.setString(2,
					paramPluginConfig.getPulginVersion());
			localPreparedStatement
					.setString(3, paramPluginConfig.getProvider());
			localPreparedStatement.setString(4, paramPluginConfig.getStatus()
					.name());
			localPreparedStatement
					.setBoolean(5, paramPluginConfig.isRequired());
			localPreparedStatement.setString(6,
					paramPluginConfig.getDescription());
			int i = localPreparedStatement.executeUpdate();
			if (i != 1)
				throw new RuntimeException("can not inster plugin "
						+ paramPluginConfig.getPulginId());
		} catch (Exception localException) {
			this._$4.error(
					"SQLException in DBManager.exceuteUpdate, sql is :\r\n"
							+ str, Integer.valueOf(2));
			this._$4.error("executeQuery", localException);
		} finally {
			DBManager.getInstance().cleanup(localConnection,
					localPreparedStatement, localResultSet);
		}
	}

	public void updatePluginStatus(String paramString1, String paramString2) {
		PreparedStatement localPreparedStatement = null;
		Connection localConnection = null;
		ResultSet localResultSet = null;
		String str = "update ls_plugin set status = ? where plugin_id = ?";
		if (this._$4.isDebugEnabled())
			this._$4.debug("Running SQL:\n" + str.replace("?", "{}"),
					paramString2, paramString1);
		try {
			localConnection = DBManager.getInstance().getConnection();
			localPreparedStatement = localConnection.prepareStatement(str);
			localPreparedStatement.setString(1, paramString2);
			localPreparedStatement.setString(2, paramString1);
			int i = localPreparedStatement.executeUpdate();
			if (i != 1)
				throw new RuntimeException("can not update plugin "
						+ paramString1);
		} catch (Exception localException) {
			this._$4.error(
					"SQLException in DBManager.exceuteUpdate, sql is :\r\n"
							+ str, Integer.valueOf(2));
			this._$4.error("executeQuery", localException);
		} finally {
			DBManager.getInstance().cleanup(localConnection,
					localPreparedStatement, localResultSet);
		}
	}

	public List<Plugin> getPlugins() {
		return this._$1;
	}

	public void setPlugins(List<Plugin> paramList) {
		this._$1 = paramList;
	}

	public boolean isPluginRunning(String paramString) {
		if ((AppUtils.isBlank(paramString)) || (this._$1 == null))
			return false;
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Plugin localPlugin = (Plugin) localIterator.next();
			if ((paramString
					.equals(localPlugin.getPluginConfig().getPulginId()))
					&& (localPlugin.getPluginConfig().getStatus()
							.equals(PluginStatusEnum.Y)))
				return true;
		}
		return false;
	}

	public List<PluginConfig> getPluginConfigFromDb() {
		if (!(isSystemInstalled()))
			return null;
		ArrayList localArrayList = new ArrayList();
		PreparedStatement localPreparedStatement = null;
		Connection localConnection = null;
		ResultSet localResultSet = null;
		String str = "select * from ls_plugin";
		if (this._$4.isDebugEnabled())
			this._$4.debug("Running SQL:\n" + str);
		try {
			localConnection = DBManager.getInstance().getConnection();
			localPreparedStatement = localConnection.prepareStatement(str);
			localResultSet = localPreparedStatement.executeQuery();
			while (localResultSet.next()) {
				PluginConfig localPluginConfig = _$1(localResultSet);
				localArrayList.add(localPluginConfig);
			}
		} catch (Exception localException) {
			this._$4.error(
					"SQLException in DBManager.exceuteQuery, sql is :\r\n"
							+ str, Integer.valueOf(2));
			this._$4.error("executeQuery", localException);
		} finally {
			DBManager.getInstance().cleanup(localConnection,
					localPreparedStatement, localResultSet);
		}
		return localArrayList;
	}

	public boolean isSystemInstalled() {
		return "true".equals(EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties", "INSTALLED"));
	}
}