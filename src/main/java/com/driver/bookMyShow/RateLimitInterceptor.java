package com.driver.bookMyShow;

import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    public RateLimitInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String clientIp = request.getRemoteAddr(); // Rate limit by IP
        Bucket bucket = rateLimiterService.resolveBucket(clientIp);

        if (bucket.tryConsume(1)) {
            System.out.println("Request allowed for client: " + clientIp);
            return true; // Allow request
        } else {
            System.out.println("Rate limit exceeded for client: " + clientIp);
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Rate limit exceeded. Try again later.");
            return false;
        }
    }
}
