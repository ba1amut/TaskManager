package com.speedsumm.bu.sqldb;

public class Task {
    int _id;
    public int _completed;
    public String _taskName;

    public Task() {}

    public Task(int _id, int _completed, String _taskName) {
        this._id = _id;
        this._completed = _completed;
        this._taskName = _taskName;
    }

    public Task(int _completed, String _taskName) {
        this._completed = _completed;
        this._taskName = _taskName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_completed() {
        return _completed;
    }

    public void set_completed(int _completed) {
        this._completed = _completed;
    }

    public String get_taskName() {
        return _taskName;
    }

    public void set_taskName(String _taskName) {
        this._taskName = _taskName;
    }
}
