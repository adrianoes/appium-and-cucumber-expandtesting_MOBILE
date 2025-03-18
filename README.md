# appium-and-cucumber-expandtesting_UI

UI testing in ApiClient apk using [expandtesting](https://practice.expandtesting.com/notes/app/). This project contains basic examples on how to use Appium, Cucumber, Java and Gherkin to test UI tests. Good practices such as hooks, custom commands and tags, among others, are used. All the necessary support documentation to develop this project is placed here.

# Pre-requirements:

| Requirement                              | Version        | Note                                                            |
| :--------------------------------------- |:---------------| :-------------------------------------------------------------- |
| IntelliJ IDEA Community Edition          | 2024.3.3       | -                                                               |
| JDK                                      | 11.0.20        | -                                                               |
| Maven                                    | 3.9.9          | -                                                               |
| Node.js                                  | 22.11.0        | -                                                               |
| Appium                                   | 2.16.2         | -                                                               |
| Appium Doctor                            | 1.16.2         | -                                                               |
| Appium Inspector                         | 2024.12.1      | -                                                               |
| uiautomator2 driver                      | 4.1.0          | -                                                               |
| SDK Plataform Tools                      | 35.0.2         | -                                                               |
| Android Studio                           | 2024.2.1.11    | -                                                               |
| ApiClient apk                            | 2.4.7          | -                                                               |
| Selenium Java maven dependency           | 4.18.1         | -                                                               |
| Java client for Appium maven dependency  | 9.1.0          | -                                                               |
| Cucumber JVM: Java Maven Repository      | 7.16.1         | -                                                               |
| Cucumber JVM: Core Maven Repository      | 7.16.1         | -                                                               |
| Cucumber JVM: JUnit 4 Maven Repository   | 7.16.1         | -                                                               |
| Jackson Databind Maven Repository        | 2.15.0         | -                                                               |
| Java Faker Maven Repository              | 1.0.2          | -                                                               |
| JSON In Java                             | 20210307       | -                                                               |
| JUnit Jupiter API                        | 5.11.0         | -                                                               |
| Gherkin plugin for IntelliJ              | 243.22562.13   | -                                                               |
| Cucumber for JAVA plugin for IntelliJ    | 243.22562.13   | -                                                               |
| Virtual device                           | Pixel 4        | -                                                               |
| Virtual device API                       | 29             | -                                                               |

# Installation:

- See [IntelliJ IDEA Community Edition download page](https://www.jetbrains.com/idea/download/?section=windows), download and install IntelliJ IDEA Community Edition. Keep all the prefereced options as they are until you reach Instalation Options page. Then, check the checkboxes below: 
  - :white_check_mark: **IntelliJ IDEA Community Edition** on Create Desktop Shortcut frame; 
  - :white_check_mark: **Add "Open Folder as Project"** in Update Context Menu frame; 
  - :white_check_mark: **Add "bin" Folder to the PATH** in Update PATH Variable (restart needed) frame; 
  - :white_check_mark: **.java** in Create Associations frame; 
  - :white_check_mark: **.gradle** in Create Associations frame; 
  - :white_check_mark: **.groovy** in Create Associations frame; 
  - :white_check_mark: **.kt** in Create Associations frame; 
  - :white_check_mark: **.kts** in Create Associations frame; 
  - :white_check_mark: **.pom** in Create Associations frame;
  - Hit :point_right: **Next**, :point_right: **Install**, :radio_button: **I want to manually reboot later** and :point_right: **Finish**. Save your stuff and reboot the computer.
- See [Java SE 11 Archive Downloads](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html), download the proper version for your OS and install it by keeping the preferenced options. 
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called JAVA_HOME containing the path that leads to where the JDK software is located (e.g. C:\Program Files\Java\jdk-11).
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %JAVA_HOME%\bin entry.
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path user variable with the new C:\Program Files\Java\jdk-11 entry.
- See [Maven download page](https://maven.apache.org/download.cgi), download the xxxBinary zip archive and unzip it in a place of your preference (e.g. C:\Program Files\Maven\apache-maven-3.9.9).
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called MAVEN_HOME containing the path that leads to where the JDK software is located (e.g. C:\Program Files\Maven\apache-maven-3.9.9).
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %MAVEN_HOME%\bin entry.
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path user variable with the new %MAVEN_HOME%\bin entry.
- See [Node.js page](https://nodejs.org/en) and install the aforementioned Node.js version. Keep all the preferenced options as they are.
- See [SDK Plataform Tools download page](https://developer.android.com/tools/releases/platform-tools?hl=pt-br), download the last version and unzip it in C:\Program Files\platform-tools. This step is needed so tests can be executed in real devices.
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new C:\Program Files\platform-tools entry.
- See [Android Studio download page](https://developer.android.com/), download the last version and install it by keeping the preferenced options. Open Virtual Device Manager and create an image that has Virtual device as Pixel 4 and Virtual device API as 29. 
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called ANDROID_HOME to point to where the sdk software is located (e.g. C:\Users\<user_name>\AppData\Local\Android\Sdk).
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %ANDROID_HOME%\platform-tools entry.
  - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path user variable with the new %ANDROID_HOME%, %ANDROID_HOME%\tools, %ANDROID_HOME%\platform-tools and C:\Users\<user_name>\AppData\Local\Android\Sdk entries.
- Open your terminal in your project directory and execute ```npm install -g appium``` to install Appium.
- Open your terminal in your project directory and execute ```npm i appium-doctor``` to install Appium Doctor.
- Open your terminal in your project directory and execute ```npx appium-doctor --android``` to run Appium Doctor and check Appium instalation status.
- Open your terminal in your project directory and execute ```npx appium driver install uiautomator2``` to install drivers for automationName and platformName capabilities.
- See [Appium Inspector download page](https://github.com/appium/appium-inspector/releases), download and install it. Open Virtual Device in Android Studio, drag the apiClient.apk to the screen of the virtual device and drop it. Wait for the app to be installed an open it. Whith the apiClient.apk in opened and in first plan, execute the ```adb shell dumpsys window | findstr "mCurrentFocus"``` command in the Command Prompt. It should return something like mCurrentFocus=Window{15ea642 u0 com.ab.apiclient/com.ab.apiclient.ui.MainActivity}, where com.ab.apiclient is the appium:appPackage and com.ab.apiclient.ui.MainActivity is the appium:appActivity. Also in the Command Prompt, execute the ```adb devices``` command. It should return a list of attached devices. If you have just the virtual device attached, it will return something like emulator-5554, which is exactly the appium:udid (important in case of multiple devices). In the virtual device, hit :point_right: **Settings**, :point_right: **About emulated device** and and use device name as appium:deviceName (e.g. Android SDK built for x86). In addition, "com.swaglabsmobileapp.SplashActivity is used in appium:appWaitActivity to help to prevent splashing screen errors in Appium Inspector. Your capabilities on the Appium Inspector should be, then, like:

  ```
  {
    "platformName": "Android",
    "appium:udid": "emulator-5554",
    "appium:platformVersion": "10.0",
    "appium:deviceName": "Android SDK built for x86",
    "appium:automationName": "UIAutomator2",
    "appium:app": "C:\\Users\\<user_name>\\IdeaProjects\\appium-and-cucumber-expandtesting_UI\\apks\\apiClient.apk",
    "appium:adbExecTimeout": 120000,
    "appium:autoGrantPermissions": true,
    "appium:appPackage": "com.ab.apiclient",
    "appium:appActivity": "com.ab.apiclient.ui.Splash",
    "appium:appWaitActivity": "com.ab.apiclient.ui.Splash,com.ab.apiclient.*,com.ab.apiclient.ui.MainActivity",
    "appium:appWaitDuration": 20000,
    "appium:noReset": true,
    "appium:autoDismissAlerts": true,
    "appium:uiautomator2ServerInstallTimeout": 60000
  }
  ```  
- Open IntelliJ IDEA, hit :point_right: **New Project**, hit :point_right: **Java** in New Project frame, hit :point_right: **Maven** as Build system option and check the checkboxes below: 
  - :white_check_mark: **Add sample code**, 
  - :white_check_mark: **Generate code with onboarding tips**. 
Hit :point_right: **Create**. 
- See [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.18.1), copy the maven dependency code. Open a dependencies tag in the pom.xml file right below the properties tag and paste the maven dependency copied code there. 
- See [Java client for Appium](https://mvnrepository.com/artifact/io.appium/java-client/9.1.0), copy the maven dependency code and paste it in the dependency tag. 
- See [Cucumber JVM: Java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java/7.16.1), copy the maven dependency code and paste it in the dependency tag. 
- See [Cucumber JVM: Core](https://mvnrepository.com/artifact/io.cucumber/cucumber-core/7.16.1), copy the maven dependency code and paste it in the dependency tag. 
- See [Cucumber JVM: JUnit 4](https://mvnrepository.com/artifact/io.cucumber/cucumber-junit/7.16.1), copy the maven dependency code and paste it in the dependency tag. 
- See [JUnit Jupiter API](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.11.0), copy the maven dependency code and paste it in the dependency tag. 
- See [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.0), copy the maven dependency code and paste it in the dependency tag. 
- See [Java Faker](https://mvnrepository.com/artifact/com.github.javafaker/javafaker/1.0.2), copy the maven dependency code and paste it in the dependency tag. 
- See [JSON In Java](https://mvnrepository.com/artifact/org.json/json/20210307), copy the maven dependency code and paste it in the dependency tag. Hit :point_right: **Sync maven changes**. Your dependency tag in the pom.xml file, now, should be something like:

  ```
    <dependencies>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.18.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.appium/java-client -->
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>9.1.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.16.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-core -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-core</artifactId>
            <version>7.16.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.16.1</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.json/json -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>

    </dependencies>
  ``` 
- Look for Gherkin in IntelliJ market place and install the one from JetBrains s.r.o.
- Look for Cucumber for JAVA in IntelliJ market place and install the one from JetBrains s.r.o.
- Open Command Prompt and execute ```appium``` to start appium session.
- Execute Virtual Device Manager on Android Studio.
- Open Appium Inspector and start the appium session. 

# Tests:

- Open command prompt in the pom.xml directory (e.g. C:\Users\<user_name>\IdeaProjects\appium-and-cucumber-expandtesting_UI) and Execute ```mvn clean install``` to run all to removes previous build files while compiles the source code an execute the tests.
- Hit :point_right:**Testing** button on left side bar in IntelliJ and choose the tests to execute.

# Support:

- [Capabilities](https://appium.io/docs/en/2.0/guides/caps/)
- [Altenative way to get appium:appActivity and appium:appPackage with Apk Info](https://apk-info.en.softonic.com/android)
- [Maven repositories](https://mvnrepository.com/)
- [Gherkin](https://plugins.jetbrains.com/plugin/9164-gherkin)
- [Appium Android Emulator not closing or making tests fail](https://stackoverflow.com/q/30405693)
- [[2025]: Appium Mobile App Automation Testing: Android Emulator + BDD (Cucumber) 📱](https://www.youtube.com/watch?v=4UxJXlrFEw4)
- [Part 9 | No Cloud Providers Needed 🔥 | Run Android Appium Tests inside Github Runner 🔥](https://www.youtube.com/watch?v=pAMNRcJkjro&list=PL9ok7C7Yn9A-6uidd3RXZPf5EfhxkPXa_&index=9)
- [Github Actions in Test Automation](https://www.youtube.com/playlist?list=PL9ok7C7Yn9A-6uidd3RXZPf5EfhxkPXa_)
- [GitHub Actions at a Glance](https://blog.magicpod.com/github-actions-at-a-glance)
- [Automate Mobile Gestures in Appium: A Detailed Guide for Developers](https://www.headspin.io/blog/automating-mobile-gestures-with-appium)
- [Package com.github.javafaker](https://javadoc.io/static/com.github.javafaker/javafaker/1.0.2/com/github/javafaker/package-summary.html)
- [ChatGPT](https://chatgpt.com/)

# Tips:

- When needed, open pom.xml directory and execute ```mvn clean install```. It removes previous build files to ensure a clean environment, while compiles the source code and runs tests to compile the automation again. 
- Make sure to have all same number versions for Cucumber JVM: Java, Cucumber JVM: Core and Cucumber JVM: JUnit 4 Maven repositories.     
- If an element is defined and used in some screen, the screen is changed, the screen is chaged back and the previous element is required, then it is needed to define it again with another name. Otherwise, a message telling that the element is not present in the DOM anymore will be presented.
- It was experienced in this project that Java reacts better by calling support functions from same test file than additional ones. 
- It seems that closing the app task is done by a deeper code from the one cucumber can reach, so instead of doing it direct in the feature file, an @after code block was implemented to do it.
- Error messages were filtered to look only the ones related to the apk activities. Remove the filter in the @given methods to get a complete error log.