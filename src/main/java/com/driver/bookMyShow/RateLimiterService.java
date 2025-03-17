package com.driver.bookMyShow;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    // Get the bucket for a user/IP
    public Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, this::createNewBucket);
    }

    // Create a new bucket for rate limiting
    private Bucket createNewBucket(String key) {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1))); // 10 requests per minute
        return Bucket.builder().addLimit(limit).build();
    }
}
