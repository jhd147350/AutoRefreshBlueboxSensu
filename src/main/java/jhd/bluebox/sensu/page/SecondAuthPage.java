package jhd.bluebox.sensu.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecondAuthPage extends BasePage {

	@FindBy(id = "token")
	private WebElement token;

	@FindBy(xpath = "//*[@id=\"wrapper\"]/div[2]/div/section/form/button")
	private WebElement authButton;

	public SecondAuthPage(WebDriver driver) {
		super(driver);
	}

	private AuthListener authListener;

	public void auth() {
		Thread onAuthCodeFinish = new Thread() {
			@Override
			public void run() {
				super.run();
				while (true) {
					System.out.println("please press the key!");
					String code = token.getAttribute("value");
					if (code.length() >= 44) {// yubi key 会生成一个44位长的code
						authButton.submit();
						authListener.onAuthSuc();
						System.out.println(code.length() + "<->" + code);
						break;
					} else {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		onAuthCodeFinish.start();
	}

	public AuthListener getAuthListener() {
		return authListener;
	}

	public void setAuthListener(AuthListener authListener) {
		this.authListener = authListener;
	}

	public interface AuthListener {
		void onAuthSuc();
	}

}
