import {Component, OnInit} from "@angular/core";
import {TaskService} from "./service/task.service";
import * as _ from "lodash";
import {MdDialog, MdSnackBar} from "@angular/material";
import {DeleteDialogComponent} from "../delete-dialog/delete-dialog.component";
@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  checked = false;
  taskList = [];
  title = '';
  taskDone = [];
  taskNotDone = [];
  task = {};

  constructor(private taskService: TaskService, public mdDialog: MdDialog, public snackBar: MdSnackBar) {
  }

  ngOnInit() {
    //  this.title="fse";
    this.taskService.get().subscribe(res => {
      this.taskList = res;
      this.taskDone = this.taskList.filter(tsk => tsk.status);
      this.taskNotDone = this.taskList.filter(tsk => !tsk.status);
    });
  }

  setTask(task) {
    this.task = task;
  }

  addNewTask(newValue) {
    if (newValue) {
      const task = {title: newValue, status: false};
      this.taskService.create(task).subscribe(res => {
        this.title = '';
        this.snackBar.open('Task has been created', null, {
          duration: 2000,
        });
        this.taskList.push(res);
        this.taskNotDone.push(res);
      });
    }
  }

  removeTask(id, isDone) {
    const dilog = this.mdDialog.open(DeleteDialogComponent);
    dilog.afterClosed().subscribe(res => {
      console.log(res);
      if (res) {
        this.taskService.remove(id).subscribe( res => {
          this.snackBar.open('Task has been deleted', null, {
            duration: 2000,
          });
          this.removeTaskFromList(id, isDone);
        });
      }
    }, (error) => {
      this.snackBar.open('Error while adding new list', null, {
        duration: 2000,
      });
    });
  }

  DoneTask(event, type) {
    type = 'done';
    if (event) {
      const task = event;
      if (task.status && type === 'inprogress') {
        task.status = false;
      } else if (!task.status && type === 'done') {
        task.status = true;
      } else {
        return;
      }
      this.taskService.update(task._id, task).subscribe(res => {
        this.removeTaskFromList(task._id, !task.status);
        type === 'inprogress' ? this.taskNotDone.push(task) : this.taskDone.push(task);
        this.snackBar.open('Task has been moved to Done', null, {
          duration: 2000,
        });
      });
    }
    ;
  }

  removeTaskFromList(id, isDone) {
    const idx = _.findIndex(isDone ? this.taskDone : this.taskNotDone, {_id: id});
    _.remove(isDone ? this.taskDone : this.taskNotDone, tsk => tsk._id == id);
  }

}
