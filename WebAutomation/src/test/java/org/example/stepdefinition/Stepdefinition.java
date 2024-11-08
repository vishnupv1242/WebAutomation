package org.example.stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.example.utilities.configReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.response.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Stepdefinition {
    WebDriver driver;
    configReader configReader = new configReader();

    @Given("I open the browser")
    public void i_open_the_browser() {
        driver = new ChromeDriver();
        driver.get(configReader.getBaseURL());
    }

    @Then("I verify the menu Section")
    public void iVerifyTheMenuSection(DataTable expectedMenuItems) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<String> expectedItems = expectedMenuItems.asList(String.class);
        List<WebElement> menuElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='nav navbar-nav']//a"))
        );
        List<String> actualItems = new ArrayList<>();
        for (WebElement menuElement : menuElements) {
            actualItems.add(menuElement.getText().trim());
        }
        for (String expectedItem : expectedItems) {
            boolean isPresent = actualItems.stream().anyMatch(actualItem -> actualItem.contains(expectedItem));
            assertTrue("Expected menu item not found: " + expectedItem, isPresent);
        }
    }

    @Then("I verify there are exactly 3 slides in the carousel")
    public void iVerifyThereAreExactlySlidesInTheCarousel() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> slides = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@data-target='#slider-carousel']"))
        );
        assertEquals("Expected exactly 3 slides in the carousel, but found " + slides.size(), 3, slides.size());
    }

    @Then("I verify there are exactly 3 category sections displayed")
    public void iVerifyThereAreExactlyCategorySectionsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> categorySections = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Category']//..//div[@class='panel-group category-products']//div[@class='panel panel-default']"))
        );
        assertEquals("Expected exactly 3 category sections, but found " + categorySections.size(), 3, categorySections.size());
    }

    @Then("I verify there are exactly 8 brand logos displayed in the Brands section")
    public void iVerifyThereAreExactlyBrandLogosDisplayedInTheBrandsSection() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> brandLogos = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Brands']//..//div[@class='brands-name']//li"))
        );
        assertEquals("Expected exactly 9 brand logos, but found " + brandLogos.size(), 8, brandLogos.size());
    }

    @When("I scroll down to the footer")
    public void iScrollDownToTheFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Then("I verify the footer contains a subscription email textbox")
    public void iVerifyTheFooterContainsASubscriptionEmailTextbox() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement emailTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));
        assertTrue("Subscription email textbox is not displayed", emailTextbox.isDisplayed());
    }

    @And("I verify the footer contains a submit button for subscribing")
    public void iVerifyTheFooterContainsASubmitButtonForSubscribing() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='subscribe']")));
        assertTrue("Submit button for subscribing is not displayed", submitButton.isDisplayed());
    }
}
