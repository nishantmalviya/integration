package com.integration.config;

public interface Configurations {
	String bootstrapServers="192.168.0.114:9092";
	
	String AUTO_OFFSET_RESET_CONFIG = "earliest";
	String GROUP_ID_CONFIG = "kafka-Integration";
	String SPECIFIC_AVRO_READER_CONFIG = "true";
	String SCHEMA_REGISTRY_CONFIG = "schema.registry.url";
	
	String SCHEMA_REGISTRY_URL ="http://127.0.0.1:8081";
	
	String TOPIC = "ubuntu-custom-topic";
	String GROUP = "kafka-integration-grp";
}
