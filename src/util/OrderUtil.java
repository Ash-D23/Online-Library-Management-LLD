package util;

import java.util.List;

import model.Order;

public class OrderUtil {
	public static void viewOrders(List<Order> orderList) {
		System.out.println("View Orders");
		System.out.println("----------");
		
		System.out.println("Id\tAmount\tStatus");
		
		for(Order o : orderList) {
			System.out.println(""+ o.getOrderId() + "\t" + o.getAmount() + "\t" + o.getStatus());
		}
	}
}
