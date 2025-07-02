package com.example.demo.data.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Bu sınıf, Watchlist tablosu için birleşik bir anahtar görevi görür.
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "movie_id")
    private Long movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistId that = (WatchlistId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}