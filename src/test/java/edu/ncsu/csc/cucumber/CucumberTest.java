package edu.ncsu.csc.cucumber;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * CucumberTest (Cucumber) test class for interacting with the Inventory model.
 * This performs a number of tests to ensure that the inventory is changed in
 * the expected manner
 *
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public abstract class CucumberTest {

    static {
        ChromeDriverManager.chromedriver().setup();
    }

    /**
     * String for system.
     */
    final static private String   OS = System.getProperty( "os.name" );

    /**
     * Driver
     */
    static protected ChromeDriver driver;

    /**
     * setup chrome
     */
    static public void setup () {

        final ChromeOptions options = new ChromeOptions();
        // options.addArguments( "headless" );
        options.addArguments( "window-size=1200x600" );
        options.addArguments( "blink-settings=imagesEnabled=false" );

        if ( Linux() ) {
            options.setBinary( "/usr/bin/google-chrome" );
        }
        else if ( Windows() ) {
            options.setBinary( "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe" );
        }
        else if ( Mac() ) {
            options.setBinary( "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" );
        }

        driver = new ChromeDriver( options );

    }

    /**
     * Mac
     *
     * @return Mac
     */
    static private boolean Mac () {
        return OS.contains( "Mac OS X" );
    }

    /**
     * Linux
     *
     * @return Linux
     */
    static private boolean Linux () {
        return OS.contains( "Linux" );
    }

    /**
     * Windows
     *
     * @return Windows
     */
    static private boolean Windows () {
        return OS.contains( "Windows" );
    }

    /**
     * Shuts down driver.
     */
    static public void tearDown () {
        driver.close();
        driver.quit();

        if ( Windows() ) {
            windowsKill();
        }
        else if ( Linux() || Mac() ) {
            unixKill();
        }

    }

    /**
     * Waits for angular.
     */
    protected void waitForAngular () {
        new NgWebDriver( driver ).waitForAngularRequestsToFinish();
    }

    /**
     * Kills windows
     */
    static private void windowsKill () {
        try {
            Runtime.getRuntime().exec( "taskkill /f /im chrome.exe" );
            Runtime.getRuntime().exec( "taskkill /f /im chromedriver.exe" );
        }
        catch ( final Exception e ) {
        }
    }

    /**
     * Kills UNIX
     */
    static private void unixKill () {
        try {
            Runtime.getRuntime().exec( "pkill -f chromium-browser" );
            Runtime.getRuntime().exec( "pkill -f chrome" );
            Runtime.getRuntime().exec( "pkill -f chromedriver" );
        }
        catch ( final Exception e ) {
        }

    }
}
