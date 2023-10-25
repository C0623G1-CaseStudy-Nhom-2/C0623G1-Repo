package codegym.vn.cs_md4.bookings.model;

import codegym.vn.cs_md4.customers.model.Customer;
import codegym.vn.cs_md4.employees.model.Employee;
import codegym.vn.cs_md4.films.model.Film;
import codegym.vn.cs_md4.times.model.Time;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "DATE")
    private String bookingDate;
    private double deposit;
    private int quantity;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "time_id",referencedColumnName = "id")
    private Time time;

    public Booking() {
    }

    public Booking(int id, String bookingDate, double deposit, boolean isDeleted) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.deposit = deposit;
        this.isDeleted = isDeleted;
    }

    public Booking(Integer id, String bookingDate, double deposit, boolean isDeleted, Film film, Customer customer, Employee employee, Time time) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.deposit = deposit;
        this.isDeleted = isDeleted;
        this.film = film;
        this.customer = customer;
        this.employee = employee;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
