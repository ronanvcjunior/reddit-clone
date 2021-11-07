package com.ronan.redditclone.repository;

import java.util.List;
import java.util.Optional;

import com.ronan.redditclone.domain.Subreddit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    
    Optional<Subreddit> findByName(String name);

    @Query("SELECT subreddit FROM Subreddit subreddit WHERE lower(subreddit.name) LIKE :letter% order by subreddit.name asc")
    List<Subreddit> findAllByFirstLetter(char letter);

    @Query("SELECT subreddit FROM Subreddit subreddit WHERE subreddit.name LIKE ?1% Or subreddit.name LIKE ?2% Or subreddit.name LIKE ?3% Or subreddit.name LIKE ?4% Or subreddit.name LIKE ?5% Or subreddit.name LIKE ?6% Or subreddit.name LIKE ?7% Or subreddit.name LIKE ?8% Or subreddit.name LIKE ?9% Or subreddit.name LIKE ?10% order by subreddit.name asc")
    List<Subreddit> findAllByFirstLetterIsSpecialCharacter(Integer a0, Integer a1, Integer a2, Integer a3, Integer a4, Integer a5, Integer a6, Integer a7, Integer a8, Integer a9);
}
