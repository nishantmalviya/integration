package org.batch.config;

import java.util.HashMap;
import java.util.Map;

import org.batch.model.User;
import org.batch.processor.MyItemProcessor;
import org.batch.writer.MyItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class MySpringBatchConfiguration {
	@Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<User> itemReader,
                   ItemProcessor<User, User> itemProcessor,
                   ItemWriter<User> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<User> itemReader() {

        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/sample.txt"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public ItemProcessor<User, User> itemProcessor(){
    	return new MyItemProcessor();
    }
    
    @Bean
    public ItemWriter<User> itemWriter(){
    	return new MyItemWriter<User>();
    }
    
//    @Bean
//    public LineMapper<User> lineMapper() {
//
//        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
//        FixedLengthTokenizer lineTokenizer = new FixedLengthTokenizer();
//
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames(new String[]{"id", "name", "dept", "salary"});
//        lineTokenizer.setColumns(new Range(1,3),new Range(4,10),new Range(11,13),new Range(14,16));
//
//        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(User.class);
//
//        defaultLineMapper.setLineTokenizer(lineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return defaultLineMapper;
//    }
    
    @Bean
    public PatternMatchingCompositeLineMapper<User> lineMapper() {
    	PatternMatchingCompositeLineMapper<User> lineMapper =
    		new PatternMatchingCompositeLineMapper<>();

    	FixedLengthTokenizer headerTokenizer = new FixedLengthTokenizer();
    	headerTokenizer.setStrict(false);
    	headerTokenizer.setNames(new String[]{"id", "name", "dept", "salary"});
    	headerTokenizer.setColumns(new Range(1,4),new Range(5,13),new Range(14,16),new Range(17,19));
        
    	FixedLengthTokenizer bodyTokenizer = new FixedLengthTokenizer();
    	bodyTokenizer.setStrict(false);
    	bodyTokenizer.setNames(new String[]{"id", "name", "dept", "salary"});
    	bodyTokenizer.setColumns(new Range(1,4),new Range(5,6),new Range(7,9),new Range(10,12));
    	
    	Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
    	tokenizers.put("????BH*", headerTokenizer);
    	tokenizers.put("????BI*", bodyTokenizer);

    	lineMapper.setTokenizers(tokenizers);

    	BeanWrapperFieldSetMapper<User> headerFieldMapper = new BeanWrapperFieldSetMapper<>();
    	headerFieldMapper.setTargetType(User.class);
    	
    	BeanWrapperFieldSetMapper<User> bodyFieldMapper = new BeanWrapperFieldSetMapper<>();
    	bodyFieldMapper.setTargetType(User.class);
        
    	Map<String, FieldSetMapper<User>> mappers = new HashMap<>(2);
    	mappers.put("????BH*", headerFieldMapper);
    	mappers.put("????BI*", bodyFieldMapper);

    	lineMapper.setFieldSetMappers(mappers);

    	return lineMapper;
    }
}
