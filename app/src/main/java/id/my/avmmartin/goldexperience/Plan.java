package id.my.avmmartin.goldexperience;

import java.util.Date;

final class Plan {
    int fk_placeid;
    int fk_userid;
    String name;
    Date date;
    Date time;
    String note;

    public Plan(int _fk_placeid, int _fk_userid, String _name, Date _date, Date _time, String _note) {
        fk_placeid = _fk_placeid;
        fk_userid = _fk_userid;

        name = _name;
        date = _date;
        time = _time;
        note = _note;
    }
}
