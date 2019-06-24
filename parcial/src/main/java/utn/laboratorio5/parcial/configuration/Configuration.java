package utn.laboratorio5.parcial.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration {

    @Value("${CORE_POOL_SIZE}")
    Integer CORE_POOL_SIZE ;
    Integer MAX_POOL_SIZE = 10;
    Integer QUEUE_SIZE = 100;


    @Bean("threadPoolTaskExecutor")
    public Executor executor ()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_SIZE);

        return executor;
    }
}
