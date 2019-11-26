package com.abc.rest.reactive.controller;

import com.abc.rest.reactive.cache.EventCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class CacheService {
    @Autowired
    private EventCache eventCache;


    @GetMapping("/{userId}")
    public Mono<String> getEventId(@PathVariable int userId){
        return eventCache.getEventId(userId);
    }
}
