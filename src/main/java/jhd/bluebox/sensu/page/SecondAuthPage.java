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
					System.out.println("令牌：" + code);
					//TODO 请不要直接在英文输入法下按yubi key，英文状态下yubi key会补充一个回车键去提交，这样下一步的提交就会找不到元素
					if (code.length() == 44) {// yubi key 会生成一个44位长的code
						System.out.println("令牌符合要求，进行下一步");
						authButton.submit();
						authListener.onAuthSuc();
						System.out.println(code.length() + "<->" + code);
						return;
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
