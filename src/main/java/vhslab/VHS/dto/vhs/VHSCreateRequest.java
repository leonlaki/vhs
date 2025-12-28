package vhslab.VHS.dto.vhs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vhslab.VHS.utility.Genre;

public class VHSCreateRequest {

    @NotBlank
    private String title;
    @NotNull
    private Genre genre;
    @Positive
    private int releaseYear;
    @NotBlank
    private String director;
    @Positive
    private int durationMinutes;

    public VHSCreateRequest() {}

    public VHSCreateRequest(String title, Genre genre, int releaseYear, String director, int durationMinutes) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
