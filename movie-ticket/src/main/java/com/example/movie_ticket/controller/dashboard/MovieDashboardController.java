package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class MovieDashboardController {
    @Autowired
    private IMovieService movieService;

    @GetMapping("/movies")
    public ModelAndView showMovieLIst(@PageableDefault(value = 6) Pageable pageable, Model model) {
        Page<Movie> movies = movieService.findAllMovie(pageable);
        model.addAttribute("newMovie", new Movie());
        return new ModelAndView("/dashboard/movies", "movies", movies);
    }
    @GetMapping("/add")
    public String addNewMovie(@ModelAttribute Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/dashboard/movies";
    }
}
