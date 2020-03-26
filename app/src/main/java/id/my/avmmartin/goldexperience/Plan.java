package id.my.avmmartin.goldexperience;

import java.util.Date;

final class Plan {
    int id;
    int fk_placeid;
    int fk_userid;
    String name;
    Date date;
    Date time;
    String note;

    public Plan(int _id, int _fk_placeid, int _fk_userid, String _name, Date _date, Date _time, String _note) {
        id = _id;
        fk_placeid = _fk_placeid;
        fk_userid = _fk_userid;

        name = _name;
        date = _date;
        time = _time;
        note = _note;
    }

    @Override public String toString() {
        return name + "\n" + Helper.to_date_format(date) + " " + Helper.to_time_format(time) + "\n" + note;
    }
}
