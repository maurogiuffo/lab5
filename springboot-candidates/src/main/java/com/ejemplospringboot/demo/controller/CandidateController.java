package com.ejemplospringboot.demo.controller;

import com.ejemplospringboot.demo.model.Candidate;
import com.ejemplospringboot.demo.model.Vote;
import com.ejemplospringboot.demo.repository.CandidateRepository;
import com.ejemplospringboot.demo.repository.VoteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.ws.http.HTTPException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;


    // Public

    // Candidates

    @GetMapping("")
    public List<Candidate> getCandidates()
    {
        return candidateRepository.findAll();
    }

    @GetMapping(value =  "/{id}")
    public Candidate getCandidate(@PathVariable final Integer id)
    {
        return candidateRepository.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Not Found") );
    }

    @PostMapping("")
    public void CreateCandidate(@RequestBody final Candidate candidate)
    {
        candidateRepository.save(candidate);
    }



    @GetMapping("fill")
    public void fill()
    {
        Candidate c = new Candidate();
        c.setName("Candidato1");
        candidateRepository.save(c);

        c = new Candidate();
        c.setName("Candidato2");
        candidateRepository.save(c);
    }


    // Votes

    @GetMapping("/votes")
    public List<Vote> getVotes()
    {
        List<Vote> votes = voteRepository.findAll();

        votes.forEach(p-> {
            System.out.println(p.getCandidate().getName());
        });

        return votes;
    }

    @PostMapping("/{id}/vote")
    public void VoteCandidate(@PathVariable final Integer id)
    {
        Candidate c = candidateRepository.findById(id).orElseThrow( ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Candidate not found."));
        Vote v = new Vote();
        v.setCandidate(c);

        voteRepository.save(v);
    }

    @Scheduled(fixedRate = 2000)
    public void deleteVotes()
    {
        Integer seconds = 10;

        LocalTime limitTime = LocalTime.now().minus(Duration.ofSeconds(seconds));

        List<Vote> votes = voteRepository.findAll()
                .stream().filter(p -> p.getTime().compareTo(limitTime) == -1 )
                .collect(Collectors.toList());

        if(votes.size()>0) {
            System.out.println(String.format(  "(%s) %s votes older than %s will be deleted :" ,LocalTime.now(),votes.size(), limitTime));

            votes.forEach(p ->
            {
                System.out.println(p.getTime());
                voteRepository.delete(p);
            });
        }
    }

}
