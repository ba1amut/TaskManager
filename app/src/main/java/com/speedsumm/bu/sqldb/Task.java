package com.speedsumm.bu.sqldb;

/**
 * Created by bu on 06.07.2016.
 */
public class Task {
    int _id;
    public int _dateTask;
    public String _taskName;

    public Task() {}

    public Task(int _id, int _dateTask, String _taskName) {
        this._id = _id;
        this._dateTask = _dateTask;
        this._taskName = _taskName;
    }

    public Task(int _dateTask, String _taskName) {
        this._dateTask = _dateTask;
        this._taskName = _taskName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_dateTask() {
        return _dateTask;
    }

    public void set_dateTask(int _dateTask) {
        this._dateTask = _dateTask;
    }

    public String get_taskName() {
        return _taskName;
    }

    public void set_taskName(String _taskName) {
        this._taskName = _taskName;
    }
}
