package com.ejemplospringboot.demo.repository;

import com.ejemplospringboot.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Integer> {

}
