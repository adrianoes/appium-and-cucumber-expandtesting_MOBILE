package stepdefinitions;

import com.github.javafaker.Faker;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersSteps {
    private static final Faker faker = new Faker();
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_new_password;
    private String user_id;
    private String user_token;
    private String user_phone;
    private String user_company;

    static AppiumDriver driver;

    @Given("User opens the app")
    public void user_opens_the_app() {
        // Sempre cria uma nova instância do driver
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
            cap.setCapability("adbExecTimeout", 120000);

            URL url = URI.create("http://127.0.0.1:4723").toURL();
            driver = new AppiumDriver(url, cap);

        } catch (Exception e) {
            // Capture the exception message and stack trace
            String exceptionMessage = e.getClass().getName() + ": " + (e.getMessage() != null ? e.getMessage().split("\n")[0] : "Unknown error");

            // Filter the stack trace to display only relevant lines
            StringBuilder filteredStackTrace = new StringBuilder();
            for (StackTraceElement element : e.getStackTrace()) {
                if (element.getClassName().contains("stepdefinitions")) {
                    filteredStackTrace.append("\n\tat ").append(element);
                }
            }

            // Throw a new exception with the formatted message and filtered stack trace
            throw new RuntimeException(exceptionMessage + filteredStackTrace);
        }
    }

    @And("User configures the app")
    public void user_configures_the_app() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

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

    @When("User sends request to create user")
    public void user_sends_request_to_create_user() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Gerar valores aleatórios e renomear variáveis
        user_name = faker.name().fullName();
        user_email = faker.internet().emailAddress().toLowerCase().replace("-", "");
        user_password = faker.regexify("[A-Za-z0-9]{12,20}");

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/spHttpMethod")));
        httpMethodDropdown.click();
        WebElement postOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='POST']")));
        postOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/register");

        // Adicionar cabeçalho
        addContentTypeHeader();

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "name=" + user_name + "&email=" + user_email + "&password=" + user_password;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see created user message")
    public void user_should_see_created_user_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        JSONObject data = responseJson.getJSONObject("data");
        user_id = data.getString("id"); // Capturando o user_id da resposta
        String returnedName = data.getString("name");
        String returnedEmail = data.getString("email");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 201", 201, status);
        assertEquals("Message should be 'User account created successfully'", "User account created successfully", message);
        assertEquals("User name should be correct", user_name, returnedName);
        assertEquals("User email should be correct", user_email, returnedEmail);
    }

    @When("User sends request to log in user")
    public void user_sends_request_to_log_in_user() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/spHttpMethod")));
        httpMethodDropdown.click();
        WebElement postOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='POST']")));
        postOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/login");

        // Adicionar cabeçalho
        addContentTypeHeader();

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "email=" + user_email + "&password=" + user_password;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see logged in user message")
    public void user_should_see_logged_in_user_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        JSONObject data = responseJson.getJSONObject("data");
        user_token = data.getString("token"); // Capturando o token da resposta
        String returnedId = data.getString("id");
        String returnedName = data.getString("name");
        String returnedEmail = data.getString("email");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Login successful'", "Login successful", message);
        assertEquals("User id should be correct", user_id, returnedId);
        assertEquals("User name should be correct", user_name, returnedName);
        assertEquals("User email should be correct", user_email, returnedEmail);
    }

    @When("User sends request to delete user")
    public void user_sends_request_to_delete_user() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar DELETE
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/spHttpMethod")));
        httpMethodDropdown.click();
        WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='DELETE']")));
        deleteOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/delete-account");

        addTokenHeader();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see deleted user message")
    public void user_should_see_deleted_user_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Account successfully deleted'", "Account successfully deleted", message);
    }

