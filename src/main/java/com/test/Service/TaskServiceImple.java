package com.test.Service;

import com.test.Domain.Task;
import com.test.Repository.TaskRepository;
import com.test.TaskNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mumarm45 on 01/10/2017.
 */
@Service
public class TaskServiceImple implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    TaskServiceImple(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @Override
    public Task getOne(String id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task create(Task task) {
        return taskRepository.insert(task);
    }

    @Override
    public Task update(String id, Task updatedTask) {
        Task task = getOne(id);
        if (task == null) {
            notFound(id);
        }

        return taskRepository.save(updatedTask);
    }

    @Override
    public void delete(String id) {
        Task task = getOne(id);
        if (task == null) {
            notFound(id);
        }
        taskRepository.delete(task);

    }


    public void notFound(String id) {
        throw new TaskNotFound("Task not found for this id" + id);
    }
}
