package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.repository.IBookingRepo;
import com.example.movie_ticket.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private IBookingRepo bookingRepo;

    @Override
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<Booking> findBookingUsername(String username, Pageable pageable) {
        return bookingRepo.findBookingUsername(username, pageable);
    }

    @Override
    public Optional<Booking> findBookingById(Long id) {
        return bookingRepo.findById(id);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    @Override
    public Page<Booking> showAllBooking(Pageable pageable, String phone, String name,String date) {
        return bookingRepo.showAllBooking(pageable, "%" + phone + "%", "%" + name + "%","%" + date + "%");
    }

    @Override
    public Booking findByIdBooking(Long id) {
        return bookingRepo.findById(id).get();
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepo.cancelBooking(id);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepo.findById(id).get();
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepo.save(booking);
        booking.setDatePurchased(booking.getDatePurchased());
    }

    @Override
    public void sendEmail(Booking booking) {
        String toAddress = booking.getCustomer().getEmail();
        String fromAddress = "trung11041990a1@gmail.com";
        String senderName = "DATPHIM";
        String subject = "Thư Xác Nhận Đặt Vé Xem Phim";
//        String content = "<body style=\"margin: 0; padding: 0\">\n" +
//                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
//                "  <tr>\n" +
//                "    <td  style=\" background: #f8f8f8; \">\n" +
//                "    </td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;\">\n" +
//                "      <p style=\"font-weight:bold;\">\nXin chào :<span\"> " + booking.getCustomer().getAccount().getUsername() + "</span></p>\n" +
//                "      <p>MOVIES24H xin xác nhận bạn đã đặt vé xem phim thành công.</p>\n" +
//                "      <ul>\n" +
//                "        <li>Mã đơn hàng: " + booking.getCodeBooking() + "</li>\n" +
//                "        <li>Tên phim: " + booking.getShowTime().getMovie().getTitle() + "</li>\n" +
//                "        <li>Ngày: " + booking.getShowTime().getShowDate() + "</li>\n" +
//                "        <li>Giá: " + booking.getTotalPrice() + "</li>\n" +
//                "        <li>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ tại MOVIES24H!</li>\n" +
//                "      </ul>\n" +
//                "    </td>\n" +
//                "  </tr>\n" +
//                "</table>\n" +
//                "</body>";
       String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Thư Xác Nhận Đặt Vé Xem Phim</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    .container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 0 auto;\n" +
                "      background-color: #f8f8f8;\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #333;\n" +
                "      font-size: 24px;\n" +
                "      margin-bottom: 20px;\n" +
                "    }\n" +
                "    p {\n" +
                "      color: #555;\n" +
                "      font-size: 16px;\n" +
                "      line-height: 1.4;\n" +
                "      margin-bottom: 10px;\n" +
                "    }\n" +
                "    ul {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      list-style-type: none;\n" +
                "    }\n" +
                "    li {\n" +
                "      margin-bottom: 5px;\n" +
                "    }\n" +
                "    .highlight {\n" +
                "      font-weight: bold;\n" +
                "      color: #007bff;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      margin-top: 20px;\n" +
                "      text-align: center;\n" +
                "      color: #888;\n" +
                "      font-size: 14px;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <p>Xin chào <span class=\"highlight\">" + booking.getCustomer().getAccount().getUsername() + "</span>,</p>\n" +
                "    <img style='heigth : 1000px;withd :500px' src='"+booking.getShowTime().getMovie().getAvatar()+"'>\n" +
                "      <p>Cảm ơn quý khách đã mua vé tại MOVIES24H vào ngày <span>"+booking.getDatePurchased().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +"</span> với mã đơn hàng <span style='font-weight: bold'> "+booking.getCodeBooking()+"</span></p>\n" +
                "      <p>Tổng giá trị đơn hàng là <span>"+booking.getTotalPrice()+"VNĐ</p>\n" +
                "      <p>Chúc quý khách có trải nghiệm tuyệt vời với dịch vụ tại MOVIES24H</p>\n" +
                "    <p class=\"footer\">MOVIES24H | Số 123, Đường ABC, Thành phố XYZ | 0123456789</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8"); // Đặt encoding là UTF-8
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true); // Sử dụng HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }

    @Override
    public List<Booking> showHistoryBookingDate() {
        return bookingRepo.showHistoryBookingDate();
    }

    @Override
    public List<Booking> showHistoryBookingMonth() {
       return bookingRepo.showHistoryBookingMonth();
    }

    @Override
    public List<Booking> showHistoryBookingYear() {
       return bookingRepo.showHistoryBookingYear();
    }

    @Override
    public List<Booking> showHistoryBookingOfMonth(int month) {
        return bookingRepo.showHistoryBookingOfMonth(month);
    }

    @Override
    public void sendEmailCancel(Booking booking) {
        String toAddress = booking.getCustomer().getEmail();
        String fromAddress = "trung11041990a1@gmail.com";
        String senderName = "DATPHIM";
        String subject = "Thư Thông Báo Hủy Đơn Hàng";
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Thư Thông Báo Hủy Đơn Hàng</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    .container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 0 auto;\n" +
                "      background-color: #f8f8f8;\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #333;\n" +
                "      font-size: 24px;\n" +
                "      margin-bottom: 20px;\n" +
                "    }\n" +
                "    p {\n" +
                "      color: #555;\n" +
                "      font-size: 16px;\n" +
                "      line-height: 1.4;\n" +
                "      margin-bottom: 10px;\n" +
                "    }\n" +
                "    ul {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      list-style-type: none;\n" +
                "    }\n" +
                "    li {\n" +
                "      margin-bottom: 5px;\n" +
                "    }\n" +
                "    .highlight {\n" +
                "      font-weight: bold;\n" +
                "      color: #007bff;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      margin-top: 20px;\n" +
                "      text-align: center;\n" +
                "      color: #888;\n" +
                "      font-size: 14px;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <h1>Thư Thông Báo Hủy Đơn Hàng</h1>\n" +
                "    <p>Xin chào <span class=\"highlight\">" + booking.getCustomer().getAccount().getUsername() + "</span>,</p>\n" +
                "    <p>MOVIES24H xin lỗi về vấn đề bản quyền nên chúng tôi không thể tiếp tục phát sóng phim <span class=\"highlight\">" + booking.getShowTime().getMovie().getTitle() + "</span>.</p>\n" +
                "    <ul>\n" +
                "      <li>Chúng tôi sẽ hoàn số tiền <span class=\"highlight\">" + booking.getTotalPrice() + "</span> vào thời gian 3 - 6 tiếng.</li>\n" +
                "      <li>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ tại MOVIES24H!</li>\n" +
                "      <li>Rất xin lỗi quý khách về vấn đề này!</li>\n" +
                "    </ul>\n" +
                "    <p class=\"footer\">MOVIES24H | Địa chỉ của bạn | Số điện thoại của bạn</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8"); // Đặt encoding là UTF-8
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true); // Sử dụng HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }

    @Override
    public List<Booking> showBookingCancel(Long id) {
        return bookingRepo.showBookingCancel(id);
    }
}