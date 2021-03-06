package com.spring.helpers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestHelper<T> {


  public static String endpoint = "http://5c18cb40cfe31000133b175a.mockapi.io/api";

  private static HttpHeaders headers = null;
  static {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    headers.setAccessControlAllowOrigin("*");
  }

  public static <T> ResponseEntity<?> post(String url, T data, Class<T> clazz) {
    log.info("Post event is starting....");
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<T> entity = new HttpEntity<T>(data, headers);
    ResponseEntity<T> response = null;
    try {
      log.info("Calling to endpoint {}", url);
      response = restTemplate.exchange(url, HttpMethod.POST, entity, clazz);
      log.debug("Response result: {}", JsonHelper.object2String(response, "RestHelper"));
    } catch (Exception ex) {
      log.error("Post failed: " + ex.getMessage());
    }
    return response;
  }

  public static final String getEndpoint(String type) {
    if ("users".equalsIgnoreCase(type)) {
      return endpoint.concat("/users");
    }
    return endpoint;
  }
}
