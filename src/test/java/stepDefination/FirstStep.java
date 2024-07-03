package stepDefination;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class FirstStep{

    public String baseUrl;
    public final Map<String, String> parameters = new HashMap<>();
    public Response response;
    public JsonNode jsonData;
    public String name;
    public String job;

    @Given("I hit the url of get users of endpoints")
    public void iHitTheUrlOfGetUsersOfEndpoints() {
        baseUrl = "https://reqres.in/api/users";
    }


    @Given("I set {string} as the name in parameter")
    public void iSetAsTheNameInParameter(String name) {
        parameters.put("name", name);
    }

    @Given("I set {string} as the job in the parameter")
    public void iSetAsTheJobInParameter(String job) {
        parameters.put("job", job);
    }

    @When("I send a Post request to {string}")
    public void iSendAPostRequestTo(String url) {
        response = given().contentType("application/json")
                .body(parameters)
                .when()
                .post(url);
    }

    @Then("I should get {int} as the expectedStatusCode")
    public void iShouldGetAsTheExpectedStatusCode(int statusCode) {
        response.then().statusCode(equalTo(statusCode));
    }

    @Given("the endpoint {string}")
    public void the_endpoint(String url) {
       RestAssured.baseURI=url;
    }
    @When("a GET request is made")
    public void a_get_request_is_made() {
        response=given().when().get();
    }
    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);

    }
    @Then("the response content type should be {string}")
    public void the_response_content_type_should_be(String contentType) {
        response.then().contentType(contentType);

    }

    @Then("I should get {string} as the first name")
    public void i_should_get_as_the_first_name(String firstName) {
        response.then().body("data.first_name",equalTo(firstName));
    }

    @Given("I hit the url of get users of endpoints through CSV file")
    public void i_hit_the_url_of_get_users_of_endpoints_through_csv_file() {
        baseUrl = "https://reqres.in/api/users";

    }
    @Given("I set {string} as the name in parameter through CSV file")
    public void i_set_as_the_name_in_parameter_through_csv_file(String name) {
        parameters.put("name", name);

    }
    @Given("I set {string} as the job in the parameter through CSV file")
    public void i_set_as_the_job_in_the_parameter_through_csv_file(String job) {
        parameters.put("job", job);

    }
    @When("I send a Post request to {string} through CSV file")
    public void i_send_a_post_request_to_through_csv_file(String url) throws IOException {
        String excelFilePath = "C:\\Users\\ANMPATEL\\OneDrive - Deloitte (O365D)\\postCSVFile.xlsx";
        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        for (Row currentRow : datatypeSheet) {
            if (currentRow.getRowNum() == 0) {
                continue;
            }
            String name = currentRow.getCell(0).getStringCellValue();
            String job = currentRow.getCell(1).getStringCellValue();
            parameters.put("name", name);
            parameters.put("job", job);
        }
        workbook.close();
        excelFile.close();
        response = given()
                .contentType("application/json")
                .body(parameters)
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();

    }
    @Given("I have a JSON file {string}")
    public void i_have_a_json_file(String filePath) throws IOException {
        System.out.println("Path to JSON file: " + new File(filePath).getAbsolutePath());
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = objectMapper.readTree(new File(filePath));
    }

    @Given("I read data from the JSON file")
    public void i_read_data_from_the_json_file() {
        name = jsonData.get("name").asText();
        job = jsonData.get("job").asText();


    }
    @Given("I set {string} as the name parameter from the JSON data")
    public void i_set_as_the_name_parameter_from_the_json_data(String key) {
        name = jsonData.get(key).asText();


    }
    @Given("I set {string} as the job parameter from the JSON data")
    public void i_set_as_the_job_parameter_from_the_json_data(String key) {
        job = jsonData.get(key).asText();


    }
    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String url) {
        response = RestAssured.given()
                .contentType("application/json")
                .body("{\"name\":\"" + name + "\", \"job\":\"" + job + "\"}")
                .post(url)
                .then()
                .log().all()
                .extract().response();

    }
    @Then("I should get {int} as the expected status code")
    public void i_should_get_as_the_expected_status_code(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Given("I have a XML file {string}")
    public void i_have_a_xml_file(String filePath) throws IOException {
        File file = new File(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        Map<String, String> data = xmlMapper.readValue(file, Map.class);
        this.name = data.get("name");
        this.job = data.get("job");

    }

    @When("I send a POST request to XML {string}")
    public void i_send_a_post_request_to_xml(String string) {

    }


}