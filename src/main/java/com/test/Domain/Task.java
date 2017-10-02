package com.test.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mumarm45 on 01/10/2017.
 */
@Document(collection = "task")
public class Task {

    public final static boolean IS_COMPLETED = true;
    public final static boolean  IS_PENDING = false;

    @Indexed(unique = true)
    private  String title;

    private boolean status;

    @Id
    private  String _id ;

    public  Task (){

    }

    public Task(String id, String title, boolean status) {
        this.title = title;
        this.status = status;
        this._id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + _id + '\'' +
                "title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
