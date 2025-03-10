package stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HealthSteps {
    static AppiumDriver driver;

    @Given("User opens the app")
    public void user_opens_the_app() {
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

    @When("User fills the API health check parameters")
    public void user_fills_api_health_check_parameters() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Disable WiFi
        try {
            Runtime.getRuntime().exec("adb shell svc wifi disable");
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to disable WiFi", e);
        }

        // Input base URL and endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etUrl']")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/health-check");

        // Click Send Request button
        WebElement sendButton = driver.findElement(AppiumBy.id("com.ab.apiclient:id/btnSend"));
        sendButton.click();
    }

    @Then("App shows API good health message")
    public void app_shows_api_good_health_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement rawButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();

        WebElement responseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/tvResult")));
        String responseText = responseElement.getText();

        // Use Jackson to parse the JSON response
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = objectMapper.readTree(responseText);

            // Extract values from the JSON response
            boolean success = responseJson.path("success").asBoolean();
            int status = responseJson.path("status").asInt();
            String message = responseJson.path("message").asText();

            // Validate the extracted values
            assertTrue("Response should indicate success", success);
            assertEquals("Response should have status 200", 200, status);
            assertEquals("Response should confirm API is running", "Notes API is Running", message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}
