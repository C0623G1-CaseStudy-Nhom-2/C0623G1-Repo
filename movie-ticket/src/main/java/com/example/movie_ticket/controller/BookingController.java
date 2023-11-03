package com.example.movie_ticket.controller;

import com.example.movie_ticket.dto.BookingDto;
import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.model.SeatBooking;
import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.service.IBookingService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.ISeatBookingService;
import com.example.movie_ticket.service.IShowTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private ISeatBookingService seatBookingService;
    @Autowired
    private IShowTimeService showTimeService;
    @Autowired
    private IMovieService movieService;
    @GetMapping("")
    public String showAllBooking(@RequestParam(defaultValue = "0",required = false) int page,
                                 @RequestParam(defaultValue = "",required = false) String dateSearch,
                                 @RequestParam(defaultValue = "",required = false) String phoneSearch,
                                 Model model){
        Pageable pageable = PageRequest.of(page,10);
        Page<Booking> bookingPage = bookingService.showAllBooking(pageable,phoneSearch,dateSearch);
        model.addAttribute("bookingPage",bookingPage);
        model.addAttribute("dateSearch",dateSearch);
        model.addAttribute("phoneSearch",phoneSearch);
        return "/booking/dashboard-admin-booking";
    }
    @GetMapping("/add")
    public String showFormCreateBooking(Model model,
                                        @RequestParam(defaultValue = "",required = false) String title){
        model.addAttribute("bookingDto", new BookingDto());
        return "/booking/create-booking";
    }

    @GetMapping("/view")
    public String showBookingDetail(@RequestParam Long id,
                                    Model model){
        Booking booking = bookingService.findByIdBooking(id);
        model.addAttribute("booking",booking);
        return "/booking/view-booking";
    }
    @PostMapping("/delete")
    public String deleteBooking(@RequestParam Long id,
                                RedirectAttributes redirectAttributes){
        Booking booking = bookingService.findById(id);
        if (booking != null){
            bookingService.deleteBooking(id);
            redirectAttributes.addFlashAttribute("success","Xóa đơn hàng thành công");
        }
        return "redirect:/booking";
    }
    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam Long idCancel,
                                RedirectAttributes redirectAttributes){
        Booking booking = bookingService.findById(idCancel);
        if (booking != null){
            bookingService.cancelBooking(idCancel);
            redirectAttributes.addFlashAttribute("success","Hủy đơn hàng thành công");
        }
        return "redirect:/booking";
    }
    @GetMapping("/edit/{id}")
    public String showFormUpdateBooking(Model model,
                                        @PathVariable Long id,
                                        @RequestParam Long movie){
        Booking booking = bookingService.findByIdBooking(id);
        List<SeatBooking> seatBookings = seatBookingService.getAll();
        List<ShowTime> showTimeList = showTimeService.getAllShowTime();
        Movie movies = movieService.findMovieById(movie);
        model.addAttribute("movies", movies);
        model.addAttribute("showTimeList", showTimeList);
        model.addAttribute("seatBooking", seatBookings);
        model.addAttribute("bookingDto", booking);
        return "/booking/edit-booking";
    }
    @PostMapping("/edit")
    public String updateBooking(@Valid @ModelAttribute BookingDto bookingDto,
                                BindingResult bindingResult,
                                Model model){
        if(bindingResult.hasErrors()){
            List<ShowTime> showTimeList = showTimeService.getAllShowTime();
            model.addAttribute("showTimeList", showTimeList);
            List<SeatBooking> seatBookings = seatBookingService.getAll();
            model.addAttribute("seatBooking", seatBookings);
            return "/booking/edit-booking";
        }
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto,booking);
        bookingService.updateBooking(booking);
        return "redirect:/booking";
    }
}
