package factory;

import payment.CreditCardPayment;
import payment.NetbankingPayment;
import payment.RazorPayCreditCardPayment;
import payment.RazorPayNetbankingPayment;
import payment.RazorPayUPIPayment;
import payment.UPIPayment;

public class RazorPayPaymentFactory implements PaymentFactory{

	@Override
	public CreditCardPayment createCreditCardPayment() {
		return new RazorPayCreditCardPayment();
	}

	@Override
	public NetbankingPayment createNetbankingPayment() {
		return new RazorPayNetbankingPayment();
	}

	@Override
	public UPIPayment createUPIPayment() {
		return new RazorPayUPIPayment();
	}

}
