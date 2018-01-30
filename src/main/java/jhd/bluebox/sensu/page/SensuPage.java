package jhd.bluebox.sensu.page;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import jhd.bluebox.sensu.CountDownTextProgressBar;
import jhd.bluebox.sensu.Mp3Player;
import jhd.bluebox.sensu.Tickets;
import jhd.bluebox.sensu.Tickets.TicketListener;

public class SensuPage extends BasePage {

	@FindBy(xpath = "/html/body/ng-view/div/div/div/div/div[2]/table/tbody")
	WebElement tbody;

	public SensuPage(WebDriver driver) {
		super(driver);
		driver.get("https://control.cn01.bluebox.net/pek01/sensu/#/events");
	}

	private Set<String> getData() {

		Set<String> tickets = new HashSet<String>();
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		// System.out.println(trs.size() + "getData");
		// 要等到这个表格元素存在 才是真正的刷新完毕
		// WebDriverWait wait = new WebDriverWait(driver, 15);
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/ng-view/div/div/div/div/div[2]/table/tbody")));

		for (int i = 0; i < trs.size(); i++) {
			List<WebElement> tds = trs.get(i).findElements(By.tagName("td"));
			if (tds != null && tds.size() >= 4) {// 1 2 5 4
				String ID = tds.get(1).getText() + "/" + tds.get(2).getText() + "/" + tds.get(5).getText();
				int existTime = Integer.valueOf(tds.get(4).getText());
				//存在超过2分钟的工单需要记录，否则先忽略，因为很多类型的工单会短时间内自动关闭
				if (existTime >= 2) {
					tickets.add(ID);
				}else {
					System.out.println("有一个新来的工单，存在时间小于2分钟，如果存在时间超过2分钟将会被统计");
				}
				// System.out.println(ID);
			}
		}
		return tickets;
	}

	public void startMonitor() {
		Tickets tickets = new Tickets();
		// 休眠三秒，因为刚加载好页面就立刻获取数据，tbody里面的列数是0，无法初始化最终就有的工单数据
		try {
			Thread.sleep(3 * 1000l);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		tickets.init(getData());
		tickets.setTicketListener(new TicketListener() {

			public void onTicketOpen(String ticket) {
				System.out.println("new ticket: " + ticket);
				Mp3Player player = new Mp3Player();
				player.play();
			}

			public void onTicketClose(String ticket) {
				System.out.println("closed ticket: " + ticket);
			}
		});

		while (true) {
			try {
				CountDownTextProgressBar.countdown();
				int total = tickets.merge(getData());
				System.out.println(new Date().toString() + " <-> total ： " + total);
			} catch (Exception e) {
				e.printStackTrace();
				Mp3Player mp3Player=new Mp3Player();
				mp3Player.playErr();
			}
		}
	}
}
