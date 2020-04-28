package id.my.avmmartin.goldexperience.data.model;

import java.util.Date;

import id.my.avmmartin.goldexperience.utils.Helper;

public class Plan {
    private int id;
    private int fkPlaceId;
    private int fkUserId;
    private String name;
    private Date date;
    private Date time;
    private String note;

    @Override
    public String toString() {
        return (
            getName() + "\n"
                + Helper.toDateFormat(getDate()) + " " + Helper.toTimeFormat(getTime()) + "\n"
                + note
        );
    }

    // constructor

    public Plan(int id, int fkPlaceId, int fkUserId, String name, Date date, Date time, String note) {
        setId(id);
        setFkPlaceId(fkPlaceId);
        setFkUserId(fkUserId);

        setName(name);
        setDate(date);
        setTime(time);
        setNote(note);
    }

    // getter

    public int getId() {
        return id;
    }

    public int getFkPlaceId() {
        return fkPlaceId;
    }

    public int getFkUserId() {
        return fkUserId;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    // setter

    public void setId(int id) {
        this.id = id;
    }

    private void setFkPlaceId(int fkPlaceId) {
        this.fkPlaceId = fkPlaceId;
    }

    private void setFkUserId(int fkUserId) {
        this.fkUserId = fkUserId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    private void setTime(Date time) {
        this.time = time;
    }

    private void setNote(String note) {
        this.note = note;
    }
}
