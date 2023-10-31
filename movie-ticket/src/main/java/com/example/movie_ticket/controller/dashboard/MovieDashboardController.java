package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.dto.MovieDto;
import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.service.IMovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class MovieDashboardController {
    @Autowired
    private IMovieService movieService;

    @GetMapping("/movies")
    public ModelAndView showMovieLIst(@PageableDefault(value = 6) Pageable pageable, Model model) {
        Page<Movie> movies = movieService.findAllMovie(pageable);
        model.addAttribute("newMovie", new MovieDto());
        return new ModelAndView("/dashboard/movies", "movies", movies);
    }
    @GetMapping("/add")
    public String addNewMovie(@Valid BindingResult bindingResult, @ModelAttribute MovieDto movieDto) {
        if (bindingResult.hasErrors()) {
            return "/dashboard/movies";
        } else {
            Movie movie = new Movie();
            BeanUtils.copyProperties(movieDto, movie);
            movieService.saveMovie(movie);
            return "redirect:/dashboard/movies";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/dashboard/movies";
    }
    @GetMapping("/search")
    public ModelAndView searchMovie(@RequestParam String keyword, @PageableDefault(value = 10) Pageable pageable) {
        Page<Movie> movies = movieService.findMovieByName(keyword, pageable);
        if (movies.isEmpty()) {
            return new ModelAndView("/dashboard/movies", "empty", "Không có kết quả");
        } else {
            return new ModelAndView("/dashboard/movies", "movies", movies);
        }
    }
}
