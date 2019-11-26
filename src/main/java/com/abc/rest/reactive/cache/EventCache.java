package com.abc.rest.reactive.cache;

import com.abc.rest.reactive.properties.CacheProperties;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class EventCache {

    private Map<Integer, String> eventCache = new HashMap<>();
    @Autowired
    private CacheProperties cacheProperties;

    @PostConstruct
    public void loadCache() {
        Flux.range(1, cacheProperties.getNumberOfElements())
            .subscribe(i -> eventCache.put(i, i + ""));
        log.info("Cache loaded with {} elements", cacheProperties.getNumberOfElements());
    }

    public Mono<String> getEventId(int userId){
        return Mono.justOrEmpty(eventCache.get(userId));
    }
}
