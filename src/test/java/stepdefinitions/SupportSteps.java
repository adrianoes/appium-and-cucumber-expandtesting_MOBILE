package stepdefinitions;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class SupportSteps {
    protected static AppiumDriver driver;

    @Given("User opens the app")
    public void user_opens_the_app() {

        if (driver == null) {  // Evita reabrir o app se já estiver aberto
            DesiredCapabilities cap = new DesiredCapabilities();
            try {
                cap.setCapability("platformName", "Android");
                cap.setCapability("deviceName", "Pixel_4_API_29");
                cap.setCapability("platformVersion", "10.0");
                cap.setCapability("automationName", "UIAutomator2");
                cap.setCapability("app", "./apks/apiClient.apk");
                cap.setCapability("appActivity", "com.ab.apiclient.ui.Splash");
                cap.setCapability("appWaitActivity", "com.ab.apiclient.ui.Splash,com.ab.apiclient.ui.MainActivity");
                cap.setCapability("autoGrantPermissions", true);

                URL url = URI.create("http://127.0.0.1:4723").toURL();
                driver = new AppiumDriver(url, cap);
            } catch (Exception e) {
                throw new RuntimeException("Failed to open the app", e);
            }
        }
    }

    @And("User configures the app")
    public void user_configures_the_app() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Abrir o menu lateral
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.ImageButton")));
        menuButton.click();

        // Ir para as configurações
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='com.ab.apiclient:id/design_menu_item_text' and @text='Settings']")));
        settingsButton.click();

        // Definir timeout da conexão
        WebElement timeoutField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etTimeoutConnection")));
        timeoutField.clear();
        timeoutField.sendKeys("120");

        // Definir timeout da leitura
        WebElement timeoutReadField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etTimeoutREAD")));
        timeoutReadField.clear();
        timeoutReadField.sendKeys("120");

        // Definir timeout da escrita
        WebElement timeoutWriteField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etTimeoutWRITE")));
        timeoutWriteField.clear();
        timeoutWriteField.sendKeys("120");

        // Fechar configurações
        menuButton.click();

        // Criar nova requisição
        WebElement newRequestButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='com.ab.apiclient:id/design_menu_item_text' and @text='New Request']")));
        newRequestButton.click();

    }

}
