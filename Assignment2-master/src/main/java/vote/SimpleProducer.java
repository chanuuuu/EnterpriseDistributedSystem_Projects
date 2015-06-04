package vote;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class SimpleProducer {
    private static Producer<String, String> producer;
    private final Properties c = new Properties();
    public SimpleProducer(){
        Properties props = new Properties();
        props.put("metadata.broker.list", "54.149.84.25:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
    }
    // The message is sent to a randomly selected partition registered in ZK


    public void sendEmail(String mod_email,String poll_result){

        new SimpleProducer();
        String topic = "CmpE273Assignment2";
        mod_email = "chanikya.mandapathi@sjsu.edu";
        String msg = mod_email+":010039120:" + poll_result;
        System.out.println(msg);
        //KeyedMessage<Integer, String> data = new KeyedMessage<>(topic, msg);
        //KeyedMessage<String, String> data = new KeyedMessage<>(topic, msg);
        //producer.send(data);
        KeyedMessage<String,String> data = new KeyedMessage<String,String>(topic,msg);
        producer.send(data);
        producer.close();
    }
}
