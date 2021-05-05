package com.gfg.product.bdd.steps;

import com.gfg.product.ProductServiceApplicationTests;
import com.gfg.product.entity.Product;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

public class ProductStep extends ProductServiceApplicationTests {

    private static final Logger LOGGER = getLogger(ProductStep.class);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Product> productRS = null;
    ResponseEntity<List> productLS = null;
    ResponseEntity re;

    @Given("Rest endpoint is up")
    public void restEndpointIsUp() {
        LOGGER.info("Rest Endpoint is Up");
    }

    @When("Get one product by id {int}")
    public void getOneProductById(int id) {
        LOGGER.info("Find Product By " + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/vnd.gfg.v1+json")));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        productRS = restTemplate.exchange("http://localhost:8082/products/findById/" + id, HttpMethod.GET, null, Product.class);
    }

    @Then("Returned product is equal product in DB")
    public void returnedProductIsEqualProductInDB() {
        Product product = new Product();
        product.setId(Long.valueOf(1));
        product.setProductId("GAS1234567");
        product.setTitle("Jeans");
        product.setDescription("Slim fit jeans");
        product.setBrand("GAS");
        product.setPrice(BigDecimal.valueOf(100.00));
        product.setColor("Blue");
        assertEquals(product.getProductId(), productRS.getBody().getProductId());
    }

    @When("Get all products")
    public void getAllProducts() {
        LOGGER.info("Get All Products");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/vnd.gfg.v1+json")));
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        productLS = restTemplate.exchange("http://localhost:8082/products/findAll", HttpMethod.GET, request, List.class);
    }

    @Then("Check product number return by five")
    public void checkProductNumberReturnByFive() {
//        assertNotNull(productLS.getBody());
        assertEquals(productLS.getBody().size(), 5);
    }

    ResponseEntity<String> responseEntityStr;

    @When("Create new product")
    public void createNewProduct() throws JSONException {

        JSONObject productDto = new JSONObject();
        productDto.put("productId", "NEWONE");
        productDto.put("title", "Jeans");
        productDto.put("description", "Slim fit jean");
        productDto.put("brand", "Gas");
        productDto.put("price", 100);
        productDto.put("color", "Blue");

        HttpHeaders headers = new HttpHeaders();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/vnd.gfg.v1+json")));

        HttpEntity<String> request = new HttpEntity<String>(productDto.toString(), headers);

        responseEntityStr = restTemplate.
                postForEntity("http://localhost:8082/products/create", request, String.class);
    }

    @Then("Return status ok")
    public void returnStatusOk() {
        assertEquals(responseEntityStr.getStatusCode().value(), 200);
    }

    @When("Update product by id {int}")
    public void updateProductById(Integer id) throws JSONException {
        JSONObject productDto = new JSONObject();
        productDto.put("productId", "GAS1234567");
        productDto.put("title", "Jeans");
        productDto.put("description", "Update Slim fit jean");
        productDto.put("brand", "Update Brand");
        productDto.put("price", 100);
        productDto.put("color", "Blue");

        HttpHeaders headers = new HttpHeaders();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/vnd.gfg.v1+json")));

        HttpEntity<String> request = new HttpEntity<String>(productDto.toString(), headers);

        restTemplate.put("http://localhost:8082/products/updateById/" + id, request);
    }

    @Then("Check product return")
    public void checkProductReturn() {
        getOneProductById(1);
        assertEquals("Update Slim fit jean", productRS.getBody().getDescription());
        assertEquals("Update Brand", productRS.getBody().getBrand());
    }


    @When("Delete product by id {int}")
    public void deleteProductById(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/vnd.gfg.v1+json")));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        re = restTemplate.exchange("http://localhost:8082/products/deleteById/" + id, HttpMethod.DELETE, request, Object.class);
    }

    @Then("Returned product by id {int} is not found")
    public void returnedProductByIdIsNotFound(int id) {
        assertEquals(200, re.getStatusCode().value());
    }

}
