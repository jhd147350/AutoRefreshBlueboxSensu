package jhd.bluebox.sensu;

import java.util.HashSet;
import java.util.Set;

public class TestTicket {

	public static void main(String[] args) {
		Tickets tickets=new Tickets();
		Set<String> test=new HashSet<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		
		Set<String> test1=new HashSet<String>();
		test1.add("1");
		test1.add("2");
		test1.add("3");
		test1.add("4");
		test1.add("5");
		
		Set<String> test2=new HashSet<String>();
		test2.add("1");
		test2.add("2");
		test2.add("3");
		test2.add("4");
		
		Set<String> test3=new HashSet<String>();
		test3.add("1");
		test3.add("2");
		test3.add("6");
		test3.add("5");
		test3.add("7");
		tickets.init(test);
		
		tickets.merge(test1);
		
		
		tickets.merge(test2);
		
		
		tickets.merge(test3);
		
		for (String string : Tickets.data) {
			System.out.println(string);
		}
	}
}
