package com.speedsumm.bu.sqldb1;

public class Task {
    int _id;
    private int _completed;
    private String _taskName;
    private String _taskBody;
    private long _expDate;

    public Task() {}

    public Task( int _completed,String _taskName, String _taskBody, long _expDate) {

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

    public long get_expDate() {
        return _expDate;
    }

    public void set_expDate(long _expDate) {
        this._expDate = _expDate;
    }
}
