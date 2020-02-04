package org.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class MyItemWriter<User> implements ItemWriter<User>{


	@Override
	public void write(List<? extends User> items) throws Exception {
		items.stream().forEach(item ->{
			System.out.println("***********"+item);
		});
	}

}
