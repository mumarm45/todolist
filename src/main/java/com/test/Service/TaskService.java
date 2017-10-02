package com.test.Service;

import com.test.Domain.Task;
import com.test.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mumarm45 on 01/10/2017.
 */
@Service
public interface TaskService {
    List<Task> list();
    Task getOne(String id);
    Task create(Task post);
    Task update(String id,Task task);
    void delete(String id);
}
