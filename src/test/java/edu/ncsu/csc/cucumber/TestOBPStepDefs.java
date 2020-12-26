package edu.ncsu.csc.cucumber;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Tests edit recipe bbt created by us.
 *
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class TestOBPStepDefs extends CucumberTest {

    /**
     * Navigates to Coffeemaker.
     */
    @When ( "I navigate to the CoffeeMaker site" )
    public void navigateToCoffeeMaker () {
        driver.get( "http://localhost:8080/" );
    }

    /**
     * Checks if you are on Coffeemaker.
     */
    @Then ( "I should be on the CoffeeMaker site" )
    public void onCoffeeMaker () {
        assertTrue( driver.getCurrentUrl().contains( "localhost:8080" ) );
    }

    /**
     * Clicks on link on website.
     * 
     * @param name
     *            Name of link
     */
    @When ( "I click on (.+)" )
    public void clickOn ( final String name ) {
        final WebElement link = driver.findElement( By.xpath( "//a[.='" + name + "']" ) );

        link.click();
    }

    /**
     * Adds a recipe.
     */
    @When ( "Click Add a Recipe" )
    public void clickOnAdd () {
        final WebElement link = driver.findElement( By.xpath( "//a[.='Add a Recipe']" ) );

        link.click();
    }

    /**
     * Edits recipe.
     */
    @When ( "Click Edit Recipe" )
    public void clickOnEdit () {
        final WebElement link = driver.findElement( By.xpath( "//a[.='Edit Recipe']" ) );

        link.click();
    }

    /**
     * Clicks home button.
     */
    @When ( "Click Home" )
    public void clickOnHome () {
        final WebElement link = driver.findElement( By.xpath( "//a[.='Home']" ) );

        link.click();
    }

    /**
     * Clicks Coffee.
     */
    @When ( "Click Coffee" )
    public void clickCoffeeRecipe () {
        final WebElement link = driver.findElement( By.xpath( "//input[@type='radio']" ) );

        link.click();
    }

    /**
     * Checks if you're on page.
     * 
     * @param title
     *            Title
     */
    @Then ( "I should be on the page for (.+)" )
    public void onPage ( final String title ) {
        assertTrue( driver.getCurrentUrl().equals( "http://localhost:8080/" + title ) );
    }

    /**
     * Adds recipe.
     * 
     * @param name
     *            Name
     * @param cost
     *            Cost of coffee
     * @param coffeeAmt
     *            amount of coffee
     * @param milkAmt
     *            amount of milk
     * @param sugarAmt
     *            amount of sugar
     * @param chocolateAmt
     *            amount of chocolate
     */
    @When ( "^I submit valid values for name: (.+); cost: (\\\\d+); and ingredients: (\\\\d+) coffee, (\\\\d+) milk, (\\\\d+) sugar, (\\\\d+) chocolate$" )
    public void addRecipe ( final String name, final Integer cost, final Integer coffeeAmt, final Integer milkAmt,
            final Integer sugarAmt, final Integer chocolateAmt ) {
        final WebElement nameInput = driver.findElement( By.xpath( "//input[name='name']" ) );
        final WebElement costInput = driver.findElement( By.xpath( "//input[name='cost']" ) );
        final WebElement coffeeInput = driver.findElement( By.xpath( "//input[name='coffee']" ) );
        final WebElement milkInput = driver.findElement( By.xpath( "//input[name='milk']" ) );
        final WebElement sugarInput = driver.findElement( By.xpath( "//input[name='sugar']" ) );
        final WebElement chocolateInput = driver.findElement( By.xpath( "//input[name='chocolate']" ) );

        nameInput.sendKeys( name );
        costInput.sendKeys( cost.toString() );
        coffeeInput.sendKeys( coffeeAmt.toString() );
        milkInput.sendKeys( milkAmt.toString() );
        sugarInput.sendKeys( sugarAmt.toString() );
        chocolateInput.sendKeys( chocolateAmt.toString() );

        final WebElement button = driver.findElement( By.xpath( "//input[@value='Submit']" ) );
        button.click();

    }

    /**
     * Edits recipe.
     * 
     * @param cost
     *            Cost of coffee
     * @param coffeeAmt
     *            amount of coffee
     * @param milkAmt
     *            amount of milk
     * @param sugarAmt
     *            amount of sugar
     * @param chocolateAmt
     *            amount of chocolate
     */
    @When ( "^I edit valid values for cost: (\\\\d+); and ingredients: (\\\\d+) coffee, (\\\\d+) milk, (\\\\d+) sugar, (\\\\d+) chocolate$" )
    public void editRecipe ( final Integer cost, final Integer coffeeAmt, final Integer milkAmt, final Integer sugarAmt,
            final Integer chocolateAmt ) {
        final WebElement costInput = driver.findElement( By.xpath( "//input[name='cost']" ) );
        final WebElement coffeeInput = driver.findElement( By.xpath( "//input[name='coffee']" ) );
        final WebElement milkInput = driver.findElement( By.xpath( "//input[name='milk']" ) );
        final WebElement sugarInput = driver.findElement( By.xpath( "//input[name='sugar']" ) );
        final WebElement chocolateInput = driver.findElement( By.xpath( "//input[name='chocolate']" ) );

        costInput.sendKeys( cost.toString() );
        coffeeInput.sendKeys( coffeeAmt.toString() );
        milkInput.sendKeys( milkAmt.toString() );
        sugarInput.sendKeys( sugarAmt.toString() );
        chocolateInput.sendKeys( chocolateAmt.toString() );

        final WebElement button = driver.findElement( By.xpath( "//input[@value='Submit']" ) );
        button.click();

    }

    /**
     * Creates a recipe with the name provided
     *
     * @param name
     *            Name of the new Recipe to create.
     */
    @Given ( "^the Coffeemaker has a recipe named MyCoffee" )
    public void addCoffeeRecipe () {
        addRecipe( "MyCoffee", 1, 1, 1, 1, 1 );
    }
}
