package org.batch.model;

import lombok.Getter;
import lombok.Setter;

/**
 * DirectPayments is the model that carries the payments made on ACI Portal with Credit Cards. 
 * These payments are specific for Direct Pay Customers. This mapping is related to Interface 19a.
 * @author B206777
 */
@Getter
@Setter
public class DirectPayments extends CABillingModel {

	private static final long serialVersionUID = -1969363933575613749L;
	
	//60 - Run Header
	private String recordId;
	private String seqNumber;
	private String runNumber;
	private String lockboxNumber;
	private String formatCd;
	private String filler1;
	private String businessDate;
	private String filler2;
	
	//40 - Batch Header
	private String batchRunNumber;
	private String mode;
	private String subremit;

	//01 Body Detail
	private String transNumber;
	private String sortPkt;
	private String batchNumber;
	private String transAmount;
	private String balDue;
	private String optAmount;
	private String billUnitId;
	private String actStartDate;
	private String checkNumber;
	private String dueDate;
	private String billNumber;
	private String groupNumber;
	private String systemId;
	
	// 41 Batch Trailer
	private String debits;
	private String credits;
	//Range not correct in mapping document
	private String debitAmount;
	//Range not correct in mapping document
	private String creditAmount;
	//Range not correct in mapping document
	private String lockboxNbr;
	//Range not correct in mapping document
	private String filler3;
	
	
	// Run Trailer 61
	private String hdrSeqNbr;
	private String runNbr;
	private String filler4;
	private String sortPatternId;
	private String filler5;
	
	// Transmission Trailer T 
	private String stubCount;
	private String stubAmount;
}
