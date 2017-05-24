package entity.enumiration;

/**
 * Created by Александр Горшов on 23.05.2017.
 */
public enum Mark {

    One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10);
    Integer mark;

    Mark(Integer mark) {
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }
}
