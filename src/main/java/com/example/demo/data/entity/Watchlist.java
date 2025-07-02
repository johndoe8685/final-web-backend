package com.example.demo.data.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "watchlist")
@Getter
@Setter
// Birleşik anahtar sınıfını burada belirtiyoruz.
@IdClass(WatchlistId.class)
public class Watchlist {

    @Id
    private Long userId;

    @Id
    private Long movieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}