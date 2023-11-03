package com.example.movie_ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "showtime_id", referencedColumnName = "id")
    private ShowTime showTime;
    @OneToMany(mappedBy = "booking")
    private Set<SeatBooking> seatBookings;
    @Column(columnDefinition = "int(1) default 0")
    private boolean isDeleted;
    private String codeBooking;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Set<SeatBooking> getSeatBookings() {
        return seatBookings;
    }

    public void setSeatBookings(Set<SeatBooking> seatBookings) {
        this.seatBookings = seatBookings;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
