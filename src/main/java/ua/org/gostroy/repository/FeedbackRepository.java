package ua.org.gostroy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.org.gostroy.domain.entity.Feedback;

/**
 * Created by Sergey on 4/11/2016.
 */
public interface FeedbackRepository extends MongoRepository<Feedback, Long> {
}
