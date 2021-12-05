package com.telran.demoqa.tests.bookstoreTests;

import com.telran.demoqa.data.BookData;
import com.telran.demoqa.data.UserData;
import com.telran.demoqa.pages.*;
import com.telran.demoqa.pages.bookstorePages.BookStorePage;
import com.telran.demoqa.pages.bookstorePages.LoginPage;
import com.telran.demoqa.pages.bookstorePages.ProfilePage;
import com.telran.demoqa.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddBookToCollectionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        new HomePage(driver).getBookStore();
        new BookStorePage(driver).getLoginPage().closeBanner();
        new LoginPage(driver).login(UserData.USER_NAME, UserData.USER_PASSWORD);
    }

    @Test
    public void addBookToCollectionPositiveTest() {
        new BookStorePage(driver).typeInSearchFieldInput(BookData.BOOK_NAME)
                .clickByFirstBook().addToYourCollection();
        new SidePanelPage(driver).selectProfile();

        Assert.assertTrue(new BookStorePage(driver).takeNameOfBook()
               .contains(BookData.BOOK_NAME));
    }

    @AfterMethod
    public void postConditions() {
        new ProfilePage(driver).clickOnTrashToDeleteBook();
    }
}
