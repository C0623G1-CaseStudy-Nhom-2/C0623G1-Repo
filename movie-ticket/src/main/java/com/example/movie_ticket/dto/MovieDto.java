package com.example.movie_ticket.dto;

import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.model.ShowTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class MovieDto {
    private Long id;
    @NotBlank(message = "Không được để trống tên phim")
    private String title;
    @NotBlank(message = "Không được để trống mô tả")
    @Size(max = 10000)
    private String description;
    @FutureOrPresent(message = "Thời gian khởi chiếu không được ở quá khứ")
    private String releaseDate;
    private Category category;
    @NotBlank(message = "Không được để trống tên đạo diễn")
    private String director;
    @NotBlank(message = "Không được để trống link ảnh")
    private String avatar;
    @NotBlank(message = "Không được để trống link ảnh")
    private String banner;
    private Set<ShowTime> showTimes;

    public MovieDto() {
    }

    public MovieDto(Long id, String title, String description, String releaseDate, Category category, String director, String avatar, String banner, Set<ShowTime> showTimes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
        this.director = director;
        this.avatar = avatar;
        this.banner = banner;
        this.showTimes = showTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Set<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(Set<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
