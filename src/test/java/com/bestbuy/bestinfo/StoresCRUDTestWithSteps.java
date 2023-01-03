package com.bestbuy.bestinfo;


import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBaseStores;
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
public class StoresCRUDTestWithSteps extends TestBaseStores {

    static String name = "Rosevilleee";
    static String type = "BigBox";
    static String address = "1643 County Road B2";
    static String address2 = "";
    static String city = "Roseville";
    static String state = "MN";
    static String zip = "55113";
    static double lat = 45.01651;
    static double lng = -93.168518;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;
    @Steps
    StoresSteps storesSteps;

    @Title("This will create new store")
    @Test
    public void test001(){
        ValidatableResponse response = storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> storeMap = storesSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");
    }
    @Title("Update the store information and verify the udated information")
    @Test
    public void test003() {
        name = name + "_updated";
        storesSteps.updateStore(storeId, name, type, address, address2, city, state, zip, lat, lng, hours)
                .log().all().statusCode(200);
        // verify update
        HashMap<String, Object> storeMap = storesSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));
    }
    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(204);
        storesSteps.getStudentById(storeId).statusCode(404);

    }

}
