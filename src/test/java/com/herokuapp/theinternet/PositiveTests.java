package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
    @Test
    public void loginTest() throws InterruptedException {
        System.out.println("Test started");
        //Create driver
        WebDriver driver = new ChromeDriver();  //create chrome driver instance.
        System.out.println("Browser started");

        //open test page
        String url = "https://the-internet.herokuapp.com/login"; //create new variable to store our url.
        driver.get(url); //we use "driver" to ope our url.

        driver.manage().window().maximize();
        //Thread.sleep(1000); //slow the execution - just to lear the process.
        System.out.println("page is opened");

        //enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        System.out.println("username is: tomsmith");

        //enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(("SuperSecretPassword!"));
        System.out.println("password is: SuperSecretPassword");

        //click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();
        System.out.println("clicked login button");


        //VERIFICATIONS:
        //new url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "actual page URL is not the same as expected.");

        //logout button is visible
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class= 'button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Log out button is not visible.");

        //successful login message
        WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message doesnt contain expected message. \nActualMessage:" + actualMessage
                + "\nExpectedMessage: " + expectedMessage);

        //close browser
        driver.close();
        System.out.println("Test finished");

    }

}
