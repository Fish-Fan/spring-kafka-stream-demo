import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class ReaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReaderApplication.class, args);
    }

    @KafkaListener(id = "myId", topics = "spring-kafka-stream-practice-topic-1")
    public void listen(String in) {
        log.info("incoming message: {}", in);
    }
}
