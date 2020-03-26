package org.batch.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model to represent PaymentReversal Updates coming from CPC portal. This is related to Interface 19b.
 * @author B206777
 */
@Getter
@Setter
public class PaymentReversal extends CABillingModel {
	private static final long serialVersionUID = 5043032261517140014L;
	
	private String billingUnitIdentifier;
	private String amount;//-999999999.99
	private String depositDate;//MMDDYYYY
	private String paymentType;
	private String checkNumber;
	private String filler;
	private String reason;

}
