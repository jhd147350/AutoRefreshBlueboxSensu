package jhd.bluebox.sensu;

import java.util.concurrent.TimeUnit;

public class CountDownTextProgressBar {

	static int TIME_INT = Config.INTERVAL;

	static public void countdown() throws InterruptedException {
		// 25*3 75s
		// =========================
		// ---
		// 进度总数
		int SUM = TIME_INT / 3;
		boolean isFirst = true;
		int N = 3;

		// 倒计时次数
		int count = TIME_INT;

		while (count > 0) {
			count--;
			if (!isFirst) {
				System.out.print('\r');
			}
			isFirst = false;
			// 将 倒计时除3得到 进度
			int realCount = count / N;
			TimeUnit.SECONDS.sleep(1);
			System.out.print("|");
			for (int i = 0; i < (SUM - realCount); i++) {
				System.out.print("=");
			}
			for (int i = 0; i < (realCount); i++) {
				System.out.print("-");
			}
			if (count < 10) {
				System.out.print("| 0" + count);
			} else {
				System.out.print("| " + count);
			}
			System.out.print("s");
		}
		System.out.print("\n");
	}
}
