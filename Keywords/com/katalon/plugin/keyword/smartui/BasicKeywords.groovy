package com.katalon.plugin.keyword.smartui

import io.github.lambdatest.SmartUI
import com.kms.katalon.core.annotation.Keyword
import org.openqa.selenium.WebDriver

class BasicKeywords {
	@Keyword
	static void takeSnapshot(String snapshotName) throws IOException {
		SmartUI smartUI = SmartKeywords.getSMARTUI_CLIENT()
		if (smartUI == null) {
			throw new IllegalStateException("SmartUI server is not started")
		}
		WebDriver driver = Utils.getDriver()
		smartUI.takeSnapshot(driver, snapshotName)
	}
}