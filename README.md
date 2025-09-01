# SmartUI Katalon Studio Plugin

A custom Katalon Studio plugin that integrates LambdaTest SmartUI for visual testing and automated screenshot capture during test execution.

## 🚀 Features

- **SmartUI Integration**: Seamless integration with LambdaTest SmartUI for visual testing
- **Automated Screenshots**: Capture screenshots during test execution with custom naming
- **Server Management**: Start and stop SmartUI server programmatically
- **Custom Keywords**: Easy-to-use keywords for SmartUI operations
- **Configuration Management**: Centralized configuration through Katalon Studio settings

## 📋 Prerequisites

- Katalon Studio (latest version)
- LambdaTest account with SmartUI access
- Java 8 or higher
- Gradle (for building the plugin)

## 🛠️ Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd SmartUI
```

### 2. Build the Plugin
```bash
./gradlew clean build
```

### 3. Import into Katalon Studio
1. Open Katalon Studio
2. Go to **File** → **Open Project**
3. Select the SmartUI project folder
4. The plugin will be automatically loaded

## ⚙️ Configuration

### 1. Plugin Settings
1. Go to **Project** → **Settings** → **Plugins**
2. Find "SmartUI Integration" in the plugins list
3. Configure the following settings:
   - **Project Token**: Your LambdaTest SmartUI project token
   - **Application Name**: Name of your application (default: "Katalon Studio")

### 2. Environment Variables (Optional)
You can also set environment variables:
- `SMARTUI_PROJECT_TOKEN`: Your SmartUI project token
- `SMARTUI_APP_NAME`: Your application name

## 📖 Usage

### Available Keywords

#### 1. SmartUIKeywords.startServer()
Starts the SmartUI server with optional configuration.

```groovy
// Basic usage
CustomKeywords.'com.katalon.plugin.keyword.smartui.SmartUIKeywords.startServer'()

// With custom build name
CustomKeywords.'com.katalon.plugin.keyword.smartui.SmartUIKeywords.startServer'('my-build-name')

// With custom build name and timeout
CustomKeywords.'com.katalon.plugin.keyword.smartui.SmartUIKeywords.startServer'('my-build-name', 60000)
```

#### 2. SmartUIKeywords.stopServer()
Stops the SmartUI server.

```groovy
CustomKeywords.'com.katalon.plugin.keyword.smartui.SmartUIKeywords.stopServer'()
```

#### 3. BasicKeywords.takeSnapshot()
Captures a screenshot with SmartUI.

```groovy
CustomKeywords.'com.katalon.plugin.keyword.smartui.BasicKeywords.takeSnapshot'('login-page')
```

### Example Test Case

```groovy
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class SmartUIExampleTest extends TestCase {
    
    @BeforeTestSuite
    def beforeTestSuite() {
        // Start SmartUI server
        CustomKeywords.'com.katalon.plugin.keyword.smartui.SmartUIKeywords.startServer'('example-test-suite')
    }
    
    @AfterTestSuite
    def afterTestSuite() {
        // Stop SmartUI server
        CustomKeywords.'com.katalon.plugin.keyword.smartui.SmartUIKeywords.stopServer'()
    }
    
    def testLoginFlow() {
        // Navigate to login page
        WebUI.navigateToUrl('https://example.com/login')
        
        // Take screenshot of login page
        CustomKeywords.'com.katalon.plugin.keyword.smartui.BasicKeywords.takeSnapshot'('login-page')
        
        // Fill login form
        WebUI.setText(findTestObject('Object Repository/Login/input_username'), 'testuser')
        WebUI.setText(findTestObject('Object Repository/Login/input_password'), 'password')
        
        // Take screenshot before login
        CustomKeywords.'com.katalon.plugin.keyword.smartui.BasicKeywords.takeSnapshot'('before-login')
        
        // Submit form
        WebUI.click(findTestObject('Object Repository/Login/button_login'))
        
        // Take screenshot after login
        CustomKeywords.'com.katalon.plugin.keyword.smartui.BasicKeywords.takeSnapshot'('after-login')
    }
}
```

## 📁 Project Structure

```
SmartUI/
├── Keywords/
│   ├── katalon-plugin.json          # Plugin configuration
│   └── com/katalon/plugin/keyword/smartui/
│       ├── BasicKeywords.groovy     # Screenshot capture keywords
│       ├── SmartUIKeywords.groovy   # Server management keywords
│       └── Utils.groovy             # Utility functions
├── Test Cases/                      # Test cases directory
├── Test Suites/                     # Test suites directory
├── Object Repository/               # Page objects
├── Data Files/                      # Test data files
├── build.gradle                     # Gradle build configuration
├── .gitignore                       # Git ignore rules
└── README.md                        # This file
```

## 🔧 Development

### Building from Source

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd SmartUI
   ```

2. **Build the project**
   ```bash
   ./gradlew clean build
   ```

3. **Run tests**
   ```bash
   ./gradlew test
   ```

### Adding New Keywords

1. Create a new Groovy file in `Keywords/com/katalon/plugin/keyword/smartui/`
2. Add the `@Keyword` annotation to your methods
3. Update `Keywords/katalon-plugin.json` to include your new class
4. Rebuild the project

Example:
```groovy
package com.katalon.plugin.keyword.smartui

import com.kms.katalon.core.annotation.Keyword

class MyCustomKeywords {
    @Keyword
    static void myCustomMethod() {
        // Your custom logic here
    }
}
```

## 🐛 Troubleshooting

### Common Issues

1. **Plugin not visible in Katalon Studio**
   - Restart Katalon Studio
   - Refresh the project (Project → Refresh)
   - Check that the plugin is listed in Project Settings → Plugins

2. **SmartUI server not starting**
   - Verify your Project Token is correct
   - Check network connectivity
   - Ensure LambdaTest account has SmartUI access

3. **Screenshots not being captured**
   - Ensure SmartUI server is running
   - Check that WebDriver is properly initialized
   - Verify snapshot names are unique

### Error Messages

- **"SmartUI's Project Token is missing"**: Configure the Project Token in plugin settings
- **"SmartUI server is not healthy"**: Check network connectivity and LambdaTest service status
- **"SmartUI server is not started"**: Call `startServer()` before taking screenshots

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

For support and questions:
- Create an issue in this repository
- Contact the development team
- Check LambdaTest SmartUI documentation

## 🔗 Related Links

- [Katalon Studio Documentation](https://docs.katalon.com/)
- [LambdaTest SmartUI Documentation](https://www.lambdatest.com/smart-ui)
- [Gradle Documentation](https://gradle.org/docs/)

---

**Note**: This plugin requires a valid LambdaTest SmartUI subscription to function properly.
