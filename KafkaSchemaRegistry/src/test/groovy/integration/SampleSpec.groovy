package integration;

import spock.lang.Specification;
import com.integration.service.KafkaSender;
import org.springframework.kafka.core.KafkaTemplate;

import com.integration.config.Configurations
import com.integration.model.User;

public class SampleSpec extends Specification{
	KafkaSender kafkaSender;
	KafkaTemplate kafkaTemplate;
	User user;

	def setup() {
		kafkaTemplate = Mock(KafkaTemplate);
		kafkaSender = new KafkaSender();
		kafkaSender.kafkaTemplate = kafkaTemplate;
	}


	def "Check sending message to kafka"() {
		given:
		user = User.newBuilder().setName("John").setAge(25).build();
		
		when:
		kafkaSender.send(user);

		then:
		user.getName() == "John";
	}
}
