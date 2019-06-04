package com.ejemplospringboot.demo.repository;


import com.ejemplospringboot.demo.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {
}
