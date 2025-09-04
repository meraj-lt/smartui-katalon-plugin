package com.katalon.plugin.keyword.smartui

import com.kms.katalon.core.setting.BundleSettingStore
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import org.apache.commons.lang3.StringUtils
import io.github.lambdatest.SmartUI
import io.github.lambdatest.SmartUIConfig
import org.openqa.selenium.WebDriver


class Utils {
	static BundleSettingStore bundleSetting
	static String PROJECT_TOKEN
	static String APP_NAME

	static {
		try {
			bundleSetting = new BundleSettingStore(RunConfiguration.getProjectDir(), 'com.kms.katalon.keyword.SmartUI-Keywords', true)

			PROJECT_TOKEN = bundleSetting.getString('Project Token', '')
			if (StringUtils.isBlank(PROJECT_TOKEN)) {
				throw new IllegalStateException("SmartUI's Project Token is missing.")
			}
			APP_NAME = bundleSetting.getString('Application Name', '')
		} catch (Exception e) {
			e.printStackTrace()
			throw e
		}
	}

	static WebDriver getDriver() {
		WebDriver katalonWebDriver = DriverFactory.getWebDriver()
		return katalonWebDriver
	}

	static SmartUI smartUIInit(String buildName, String configFile, Integer port) {
		SmartUIConfig config = new SmartUIConfig()
				.withProjectToken(Utils.PROJECT_TOKEN);
		if (port != null) {
			config = config.withPort(port);
		}
		if (buildName != null && !buildName.isEmpty()) {
			config = config.withBuildName(buildName);
		}
		if (configFile != null && !configFile.isEmpty()) {
			config = config.withConfigFile(configFile);
		}
		return new SmartUI(config);
	}
}