package com.test.Repository;

import com.test.Domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mumarm45 on 01/10/2017.
 */
@Repository
public interface TaskRepository extends MongoRepository<Task,String> {
}
