package org.batch.processor;

import org.batch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {
		return item;
	}

}
