package com.library.steps;

import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.remote.Browser;

import java.util.List;

public class UserStepDefs {
    String actualUserCount;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Create conn for Database
        // DB_Util.createConnection();

        System.out.println("--------------------------------------------");
        System.out.println("-------DATABASE CONN IS DONE BY HOOKS-------");
        System.out.println("--------------------------------------------");


    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount"+actualUserCount);


    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query="select count(distinct id) from users";
        DB_Util.runQuery(query);


        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserCount"+expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);

        // Close conn for Database
        // DB_Util.destroy();
        System.out.println("--------------------------------------------");
        System.out.println("-------DATABASE CONN IS CLOSED BY HOOKS-----");
        System.out.println("--------------------------------------------");
    }

    List<String> actualColumnNames;
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        String query="select * from users";
        DB_Util.runQuery(query);

       actualColumnNames = DB_Util.getAllColumnNamesAsList();
        System.out.println("actualColumnNames = " + actualColumnNames);

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnNames) {

        System.out.println("expectedColumnNames = " + expectedColumnNames);

        Assert.assertEquals(expectedColumnNames,actualColumnNames);
    }
}
