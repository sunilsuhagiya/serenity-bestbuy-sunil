package com.bestbuy.bestinfo;

import com.bestbuy.model.ProductsPojo;
import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTestWithSteps extends TestBase {

    static String name = "Prime8" + TestUtils.getRandomValue();
    static String type = "Testing";
    static int price = 10;
    static int shipping = 0;
    static String upc = "041333825014";
    static String description = "Compatible with select electronic devices; AA size; DURALOCK Power Preserve technology; 8-pack";
    static String manufacturer = "Duracell";
    static String model = "MN1500B8Z";
    static String url = "http://www.bestbuy.com/site/duracell-aa-batteries-8-pack/127687.p?id=1051384045676&skuId=127687&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/1276/127687_sa.jpg";
    static int ProductId;
    @Steps
    ProductsSteps productsSteps;

    @Title("This will create new product")
    @Test
    public void test001() {

        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);

    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
        ProductId = (int) productMap.get("id");
    }

    @Title("Update the product information and verify the udated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updateProduct(ProductId, name, type, price, shipping, upc, description, manufacturer, model, url, image)
                .log().all().statusCode(200);
        // verify update
        HashMap<String, Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        productsSteps.deleteProduct(ProductId).statusCode(200);
        productsSteps.getProductById(ProductId).statusCode(404);

    }
}
