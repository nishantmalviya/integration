package org.KafkaEnrichment.config;

import java.util.HashMap;
import java.util.Map;

import org.KafkaEnrichment.model.Request;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
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



@EnableKafka
@Configuration
public class KafkaConfig {
String bootstrapServers="localhost:9092";
	
	String AUTO_OFFSET_RESET_CONFIG = "earliest";
	String GROUP_ID_CONFIG = "kafka-Integration";
	
	String TOPIC = "ubuntu-custom-topic";
	String GROUP = "kafka-integration-grp";
	
	@Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Serdes.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Serdes.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Serdes.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Serdes.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET_CONFIG);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Request> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(producerConfigs());
    }
    
    @Bean
    public ProducerFactory<String, Request> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Request> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Request>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Request> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    
    
}
