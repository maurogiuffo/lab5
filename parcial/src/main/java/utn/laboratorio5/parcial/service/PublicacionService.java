package utn.laboratorio5.parcial.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class PublicacionService {

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> Count(String id)
    {
        System.out.println(String.format( "Count(%s) operation started at %s",id ,LocalDateTime.now()));

        Integer qty=0;

        for (Integer i =0 ; i<1000000; i++) {
            if(toString().contains("0"))
                qty += i.toString().split("0").length;
        }

        System.out.println(String.format( "Count(%s) operation finished at %s",id ,LocalDateTime.now()));

        return CompletableFuture.completedFuture(qty);
    }


    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> Sum()
    {

        System.out.println("Sum operation started at " + LocalDateTime.now());

        Integer sum = 0;
        for (Integer i =0 ; i<1000000; i++) {
            sum +=i;
        }

        System.out.println("Sum operation finished at " + LocalDateTime.now());

        return CompletableFuture.completedFuture(sum);
    }
}
