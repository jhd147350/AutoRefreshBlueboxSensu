package jhd.bluebox.sensu;

import java.util.Iterator;
import java.util.Set;

public class Tickets {

	public static Set<String> data;

	public void init(Set<String> data) {
		Tickets.data = data;
		for (String string : data) {
			System.out.println(string);
		}
		System.out.println("init success");
	}

	private TicketListener ticketListener;

	//return tickets number
	public int merge(Set<String> newData) {
		// 添加新来的工单
		for (String ticket : newData) {
			if (!data.contains(ticket)) {
				if (ticketListener != null) {
					ticketListener.onTicketOpen(ticket);
				}
				data.add(ticket);
				//System.out.println("add " + ticket);
			}
		}

		// 去掉关闭的工单 遍历移除数组时需要用迭代器
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			String ticket = it.next();
			if (!newData.contains(ticket)) {
				it.remove();
				if (ticketListener != null) {
					ticketListener.onTicketClose(ticket);
				}
				//System.out.println("remove " + ticket);
			}
		}
		return data.size();
	}

	public TicketListener getTicketListener() {
		return ticketListener;
	}

	public void setTicketListener(TicketListener ticketListener) {
		this.ticketListener = ticketListener;
	}

	public interface TicketListener {
		void onTicketOpen(String ticket);

		void onTicketClose(String ticket);
	}

}
