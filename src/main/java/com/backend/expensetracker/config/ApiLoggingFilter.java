//package com.backend.expensetracker.config;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.time.Instant;
//
//@Component
//public class ApiLoggingFilter implements Filter {
//
//    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(res);
//
//        long startTime = System.currentTimeMillis();
//        chain.doFilter(request, responseWrapper);
//        long duration = System.currentTimeMillis() - startTime;
//
//        // Read response body
//        String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
//        responseWrapper.copyBodyToResponse(); // Important: Ensure response is correctly sent back to client
//
//        logger.info("{\"@timestamp\": \"{}\", \"method\": \"{}\", \"endpoint\": \"{}\", \"status_code\": {}, \"response_time_ms\": {}, \"service\": \"expense-tracker\", \"response_body\": \"{}\"}",
//                Instant.now(), req.getMethod(), req.getRequestURI(), res.getStatus(), duration, responseBody.replaceAll("\"", "'")); // Escape double quotes
//
//    }
//}
