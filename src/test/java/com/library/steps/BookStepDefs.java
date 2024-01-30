package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class BookStepDefs {
    BookPage bookPage=new BookPage();

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(2);
    }

    List<String> actualCategoryList;
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        actualCategoryList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualCategoryList = " + actualCategoryList);

        // Remove ALL Option from the List
        actualCategoryList.remove(0);
        System.out.println("----- AFTER REMOVE ALL -------");
        System.out.println("actualCategoryList = " + actualCategoryList);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        String query="select name from book_categories";
        DB_Util.runQuery(query);

        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedCategoryList = " + expectedCategoryList);

        Assert.assertEquals(expectedCategoryList,actualCategoryList);


    }


    // US-04
    String globalBookName;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        globalBookName = bookName;
        bookPage.search.sendKeys(bookName);
        BrowserUtil.waitFor(1);

    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(globalBookName).click();
        BrowserUtil.waitFor(2);

    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        System.out.println("--------- DATA FROM UI -----------");

        // GET DATA FROM UI
        String actualBookName = bookPage.bookName.getAttribute("value");
        System.out.println(actualBookName);
        String actualAuthor = bookPage.author.getAttribute("value");
        System.out.println(actualAuthor);
        String actualISBN = bookPage.isbn.getAttribute("value");
        System.out.println(actualISBN);
        String actualDesc = bookPage.description.getAttribute("value");
        System.out.println(actualDesc);
        String actualYear = bookPage.year.getAttribute("value");
        System.out.println(actualYear);

        Select select=new Select(bookPage.categoryDropdown);
        String actualCategory = select.getFirstSelectedOption().getText();
        System.out.println("actualCategory = " + actualCategory);


        System.out.println("--------- DATA FROM DATABASE -----------");

        // GET DATA FROM DB
        String query="select b.name as bookName,isbn,author,year,b.description as des,bc.name as bookCategory\n" +
                "    from books inner join book_categories bc on b.book_category_id=bc.id " +
                "where b.name='"+globalBookName+"'";

        DB_Util.runQuery(query);

        Map<String, String> rowMap = DB_Util.getRowMap(1);
        String expectedBookName = rowMap.get("bookName");
        System.out.println(expectedBookName);

        String expectedISBN = rowMap.get("isbn");
        System.out.println(expectedISBN);

        String expectedYear = rowMap.get("year");
        System.out.println(expectedYear);

        String expectedDesc = rowMap.get("des");
        System.out.println(expectedDesc);

        String expectedAuthor = rowMap.get("author");
        System.out.println(expectedAuthor);

        String expectedCategory = rowMap.get("bookCategory");
        System.out.println(expectedCategory);


        System.out.println("NOT IMPL YET ");

        // COMPARE




    }

}
