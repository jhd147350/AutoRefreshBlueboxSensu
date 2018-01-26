package jhd.bluebox.sensu;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import jhd.bluebox.sensu.page.LoginPage;
import jhd.bluebox.sensu.page.SecondAuthPage;
import jhd.bluebox.sensu.page.SecondAuthPage.AuthListener;
import jhd.bluebox.sensu.page.SensuPage;

/**
 * Hello world!
 *
 */
public class App {
	private static WebDriver driver;

	public static void main(String[] args) throws AWTException {
		driver = new ChromeDriver();
		System.out.println("源码地址：https://github.com/jhd147350/AutoRefreshBlueboxSensu");

		LoginPage loginPage = new LoginPage(driver);
		String username = args[0];
		String password = args[1];
		loginPage.login(username, password);

		SecondAuthPage secondAuthPage = new SecondAuthPage(driver);
		secondAuthPage.auth();
		secondAuthPage.setAuthListener(new AuthListener() {
			public void onAuthSuc() {
				try {
					monitorSensu();
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void monitorSensu() throws AWTException {
		// 认证成功后，要新开个标签，这里使用selenium的模拟按键不起作用，改用java自带的模拟按键
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);

		String currentWindow = driver.getCurrentUrl();

		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		while (it.hasNext()) {// 找到新标签，然后在新标签打开sensu页面
			if (currentWindow == it.next()) {
				continue;
			}
			WebDriver window = driver.switchTo().window(it.next());
			SensuPage sensuPage = new SensuPage(window);
			sensuPage.startMonitor();
		}
	}
}
