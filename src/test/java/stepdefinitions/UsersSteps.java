package stepdefinitions;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.github.javafaker.Faker;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.nio.file.Paths;

public class UsersSteps extends SupportSteps {
    private static final Faker faker = new Faker();
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_id;

    public void user_opens_the_app() {
        super.user_opens_the_app();
    }

    public void user_configures_the_app() {
        super.user_configures_the_app();
    }

    @When("User creates a new account with name {string}, email {string} and password {string}")
    public void user_creates_a_new_account_with_name_email_and_password(String name, String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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

        // Adicionar cabeçalhos
        addAcceptHeader();
        addContentTypeHeader();

        // Selecionar formato do corpo da requisição
        WebElement formUrlEncodeButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.ab.apiclient:id/rbFormUrlEncode")));
        formUrlEncodeButton.click();

        // Inserir nome
        WebElement keyFieldName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etKey' and @text='Key']")));
        keyFieldName.sendKeys("name");
        WebElement valueFieldName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etValue' and @text='Value']")));
        valueFieldName.sendKeys(user_name);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.ab.apiclient:id/btnAdd']")));
        scrollAndClick("Add");

        // Inserir email
        WebElement keyFieldEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etKey' and @text='Key']")));
        keyFieldEmail.sendKeys("email");
        WebElement valueFieldEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etValue' and @text='Value']")));
        valueFieldEmail.sendKeys(user_email);
        scrollAndClick("Add");

        // Inserir senha
        WebElement keyFieldPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etKey' and @text='Key']")));
        keyFieldPassword.sendKeys("password");
        WebElement valueFieldPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.ab.apiclient:id/etValue' and @text='Value']")));
        valueFieldPassword.sendKeys(user_password);
        scrollAndClick("Add");

        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));

        // Enviar requisição
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.ab.apiclient:id/btnSend']")));
        sendButton.click();
        WebElement rawButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Raw']")));
        rawButton.click();
    }

    @Then("User should see a success message {string}")
    public void user_should_see_a_success_message(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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

        // Salvar dados no arquivo JSON
        saveUserData();
    }


    private void saveUserData() {
        try {
            // Geração do nome do arquivo com número aleatório de 16 dígitos
            Faker faker = new Faker();
            String randomDigits = faker.number().digits(16); // Gera um número aleatório com 16 dígitos
            String fileName = "test-" + randomDigits + ".json"; // Nome do arquivo com o padrão "test-xxxxx.json"

            JSONObject userData = new JSONObject();
            userData.put("user_name", user_name);
            userData.put("user_email", user_email);
            userData.put("user_password", user_password);
            userData.put("user_id", user_id);

            // Caminho do arquivo com o nome gerado
            String filePath = Paths.get("src", "test", "fixtures", fileName).toAbsolutePath().toString();

            // Criar arquivo e escrever os dados
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(userData.toString(4)); // Formatação bonita
            fileWriter.close();

            System.out.println("User data saved to: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para adicionar o cabeçalho "Accept"
    private void addAcceptHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement imageView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView")));
        imageView.click();
        WebElement iconDown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@resource-id='com.ab.apiclient:id/iconDown']")));
        iconDown.click();
        WebElement acceptTextView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Accept']")));
        acceptTextView.click();
        WebElement iconDownVal = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@resource-id='com.ab.apiclient:id/iconDownVal']")));
        iconDownVal.click();
        WebElement applicationXmlTextView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='application/xml']")));
        applicationXmlTextView.click();
    }

    // Função para adicionar o cabeçalho "Content-Type"
    private void addContentTypeHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement addHeaderImageView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@resource-id='com.ab.apiclient:id/llAddHeader']/android.widget.ImageView")));
        addHeaderImageView.click();
        WebElement iconDown2 = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.ImageView[@resource-id='com.ab.apiclient:id/iconDown'])[2]")));
        iconDown2.click();
        WebElement contentTypeTextView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Content-Type']")));
        contentTypeTextView.click();
        WebElement iconDownVal2 = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.ImageView[@resource-id='com.ab.apiclient:id/iconDownVal'])[2]")));
        iconDownVal2.click();
        WebElement formUrlEncodeTextView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='application/x-www-form-urlencoded']")));
        formUrlEncodeTextView.click();
    }

    public void scrollAndClick(String visibleText) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))"
        )).click();
    }





}
