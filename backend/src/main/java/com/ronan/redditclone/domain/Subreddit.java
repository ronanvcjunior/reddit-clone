package com.ronan.redditclone.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subreddit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Community name is required")
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9][A-z0-9À-ü_]+$")
    @Length(min = 3, max = 21, message = "O campo NAME deve ter entre 5 e 25 caracteres")
    private String name;

    @NotBlank(message = "Description is required")
    @Lob
    private String description;

    @OneToMany(fetch = LAZY)
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    private User user;

    private Integer numberOfPosts=0;

    public void deletePost(Post post) {
        posts.remove(post);
    }
}