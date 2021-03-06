package jhd.bluebox.sensu.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "user_email")
	private WebElement emailInput;

	private final static String BoxPanelURL = "https://support.cn01.bluebox.net/tickets?sort=-created";
	
	private final static String SensuURL = "https://control.cn01.bluebox.net/pek01/sensu/#/events";

	@FindBy(id = "user_password")
	private WebElement passwordInput;

	@FindBy(xpath = "//*[@id=\"new_user\"]/input[4]")
	private WebElement login;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {

		//driver.get(BoxPanelURL);
		driver.get(SensuURL);//直接不需要boxpanel页面，直接监控sensu就足够了
		emailInput.sendKeys(username);
		passwordInput.sendKeys(password);
		login.submit();
	}

}
