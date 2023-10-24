package codegym.vn.cs_md4.times.model;

import codegym.vn.cs_md4.bookings.model.Booking;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "times")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "time", unique = true)
    private int time;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    @OneToMany(mappedBy = "time")
    private Set<Booking> bookingSet;

    public Time() {
    }

    public Time(int id, int time, boolean isDeleted) {
        this.id = id;
        this.time = time;
        this.isDeleted = isDeleted;
    }

    public Time(int id, int time, boolean isDeleted, Set<Booking> bookingSet) {
        this.id = id;
        this.time = time;
        this.isDeleted = isDeleted;
        this.bookingSet = bookingSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time1 = (Time) o;
        return time == time1.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}
