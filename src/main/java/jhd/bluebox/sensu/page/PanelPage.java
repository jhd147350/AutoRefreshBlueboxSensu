package jhd.bluebox.sensu.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PanelPage extends BasePage {

	@FindBy(xpath = "//*[@id=\"status-header\"]/div/ul/li[1]/a")
	private WebElement supportA;

	public PanelPage(WebDriver driver) {
		super(driver);
	}

	public void clickSupport() {
		supportA.click();
	}

}
