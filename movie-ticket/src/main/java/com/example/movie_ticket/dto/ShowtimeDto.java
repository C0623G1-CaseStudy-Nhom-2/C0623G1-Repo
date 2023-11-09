package com.example.movie_ticket.dto;

import com.example.movie_ticket.model.Employee;
import com.example.movie_ticket.model.Movie;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class ShowtimeDto implements Validator {
    private Long id;
    private Movie movie;
    @NotBlank(message = "Không được bỏ trống ngày chiếu")
    private String showDate;
    @NotBlank(message = "Không được bỏ trống giờ khởi chiếu")
    private String startTime;
    @NotBlank(message = "Không được bỏ trống giờ kết thúc")
    private String endTime;
    private Float price;
    private Employee employee;

    public ShowtimeDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShowtimeDto showtimeDto = (ShowtimeDto) target;
        if (LocalDate.parse(showtimeDto.showDate).isAfter(LocalDate.now())){
            errors.rejectValue("showDate",null,"Không được nhập ngày quá khứ");
        }
    }
}
