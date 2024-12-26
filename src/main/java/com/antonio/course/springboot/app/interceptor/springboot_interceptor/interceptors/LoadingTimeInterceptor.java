package com.antonio.course.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HandlerMethod controller = ((HandlerMethod) handler);
    logger.info("LoadingTimeInterceptor: preHandle() enter... " + controller.getMethod().getName());

    long start = System.currentTimeMillis();
    request.setAttribute("start", start);

    Random random = new Random();
    int delay = random.nextInt(500);
    Thread.sleep(delay);

    Map<String, String> json = new HashMap<>();
    json.put("error", "Unauthorized");
    json.put("message", "You don't have permission to access this resource");

    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(json);
    response.setContentType("application/json");
    response.setStatus(401);
    response.getWriter().write(jsonString);

    return false;
    // return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {

    long end = System.currentTimeMillis();
    long start = (long) request.getAttribute("start");

    long result = end - start;
    logger.info("time spent " + result + " ms");

    logger.info("LoadingTimeInterceptor: postHandle() exit... " + ((HandlerMethod) handler).getMethod().getName());
  }

}