package stepdefinitions;




import java.util.List;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class Mobilesteps {
	
	WebDriver driver;
	String outsideprice;
	String insideprice;
	
	@Before
	public void launchbrowser() {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
	driver = new ChromeDriver(options);
	}
	
	@Given("user is on Home Page")
	public void user_is_on_home_page() {
		
		driver.navigate().to("https://live.guru99.com");
	}

	@When("user clicks on {string}")
	public void user_clicks_on(String expectedtitle) {
		WebElement mobilelink = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
		mobilelink.click();
		String actualtitle = driver.getTitle();
		Assert.assertEquals(expectedtitle, actualtitle);
	}

	@When("user selects SortBy dropdown as {string}")
	public void user_selects_sort_by_dropdown_as(String string) {
		WebElement sortby = driver.findElement(By.xpath("//select[@title='Sort By']"));
		sortby.click();
		Select select = new Select(sortby);	
		select.selectByVisibleText("Name");
	}

	@Then("validate if products are sorted by Name")
	public void validate_if_products_are_sorted_by_name() {
		String xpath = "//body/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']/div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select[1]/option[contains(text(), 'Name')]";
	    WebElement nameoption = driver.findElement(By.xpath(xpath));
	    String actualselected = nameoption.getAttribute("selected");
	    String expectedselected = "true";
	    Assert.assertEquals(expectedselected, actualselected);
	    
	}

	@When("user notes the value of sony xperia mobile")
	public void user_notes_the_value_of_sony_xperia_mobile() {
	    WebElement xperiaprice = driver.findElement(By.xpath("//span[contains(text(),'$100.00')]"));
	    outsideprice = xperiaprice.getText();
	}

	@When("user clicks on Xperia mobile")
	public void user_clicks_on_xperia_mobile() {
	    WebElement xperia = driver.findElement(By.xpath("//img[@id='product-collection-image-1']"));
	    xperia.click();
	}

	@When("user note the value of mobile on details page")
	public void user_note_the_value_of_mobile_on_details_page() {
	    WebElement price = driver.findElement(By.xpath("(//span[@class='price'])[1]"));
	    insideprice = price.getText();
	}

	@Then("validate if both prices are same")
	public void validate_if_both_prices_are_same() {
	    Assert.assertEquals(outsideprice, insideprice);
	}

	@When("user adds sony xperia mobile to cart")
	public void user_adds_sony_xperia_mobile_to_cart() {
	    WebElement addtocartbtn = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[2]/div[1]/div[3]/button[1]"));
	    addtocartbtn.click();
	}

	@When("user chnages the qty to {string}")
	public void user_chnages_the_qty_to(String qty) {
	    WebElement qtyfield = driver.findElement(By.xpath("//input[@title='Qty']"));
	    qtyfield.clear();
	    qtyfield.sendKeys(qty);
	    WebElement updatebtn = driver.findElement(By.xpath("//button[@title='Update']"));
	    updatebtn.click();
	}
	
	@Then("validate the error message as error msg click Empty cart")
	public void validate_the_error_message_as_error_msg_click_empty_cart(DataTable dataTable) { 
		List<List<String>> data = dataTable.asLists(String.class);
		String expectederrormsg = data.get(0).get(0);
		WebElement errormsg = driver.findElement(By.xpath("//p[@class='item-msg error']"));
		String actualerrormsg = errormsg.getText();
		Assert.assertEquals(expectederrormsg, actualerrormsg);
		WebElement emptycartbtn = driver.findElement(By.xpath("//button[@id='empty_cart_button']"));
		emptycartbtn.click();
	    
	}

	@Then("^Validate \"([^\"]*)\" msg$")
    public void validate_something_msg(String expectedinfomsg){
	    WebElement infomsg = driver.findElement(By.xpath("//h1[normalize-space()='Shopping Cart is Empty']"));
	    String actualinfomsg = infomsg.getText();
	    Assert.assertEquals(expectedinfomsg, actualinfomsg);
	    
	    
	}

	@When("user clicks on add to compare option for Sony xperia and IPhone")
	public void user_clicks_on_add_to_compare_option_for_sony_xperia_and_i_phone() throws InterruptedException {
		WebElement iphoneaddtocart = driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]"));
		iphoneaddtocart.click();
		WebElement xperiaaddtocart = driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]"));
		xperiaaddtocart.click();
	}

	@When("user clicks on compare button")
	public void user_clicks_on_compare_button() {
	    WebElement comparebtn = driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]"));
	    comparebtn.click(); 
	}

	@Then("Validate products are reflects in pop-up close the pop-up")
	public void validate_products_are_reflects_in_pop_up_close_the_pop_up() {
	    WebElement xperia = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']"));
	    String xperiatext = xperia.getText();
	    WebElement iphone = driver.findElement(By.xpath("//a[normalize-space()='IPhone']"));
	    String iphonetext = iphone.getText();
	    Assert.assertEquals("SONY XPERIA", xperiatext);
	    Assert.assertEquals("IPHONE", iphonetext);
	    driver.quit();
	}
	
	@After
	public void closebrowser() {
	driver.quit();
	}

}
