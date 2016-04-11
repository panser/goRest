package ua.org.gostroy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.org.gostroy.domain.entity.User;

/**
 * Created by Sergey on 4/11/2016.
 */
public interface UserRepository extends MongoRepository<User, Long> {
}
