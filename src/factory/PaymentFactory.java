package factory;

import payment.CreditCardPayment;
import payment.NetbankingPayment;
import payment.UPIPayment;

public interface PaymentFactory {
	
	public CreditCardPayment createCreditCardPayment();
	public NetbankingPayment createNetbankingPayment();
	public UPIPayment createUPIPayment();
		
}
