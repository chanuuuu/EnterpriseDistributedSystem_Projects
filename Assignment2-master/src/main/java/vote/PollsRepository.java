package vote;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PollsRepository extends MongoRepository<Polls, String>{

}