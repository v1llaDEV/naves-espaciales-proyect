package com.v1lladev.naves.espaciales.configurations;

import com.v1lladev.naves.espaciales.dto.requests.NaveEspacialRequest;
import com.v1lladev.naves.espaciales.kafka.desealizers.NaveEspacialRequestDeserializer;
import com.v1lladev.naves.espaciales.kafka.serializers.NaveEspacialRequestSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Conditional(ScheduledCondition.class)
@Slf4j
@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(props);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("nave-espacial-topic").partitions(3).replicas(1).build();
    }

    private ProducerFactory<String, NaveEspacialRequest> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, NaveEspacialRequestSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, NaveEspacialRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "consumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,
                "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,
                "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                NaveEspacialRequest.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, NaveEspacialRequest> consumerFactory() {
        /*return new DefaultKafkaConsumerFactory<>(consumerProps());*/
        return new DefaultKafkaConsumerFactory<>(
                consumerProps(),
                new StringDeserializer(),
                naveEspacialRequestDeserializer()
        );
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NaveEspacialRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NaveEspacialRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

    @Bean
    public Deserializer<NaveEspacialRequest> naveEspacialRequestDeserializer() {
        return new NaveEspacialRequestDeserializer();
    }

    @Bean
    public Serializer<NaveEspacialRequest> naveEspacialRequestSerializer() {
        return new NaveEspacialRequestSerializer();
    }
}
