package com.integration.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.integration.model.User;
import com.integration.service.MyKafkaConsumer;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@EnableKafka
@Configuration
public class KafkaConfig {

	    @Bean
	    public Map<String, Object> producerConfigs() {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Configurations.bootstrapServers);
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, Configurations.GROUP_ID_CONFIG);
	        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Configurations.AUTO_OFFSET_RESET_CONFIG);
	        props.put(Configurations.SCHEMA_REGISTRY_CONFIG,Configurations.SCHEMA_REGISTRY_URL);
	        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, Configurations.SPECIFIC_AVRO_READER_CONFIG);
	        return props;
	    }

	    @Bean
	    public ConsumerFactory<String, User> consumerFactory() {
	        return new DefaultKafkaConsumerFactory<>(producerConfigs());
	    }
	    
	    @Bean
	    public ProducerFactory<String, User> producerFactory() {
	        return new DefaultKafkaProducerFactory<>(producerConfigs());
	    }

	    @Bean
	    public KafkaTemplate<String, User> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }

	    @Bean
	    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, User>> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }
	    
	    @Autowired
	    private MyKafkaConsumer kafkaConsumer;
}
