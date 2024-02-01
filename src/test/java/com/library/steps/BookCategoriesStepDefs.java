package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BookCategoriesStepDefs {

    // US 05
    String actualCategory;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {

        String query="select bc.name,count(*)\n" +
                "    from book_borrow bb\n" +
                "        inner join books b on bb.book_id = b.id\n" +
                "        inner join book_categories bc on b.book_category_id = bc.id\n" +
                "    group by bc.name\n" +
                "    order by count(*) desc\n" +
                "    limit 1";

        DB_Util.runQuery(query);

        actualCategory = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualCategory = " + actualCategory);

        String borrowedBookCount = DB_Util.getCellValue(1, 2);
        System.out.println("borrowedBookCount = " + borrowedBookCount);


    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedCategory) {
        Assert.assertEquals(expectedCategory,actualCategory);
    }

}
