package org.batch.processor;

import org.batch.model.PaymentUpdates;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyItemProcessor implements ItemProcessor<PaymentUpdates, PaymentUpdates> {

	@Autowired
	private Validator<PaymentUpdates> validator;

	@Override
	public PaymentUpdates process(PaymentUpdates item) throws Exception {
		validator.validate(item);
		
		return item;
	}

}
