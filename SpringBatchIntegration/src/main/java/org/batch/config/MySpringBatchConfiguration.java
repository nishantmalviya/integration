package org.batch.config;

import java.util.HashMap;
import java.util.Map;

import org.batch.model.PaymentUpdates;
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
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.validator.SpringValidator;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MySpringBatchConfiguration {
	@Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<PaymentUpdates> itemReader,
                   ItemProcessor<PaymentUpdates, PaymentUpdates> itemProcessor,
                   ItemWriter<PaymentUpdates> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<PaymentUpdates, PaymentUpdates>chunk(100)
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
    public FlatFileItemReader<PaymentUpdates> itemReader() {

        FlatFileItemReader<PaymentUpdates> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/I19B.txt"));
        flatFileItemReader.setName("CSV-Reader");
//        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public ItemProcessor<PaymentUpdates, PaymentUpdates> itemProcessor(){
    	return new MyItemProcessor();
    }
    
    @Bean
    public ItemWriter<PaymentUpdates> itemWriter(){
    	return new MyItemWriter<PaymentUpdates>();
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean("true"));
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
	public org.springframework.validation.Validator validator() {
    	LocalValidatorFactoryBean localValidator = new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
    	localValidator.setValidationMessageSource(messageSource());
    	return localValidator;
	}
    
	@Bean
	public Validator<PaymentUpdates> springValidator() {
		SpringValidator<PaymentUpdates> springValidator = new SpringValidator<>();
		springValidator.setValidator(validator());
		return springValidator;
	}
	
    @Bean
    public PatternMatchingCompositeLineMapper<PaymentUpdates> lineMapper() {
    	PatternMatchingCompositeLineMapper<PaymentUpdates> lineMapper =
    		new PatternMatchingCompositeLineMapper<>();

    	FixedLengthTokenizer headerTokenizer = new FixedLengthTokenizer();
    	headerTokenizer.setStrict(false);
    	headerTokenizer.setNames(new String[]{"transactionDate","recordId", "depositDate", "lockboxNbr","batchNumber","totalNumberOfBatch"
    			,"totalNumberOfBatchItem","totalNumberOfCheck","totalAmountOfBatch","filler","boaInfoId","boaTransactionTime"});
    	headerTokenizer.setColumns(new Range(1,8),
    			new Range(9,10),
    			new Range(11,18),
    			new Range(19,24),
    			new Range(25,28),
    			new Range(29,33),
    			new Range(34,38),
    			new Range(39,49),
    			new Range(50,64),
    			new Range(65,74),
    			new Range(75,80));
    	
    	FixedLengthTokenizer bodyTokenizer = new FixedLengthTokenizer();
    	bodyTokenizer.setStrict(false);
    	bodyTokenizer.setNames(new String[]{"transactionDate","recordId", "depositDate", "lockboxNbr","batchNumber","batchItemNumber"
    			,"pmtIdentifier","checkAmount","groupNumber","billDueDate","checkDigit1","pmtMethod","checkDigit2"});
    	bodyTokenizer.setColumns(new Range(1,8),
    			new Range(9,10),
    			new Range(11,18),
    			new Range(19,24),
    			new Range(25,28),
    			new Range(29,31),
    			new Range(32,41),
    			new Range(42,52),
    			new Range(53,61),
    			new Range(62,67),
    			new Range(68,68),
    			new Range(69,79),
    			new Range(80,80));
    	
    	
    	FixedLengthTokenizer LTTokenizer = new FixedLengthTokenizer();
    	LTTokenizer.setStrict(false);
    	LTTokenizer.setNames(new String[]{"transactionDate","recordId", "depositDate", "lockboxNbr","batchNumber","totalNumberOfBatch"
    			,"totalNumberOfBatchItem","totalNumberOfCheck","totalAmountOfBatch","filler","boaInfoId","boaTransactionTime"});
    	LTTokenizer.setColumns(new Range(1,8),
    			new Range(9,10),
    			new Range(11,18),
    			new Range(19,24),
    			new Range(25,28),
    			new Range(29,31),
    			new Range(32,36),
    			new Range(37,41),
    			new Range(42,52),
    			new Range(53,64),
    			new Range(65,74),
    			new Range(75,80));
        
    	FixedLengthTokenizer TTTokenizer = new FixedLengthTokenizer();
    	TTTokenizer.setStrict(false);
    	TTTokenizer.setNames(new String[]{"transactionDate","recordId", "depositDate", "lockboxNbr","batchNumber","totalNumberOfBatch"
    			,"totalNumberOfBatchItem","totalNumberOfCheck","totalAmountOfBatch","filler","boaInfoId","boaTransactionTime"});
    	TTTokenizer.setColumns(new Range(1,8),
    			new Range(9,10),
    			new Range(11,18),
    			new Range(19,24),
    			new Range(25,28),
    			new Range(29,31),
    			new Range(32,36),
    			new Range(37,41),
    			new Range(42,52),
    			new Range(53,64),
    			new Range(65,74),
    			new Range(75,80));
    	
    	Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
    	tokenizers.put("????????BH*", headerTokenizer);
    	tokenizers.put("????????BI*", bodyTokenizer);
    	tokenizers.put("????????LT*", LTTokenizer);
    	tokenizers.put("????????TT*", TTTokenizer);
    	
    	lineMapper.setTokenizers(tokenizers);

    	BeanWrapperFieldSetMapper<PaymentUpdates> headerFieldMapper = new BeanWrapperFieldSetMapper<>();
    	headerFieldMapper.setTargetType(PaymentUpdates.class);
    	BeanWrapperFieldSetMapper<PaymentUpdates> bodyFieldMapper = new BeanWrapperFieldSetMapper<>();
    	bodyFieldMapper.setTargetType(PaymentUpdates.class);
    	BeanWrapperFieldSetMapper<PaymentUpdates> ltFieldMapper = new BeanWrapperFieldSetMapper<>();
    	ltFieldMapper.setTargetType(PaymentUpdates.class);
    	BeanWrapperFieldSetMapper<PaymentUpdates> ttFieldMapper = new BeanWrapperFieldSetMapper<>();
    	ttFieldMapper.setTargetType(PaymentUpdates.class);
    	
    	Map<String, FieldSetMapper<PaymentUpdates>> mappers = new HashMap<>(4);
    	mappers.put("????????BH*", headerFieldMapper);
    	mappers.put("????????BI*", bodyFieldMapper);
    	mappers.put("????????LT*", ltFieldMapper);
    	mappers.put("????????TT*", ttFieldMapper);

    	lineMapper.setFieldSetMappers(mappers);

    	return lineMapper;
    }
}
