package org.KafkaEnrichment;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaRequestStream {
	KStreamBuilder builder = new KStreamBuilder();
	KTable<String, String> requestStream = builder.table(Serdes.String(), Serdes.String(), "requestTopic");
	KTable<String, String> responseStream = builder.table(Serdes.String(), Serdes.String(), "responseTopic");
	KTable<String, String> joinTable = requestStream.join(responseStream, (request, response) ->  "left=" + request + ", right=" + response );
//	joinTable.to("my-kafka-stream-table-inner-join-out");
	
}
