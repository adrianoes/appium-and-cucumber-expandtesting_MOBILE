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

public class HealthSteps extends SupportSteps {

    public void user_opens_the_app() {
        super.user_opens_the_app();
    }

    public void user_configures_the_app() {
        super.user_configures_the_app();
    }

    @When("User sends request to check API health")
    public void uUser_sends_request_to_check_API_health() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Disable WiFi
        try {
            Runtime.getRuntime().exec("adb shell svc wifi disable");
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to disable WiFi", e);
        }

        // Input base URL and endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etUrl']")));
        urlField.click();
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/health-check");

        // Click Send Request button
        WebElement sendButton = driver.findElement(AppiumBy.id("com.ab.apiclient:id/btnSend"));
        sendButton.click();
    }

    @Then("User should see API health message")
    public void user_should_see_API_health_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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