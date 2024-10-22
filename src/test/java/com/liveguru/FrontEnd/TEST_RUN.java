package com.liveguru.FrontEnd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TEST_RUN {

    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");

        driver.quit();
    }
}
