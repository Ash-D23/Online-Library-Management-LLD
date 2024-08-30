package factory;

import payment.CreditCardPayment;
import payment.NetbankingPayment;
import payment.PhonePeCreditCardPayment;
import payment.PhonePeNetbankingPayment;
import payment.PhonePeUPIPayment;
import payment.UPIPayment;

public class PhonePePaymentFactory implements PaymentFactory{

	@Override
	public CreditCardPayment createCreditCardPayment() {
		return new PhonePeCreditCardPayment();
	}

	@Override
	public NetbankingPayment createNetbankingPayment() {
		return new PhonePeNetbankingPayment();
	}

	@Override
	public UPIPayment createUPIPayment() {
		return new PhonePeUPIPayment();
	}

}
