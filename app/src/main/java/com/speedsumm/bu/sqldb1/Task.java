package com.speedsumm.bu.sqldb1;

public class Task {
    int _id;
    public int _completed;
    public String _taskName;
    public String _taskBody;
    public String _expDate;

    public Task() {}

    public Task( int _completed,String _taskName, String _taskBody, String _expDate) {

        this._taskName = _taskName;
        this._taskBody = _taskBody;
        this._expDate = _expDate;
        this._completed = _completed;
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

    public String get_taskBody() {
        return _taskBody;
    }

    public void set_taskBody(String _taskBody) {
        this._taskBody = _taskBody;
    }

    public String get_expDate() {
        return _expDate;
    }

    public void set_expDate(String _expDate) {
        this._expDate = _expDate;
    }
}
