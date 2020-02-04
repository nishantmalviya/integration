package org.batch.model;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for Interface 19B Payment Updates from CPC. The model has the definition for 
 * the Batch header, body and trailer records. This is related to Interface 19b.
 * @author B206777
 */
@Getter
@Setter
public class PaymentUpdates extends CABillingModel{
	private static final long serialVersionUID = -1378838439168332553L;
	//Attributes for BH,LT and TT
	@Pattern(regexp = "([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))",message = "{I19b.transactionDate}")
	private String transactionDate;
	@Pattern(regexp = "^(BH|BI|LT|TT)")
	private String recordId;
	@Pattern(regexp = "([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))")
	private String depositDate;
	@Pattern(regexp = "(^\\d+$)")
	private String lockboxNbr;
	@Pattern(regexp = "(^\\d+$)")
	private String totalNumberOfBatch;
	@Pattern(regexp = "(^\\d+$)")
	private String totalNumberOfBatchItem;
	@Pattern(regexp = "(^\\d+$)")
	private String totalNumberOfCheck;
	private String totalAmountOfBatch;
	private String filler;
	private String boaInfoId;
	private String boaTransactionTime;

	//Attributes for BI
	@Pattern(regexp = "(^\\d+$)")
	private String batchNumber;
	@Pattern(regexp = "(^\\d+$)")
	private String batchItemNumber;
	private String pmtIdentifier;
	private String checkAmount;
	private String groupNumber;
	private String billDueDate;
	private String checkDigit1;
	private String pmtMethod;
	private String checkDigit2;
	
}   