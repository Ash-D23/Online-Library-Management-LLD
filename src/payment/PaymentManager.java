package payment;

import java.util.Date;

import model.BookTransaction;

public class PaymentManager {
	
	private static PaymentManager instance = null;
	
	private PaymentManager() {
		
	}
	
	public static PaymentManager getInstance() {
		
		if(instance == null) {
			instance = new PaymentManager();
		}
		
		return instance;
	}
	
	public void initialise() {
		
		
	}

	public BookTransaction process(Double amount) {
		// TODO Auto-generated method stub
		BookTransaction t = new BookTransaction();
		
		t.setAmount(amount);
		
		UPIPayment upi = new RazorPayUPIPayment();
		boolean payResult = upi.processUPIPayment(amount);
		
		if(payResult) {
			t.setStatus("complete");
		}else {
			t.setStatus("failed");
		}
		
		t.setDate(new Date());
		
		return t;
		
		
	}
}
