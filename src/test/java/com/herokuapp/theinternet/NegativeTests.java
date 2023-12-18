package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
    @Test
    public void incorrectUsernameTest() {
        System.out.println("incorrectUsernameTest started");
        //Create driver
        WebDriver driver = new ChromeDriver();  //create chrome driver instance.
        System.out.println("Browser started");

        //open test page
        String url = "https://the-internet.herokuapp.com/login"; //create new variable to store our url.
        driver.get(url); //we use "driver" to ope our url.

        driver.manage().window().maximize(); //open driver in full screen.
        System.out.println("page is opened");

        //enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("incorrectUsername"); //enter incorrect username (suppose to be "tomsmith").

        //enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(("SuperSecretPassword!"));
        System.out.println("password is: SuperSecretPassword");

        //click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();
        System.out.println("clicked login button");

        //VERIFICATIONS:
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();

        /*to make sure our error message is correct, we will compare our expected to the actual error msg.*/
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contain expected. \nActual: "
                + actualErrorMessage + "\nExpected: " + expectedErrorMessage);

        //close browser
        driver.close();
    }
}
