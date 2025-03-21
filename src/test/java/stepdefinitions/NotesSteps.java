package stepdefinitions;

import com.github.javafaker.Faker;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import static org.junit.Assert.*;

public class NotesSteps {
    private static final Faker faker = new Faker();
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_id;
    private String user_id_1;
    private String user_id_2;
    private String user_token;
    private String note_title;
    private String note_updated_title;
    private String note_description;
    private String note_updated_description;
    private String note_category;
    private String note_updated_category;
    private String note_id;
    private String note_created_at;
    private String note_updated_at;
    private String note_title_1;
    private String note_description_1;
    private String note_category_1;
    private String note_id_1;
    private String note_created_at_1;
    private String note_updated_at_1;
    private String note_title_2;
    private String note_description_2;
    private String note_category_2;
    private String note_id_2;
    private String note_created_at_2;
    private String note_updated_at_2;

    static AppiumDriver driver;

    @Given("User opens the app before note creation")
    public void user_opens_the_app_before_note_creation() {
        // Sempre cria uma nova instância do driver
        DesiredCapabilities cap = new DesiredCapabilities();
        try {
            cap.setCapability("platformName", "Android");
            cap.setCapability("deviceName", "Pixel_4_API_29");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("automationName", "UIAutomator2");
            cap.setCapability("app", "./apks/apiClient.apk");
            cap.setCapability("appActivity", "com.ab.apiclient.ui.Splash");
            cap.setCapability("appWaitActivity", "com.ab.apiclient.ui.Splash, com.ab.apiclient.ui.MainActivity, com.ab.apiclient.*");
            cap.setCapability("autoGrantPermissions", true);
            cap.setCapability("enforceXPath1", true);
            cap.setCapability("appWaitDuration", 60000);
            cap.setCapability("newCommandTimeout", 120);
            cap.setCapability("adbExecTimeout", 120000);

//            cap.setCapability("autoDismissAlerts", true);
//            cap.setCapability("noReset", true);
//            cap.setCapability("adbExecTimeout", 120000);

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

//    @And("User configures the app before note creation")
//    public void user_configures_the_app_before_note_creation() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//
//        // Abrir o menu lateral
//        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.ImageButton")));
//        menuButton.click();
//
//        // Ir para as configurações
//        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='com.ab.apiclient:id/design_menu_item_text' and @text='Settings']")));
//        settingsButton.click();
//
//        // Definir timeout da conexão
//        WebElement timeoutField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etTimeoutConnection")));
//        timeoutField.clear();
//        timeoutField.sendKeys("120");
//
//        // Definir timeout da leitura
//        WebElement timeoutReadField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etTimeoutREAD")));
//        timeoutReadField.clear();
//        timeoutReadField.sendKeys("120");
//
//        // Definir timeout da escrita
//        WebElement timeoutWriteField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etTimeoutWRITE")));
//        timeoutWriteField.clear();
//        timeoutWriteField.sendKeys("120");
//
//        // Fechar configurações
//        menuButton.click();
//
//        // Criar nova requisição
//        WebElement newRequestButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='com.ab.apiclient:id/design_menu_item_text' and @text='New Request']")));
//        newRequestButton.click();
//    }

    @When("User sends request to create user before note creation")
    public void user_sends_request_to_create_user_before_note_creation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Gerar valores aleatórios e renomear variáveis
        user_name = faker.name().fullName();
        user_email = faker.internet().emailAddress().toLowerCase().replace("-", "");
        user_password = faker.regexify("[A-Za-z0-9]{12,20}");

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
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
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see created user message before note creation")
    public void user_should_see_created_user_message_before_note_creation() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
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

    @When("User sends request to log in user before note creation")
    public void user_sends_request_to_log_in_user_before_note_creation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
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
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see logged in user message before note creation")
    public void user_should_see_logged_in_user_message_before_note_creation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
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

    @When("User sends request to delete user after note creation")
    public void user_sends_request_to_delete_user_after_note_creation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar DELETE
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown.click();
        WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='DELETE']")));
        deleteOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/users/delete-account");

        addTokenHeader();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see deleted user message after note creation")
    public void user_should_see_deleted_user_message_after_note_creation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
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

//    @And("App is closed after note creation")
//    public void app_is_closed_after_note_creation() {
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

    @When("User sends request to create a note")
    public void user_sends_request_to_create_a_note() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Gerar valores aleatórios e renomear variáveis
        note_title = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_description = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_category = faker.options().option("Home", "Personal", "Work");

        newRequest();

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown.click();
        WebElement postOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='POST']")));
        postOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes");

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "title=" + note_title + "&description=" + note_description + "&category=" + note_category;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see created note message")
    public void user_should_see_created_note_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        JSONObject data = responseJson.getJSONObject("data");
        note_id = data.getString("id"); // Capturando o ID da nota
        String returnedTitle = data.getString("title");
        String returnedDescription = data.getString("description");
        String returnedCategory = data.getString("category");
        boolean returnedCompleted = data.getBoolean("completed");
        note_created_at = data.getString("created_at"); // Capturando o timestamp de criação
        note_updated_at = data.getString("updated_at"); // Capturando o timestamp de atualização
        user_id = data.getString("user_id"); // Capturando o ID do usuário

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Note successfully created'", "Note successfully created", message);
        assertEquals("Title should be correct", note_title, returnedTitle);
        assertEquals("Description should be correct", note_description, returnedDescription);
        assertEquals("Category should be correct", note_category, returnedCategory);
        assertFalse("Completed status should be false for a newly created note", returnedCompleted);
    }

    @When("User sends request to create notes")
    public void user_sends_request_to_create_notes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Gerar valores aleatórios e renomear variáveis
        note_title_1 = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_description_1 = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_category_1 = faker.options().option("Home", "Personal", "Work");

        note_title_2 = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_description_2 = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_category_2 = faker.options().option("Home", "Personal", "Work");

        newRequest();

        // Selecionar POST
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown.click();
        WebElement postOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='POST']")));
        postOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes");

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "title=" + note_title_1 + "&description=" + note_description_1 + "&category=" + note_category_1;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);
        JSONObject data = responseJson.getJSONObject("data");
        note_id_1 = data.getString("id"); // Capturando o ID da nota
        note_created_at_1 = data.getString("created_at"); // Capturando o timestamp de criação
        note_updated_at_1 = data.getString("updated_at"); // Capturando o timestamp de atualização
        user_id_1 = data.getString("user_id"); // Capturando o ID do usuário

        newRequest();

        // Selecionar POST
        WebElement httpMethodDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown2.click();
        WebElement postOption2 = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='POST']")));
        postOption2.click();

        // Inserir URL do endpoint
        WebElement urlField2 = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField2.sendKeys("https://practice.expandtesting.com/notes/api/notes");

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        //fill form in raw code
        WebElement jsonDataField2 = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData2 = "title=" + note_title_2 + "&description=" + note_description_2 + "&category=" + note_category_2;
        jsonDataField2.sendKeys(formData2);

        // Enviar requisição
        WebElement sendButton2 = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton2.click();
        WebElement rawButton2 = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton2.click();

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText2 = resultTextElement2.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson2 = new JSONObject(responseText2);
        JSONObject data2 = responseJson2.getJSONObject("data");
        note_id_2 = data2.getString("id"); // Capturando o ID da nota
        note_created_at_2 = data2.getString("created_at"); // Capturando o timestamp de criação
        note_updated_at_2 = data2.getString("updated_at"); // Capturando o timestamp de atualização
        user_id_2 = data2.getString("user_id"); // Capturando o ID do usuário
    }

    @And("User sends request to get notes info")
    public void user_sends_request_to_get_notes_info() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes");

        // Adicionar cabeçalho
        addTokenHeader();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see notes info message")
    public void user_should_see_notes_info_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Extraindo o array de notas
        JSONArray dataArray = responseJson.getJSONArray("data");

        // Pegando a nota mais antiga (índice 1)
        JSONObject note1 = dataArray.getJSONObject(1);
        String returnedId1 = note1.getString("id");
        String returnedTitle1 = note1.getString("title");
        String returnedDescription1 = note1.getString("description");
        String returnedCategory1 = note1.getString("category");
        boolean returnedCompleted1 = note1.getBoolean("completed");
        String returnedCreatedAt1 = note1.getString("created_at");
        String returnedUpdatedAt1 = note1.getString("updated_at");
        String returnedUserId1 = note1.getString("user_id");

        // Pegando a nota mais recente (índice 0)
        JSONObject note2 = dataArray.getJSONObject(0);
        String returnedId2 = note2.getString("id");
        String returnedTitle2 = note2.getString("title");
        String returnedDescription2 = note2.getString("description");
        String returnedCategory2 = note2.getString("category");
        boolean returnedCompleted2 = note2.getBoolean("completed");
        String returnedCreatedAt2 = note2.getString("created_at");
        String returnedUpdatedAt2 = note2.getString("updated_at");
        String returnedUserId2 = note2.getString("user_id");

        // Validando os valores gerais
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Notes successfully retrieved'", "Notes successfully retrieved", message);

        // Validando a nota mais antiga
        assertEquals("First note ID should be correct", note_id_1, returnedId1);
        assertEquals("First note title should be correct", note_title_1, returnedTitle1);
        assertEquals("First note description should be correct", note_description_1, returnedDescription1);
        assertEquals("First note category should be correct", note_category_1, returnedCategory1);
        assertEquals("First note created_at should be correct", note_created_at_1, returnedCreatedAt1);
        assertEquals("First note updated_at should be correct", note_updated_at_1, returnedUpdatedAt1);
        assertEquals("First note user_id should be correct", user_id_1, returnedUserId1);
        assertFalse("First note completed status should be false", returnedCompleted1);

        // Validando a nota mais recente
        assertEquals("Second note ID should be correct", note_id_2, returnedId2);
        assertEquals("Second note title should be correct", note_title_2, returnedTitle2);
        assertEquals("Second note description should be correct", note_description_2, returnedDescription2);
        assertEquals("Second note category should be correct", note_category_2, returnedCategory2);
        assertEquals("Second note created_at should be correct", note_created_at_2, returnedCreatedAt2);
        assertEquals("Second note updated_at should be correct", note_updated_at_2, returnedUpdatedAt2);
        assertEquals("Second note user_id should be correct", user_id_2, returnedUserId2);
        assertFalse("Second note completed status should be false", returnedCompleted2);
    }

    @When("User sends request to get note info")
    public void user_sends_request_to_get_note_info() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes/" + note_id );

        // Adicionar cabeçalho
        addTokenHeaderAs2nd();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see note info message")
    public void user_should_see_note_info_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Extraindo os valores do JSON
        JSONObject data = responseJson.getJSONObject("data");
        String returnedNoteId = data.getString("id"); // Capturando o ID da nota
        String returnedTitle = data.getString("title");
        String returnedDescription = data.getString("description");
        String returnedCategory = data.getString("category");
        boolean returnedCompleted = data.getBoolean("completed");
        String returnedCreatedAt = data.getString("created_at"); // Capturando o timestamp de criação
        String returnedUpdatedAt = data.getString("updated_at"); // Capturando o timestamp de atualização
        String returnedUserId = data.getString("user_id"); // Capturando o ID do usuário

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Note successfully retrieved'", "Note successfully retrieved", message);
        assertEquals("Note ID should be correct", note_id, returnedNoteId);
        assertEquals("Title should be correct", note_title, returnedTitle);
        assertEquals("Description should be correct", note_description, returnedDescription);
        assertEquals("Category should be correct", note_category, returnedCategory);
        assertEquals("Created at timestamp should be correct", note_created_at, returnedCreatedAt);
        assertEquals("Updated at timestamp should be correct", note_updated_at, returnedUpdatedAt);
        assertEquals("User ID should be correct", user_id, returnedUserId);
        assertFalse("Completed status should be false for a newly created note", returnedCompleted);
    }

    @When("User sends request to update note")
    public void user_sends_request_to_update_note() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Gerar valores aleatórios e renomear variáveis
        note_updated_title = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_updated_description = "1234" + faker.rockBand().name().replaceAll("[^a-zA-Z0-9 ]", "");
        note_updated_category = faker.options().option("Home", "Personal", "Work");

        newRequest();

        // Selecionar PUT
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown.click();
        WebElement putOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='PUT']")));
        putOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes/" + note_id);

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "title=" + note_updated_title + "&description=" + note_updated_description + "&completed=true" + "&category=" + note_updated_category;
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see note updated message")
    public void user_should_see_note_updated_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Extraindo os valores do JSON
        JSONObject data = responseJson.getJSONObject("data");
        String returnedNoteId = data.getString("id"); // Capturando o ID da nota
        String returnedTitle = data.getString("title");
        String returnedDescription = data.getString("description");
        String returnedCategory = data.getString("category");
        boolean returnedCompleted = data.getBoolean("completed");
        String returnedCreatedAt = data.getString("created_at"); // Capturando o timestamp de criação
        String returnedUserId = data.getString("user_id"); // Capturando o ID do usuário

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Note successfully Updated'", "Note successfully Updated", message);
        assertEquals("Note ID should be correct", note_id, returnedNoteId);
        assertEquals("Title should be correct", note_updated_title, returnedTitle);
        assertEquals("Description should be correct", note_updated_description, returnedDescription);
        assertEquals("Category should be correct", note_updated_category, returnedCategory);
        assertEquals("Created at timestamp should be correct", note_created_at, returnedCreatedAt);
        assertEquals("User ID should be correct", user_id, returnedUserId);
        assertTrue("Completed status should be true for a updated note", returnedCompleted);
    }

    @When("User sends request to update note status")
    public void user_sends_request_to_update_note_status() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar PATCH
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown.click();
        WebElement patchOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='PATCH']")));
        patchOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes/" + note_id);

        // Adicionar cabeçalho
        addContentTypeHeader();
        addTokenHeaderAs2nd();

        //fill form in raw code
        WebElement jsonDataField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etJSONData")));
        String formData = "completed=true";
        jsonDataField.sendKeys(formData);

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see note status updated message")
    public void user_should_see_note_status_updated_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
        String responseText = resultTextElement.getText();

        // Criando o objeto JSONObject para parsear o JSON da resposta
        JSONObject responseJson = new JSONObject(responseText);

        // Extraindo os valores do JSON
        boolean success = responseJson.getBoolean("success");
        int status = responseJson.getInt("status");
        String message = responseJson.getString("message");

        // Extraindo os valores do JSON
        JSONObject data = responseJson.getJSONObject("data");
        String returnedNoteId = data.getString("id"); // Capturando o ID da nota
        String returnedTitle = data.getString("title");
        String returnedDescription = data.getString("description");
        String returnedCategory = data.getString("category");
        boolean returnedCompleted = data.getBoolean("completed");
        String returnedCreatedAt = data.getString("created_at"); // Capturando o timestamp de criação
        String returnedUserId = data.getString("user_id"); // Capturando o ID do usuário

        // Validando os valores
        assertTrue("Success should be true", success);
        assertEquals("Status should be 200", 200, status);
        assertEquals("Message should be 'Note successfully Updated'", "Note successfully Updated", message);
        assertEquals("Note ID should be correct", note_id, returnedNoteId);
        assertEquals("Title should be correct", note_title, returnedTitle);
        assertEquals("Description should be correct", note_description, returnedDescription);
        assertEquals("Category should be correct", note_category, returnedCategory);
        assertEquals("Created at timestamp should be correct", note_created_at, returnedCreatedAt);
        assertEquals("User ID should be correct", user_id, returnedUserId);
        assertTrue("Completed status should be true for a updated note", returnedCompleted);
    }

    @When("User sends request to delete note")
    public void user_sends_request_to_delete_note() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        newRequest();

        // Selecionar DELETE
        WebElement httpMethodDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Spinner")));
        httpMethodDropdown.click();
        WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='DELETE']")));
        deleteOption.click();

        // Inserir URL do endpoint
        WebElement urlField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.ab.apiclient:id/etUrl")));
        urlField.sendKeys("https://practice.expandtesting.com/notes/api/notes/" + note_id );

        // Adicionar cabeçalho
        addTokenHeaderAs2nd();

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/btnSend")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Raw\")")));
        rawButton.click();
    }

    @Then("User should see note deleted message")
    public void user_should_see_note_deleted_message() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Aguardando e pegando o texto da resposta da API
        WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.ab.apiclient:id/tvResult\")")));
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
        assertEquals("Message should be 'Note successfully deleted'", "Note successfully deleted", message);
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
        WebElement newRequestButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/drawer_menu_new_request")));
        newRequestButton.click();
    }

}