//    @And("App is closed")
//    public void app_is_closed() {
//        if (driver != null) {
//            try {
//                String packageName = "com.ab.apiclient";
//                String adbCommand = "adb -s emulator-5554 shell am force-stop " + packageName;
//                Process process = Runtime.getRuntime().exec(adbCommand);
//                process.waitFor();
//                Thread.sleep(1000);
//                driver.quit();
//                driver = null; // Garante que um novo driver seja criado no próximo cenário
//            } catch (IOException | InterruptedException e) {
//                System.out.println("Error during closing the app via ADB: " + e.getMessage());
//            }
//        }
//    }

    @After
    public void quitDriver() {
        if (driver != null) {
            try {
                String packageName = "com.ab.apiclient";
                String adbCommand = "adb -s emulator-5554 shell am force-stop " + packageName;
                Process process = Runtime.getRuntime().exec(adbCommand);
                process.waitFor();
                Thread.sleep(1000);
                driver.quit();
            } catch (IOException | InterruptedException e) {
                System.out.println("Error during closing the app via ADB: " + e.getMessage());
            }
        }
    }

    @When("User sends request to get user info")
    public void user_sends_request_to_get_user_info() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/profile");

        // Adicionar cabeçalho
        addTokenHeader();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see user info")
    public void user_should_see_user_info() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        JSONObject data = responseJson.getJSONObject("data");
        String returnedId = data.getString("id");
        String returnedName = data.getString("name");
        String returnedEmail = data.getString("email");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Profile successful'", "Profile successful", message);
        assertEquals("User id should be correct", user_id, returnedId);
        assertEquals("User name should be correct", user_name, returnedName);
        assertEquals("User email should be correct", user_email, returnedEmail);
    }

    @When("User sends request to updates user info")
    public void user_sends_request_to_updates_user_info() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar PATCH
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/spHttpMethod")));
        httpMethodDropdown.click();
        WebElement patchOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='PATCH']")));
        patchOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/profile");

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        user_phone = faker.regexify("[0-9]{10,12}");
        user_company = faker.regexify("[A-Za-z0-9]{5,15}");

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "name=" + user_name + "&phone=" + user_phone + "&company=" + user_company;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see updated user info")
    public void user_should_see_updated_user_info() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        JSONObject data = responseJson.getJSONObject("data");
        String returnedId = data.getString("id");
        String returnedName = data.getString("name");
        String returnedEmail = data.getString("email");
        String returnedPhone = data.getString("phone");
        String returnedCompany = data.getString("company");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Profile updated successful'", "Profile updated successful", message);
        assertEquals("User id should be correct", user_id, returnedId);
        assertEquals("User name should be correct", user_name, returnedName);
        assertEquals("User email should be correct", user_email, returnedEmail);
        assertEquals("User phone should be correct", user_phone, returnedPhone);
        assertEquals("User company should be correct", user_company, returnedCompany);
    }

    @When("User sends request to updates password")
    public void user_sends_request_to_updates_password() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/spHttpMethod")));
        httpMethodDropdown.click();
        WebElement postOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='POST']")));
        postOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/change-password");

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        user_new_password = faker.regexify("[A-Za-z0-9]{12,20}");

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "currentPassword=" + user_password + "&newPassword=" + user_new_password;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User password should be updated")
    public void user_password_should_be_updated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'The password was successfully updated'", "The password was successfully updated", message);
    }

    @When("User sends request to log out user")
    public void user_sends_request_to_log_out_user() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar DELETE
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/spHttpMethod")));
        httpMethodDropdown.click();
        WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='DELETE']")));
        deleteOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/logout");

        addTokenHeader();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see logged out user message")
    public void user_should_see_logged_out_user_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/tvResult']")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'User has been successfully logged out'", "User has been successfully logged out", message);
    }

    private void addContentTypeHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement imageView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView")));
        imageView.click();
        WebElement iconDown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@resource-id='com.ab.apiclient:id/iconDown']")));
        iconDown.click();
        WebElement acceptTextView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Content-Type']")));
        acceptTextView.click();
        WebElement iconDownVal = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@resource-id='com.ab.apiclient:id/iconDownVal']")));
        iconDownVal.click();
        WebElement applicationXmlTextView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='application/x-www-form-urlencoded']")));
        applicationXmlTextView.click();
    }

    private void addTokenHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement imageView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView")));
        imageView.click();
        WebElement keyField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ab.apiclient:id/etKey\"]")));
        keyField.sendKeys("x-auth-token");
        WebElement valueField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ab.apiclient:id/etValue\"]")));
        valueField.sendKeys(user_token);
    }

    private void addTokenHeaderAs2nd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement imageView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"com.ab.apiclient:id/llAddHeader\"]/android.widget.ImageView")));
        imageView.click();
        WebElement keyField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ab.apiclient:id/etKey\" and @text=\"Key\"]")));
        keyField.sendKeys("x-auth-token");
        WebElement valueField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ab.apiclient:id/etValue\" and @text=\"Value\"]")));
        valueField.sendKeys(user_token);
    }

//    public void scrollDown(WebDriver driver) {
//        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
//                ImmutableMap.of(
//                        "left", 100,
//                        "top", 100,
//                        "width", 200,
//                        "height", 200,
//                        "direction", "down",
//                        "percent", 50.0
//                ));
//    }

    public void newRequest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.ImageButton")));
        menuButton.click();
        WebElement newRequestButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='com.ab.apiclient:id/design_menu_item_text' and @text='New Request']")));
        newRequestButton.click();
    }

}



