package com.test.Controller;

import com.test.Domain.Task;
import com.test.Service.TaskServiceImple;
import com.test.TaskNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mumarm45 on 01/10/2017.
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {
    private TaskServiceImple taskServiceImple;
    @Autowired
    TaskController(TaskServiceImple taskServiceImple){
        this.taskServiceImple = taskServiceImple;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> list(){

        return taskServiceImple.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task create(@RequestBody Task task){

        return taskServiceImple.create(task);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Task get(@PathVariable("id") String id){
        Task task =  taskServiceImple.getOne(id);
        if (task==null){
            taskServiceImple.notFound(id);
        }
        return task;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Task update(@PathVariable("id") String id, @RequestBody Task task){

        return taskServiceImple.update(id,task);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id){

         taskServiceImple.delete(id);
        return   new ResponseEntity<>(org.springframework.http.HttpStatus.NO_CONTENT);
    }

}
