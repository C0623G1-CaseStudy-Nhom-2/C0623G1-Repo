package com.example.movie_ticket.dto.customer;

import com.example.movie_ticket.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;

public class CustomerDto implements Validator {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String birthday;
    @Pattern(regexp = "(^\\d{9}$)|(^\\d{12}$)",message = "Số CCCD|CMND của bạn chưa đúng xin vui lòng nhập lại")
    private Long idCard;
    private String address;
    private Account account;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String fullName, String email, String phoneNumber, String birthday, Long idCard, String address, Account account) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.idCard = idCard;
        this.address = address;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;
        if (customerDto.getFullName().length() < 5){
            errors.rejectValue("fullName",null,"Tổng số ký tự của tên phải lớn hơn hoặc bằng 5");
        } else if (customerDto.getFullName().length() > 45) {
            errors.rejectValue("fullName",null,"Tổng số ký tự của tên phải bé hơn hoặc bằng 45");
        } else if (!customerDto.getEmail().matches("(^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*@(gmail)|(email)(\\.com)|(\\.email)|(\\.com)?[\\.vn]{3}$)")) {
            errors.rejectValue("email",null,"Gmail bạn nhập chưa đứng yêu cầu xin vui lòng nhập lại");
        } else if (!customerDto.getPhoneNumber().matches("\"((09|03|07|08|05)+([0-9]{8})\\\\b)\"")) {
            errors.rejectValue("phoneNumber",null,"Số điện thoại bạn nhập chưa đúng xin lòng nhập lại");
        } else if (!customerDto.getBirthday().matches("^(0[1-9]|[12][0-9]|3[01])[-/.]([0-9]|0[0-9]|1[012])[-/.]\\d\\d\\d\\d$")) {
            errors.rejectValue("birthday",null,"Ngày tháng năm sinh bạn nhập chưa đúng yêu cầu xin vui lòng nhập lại");
        }
    }
}
