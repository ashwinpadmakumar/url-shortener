/**
 * Description: Redis Repository Implementation.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.respository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.workspace.urlshortener.exception.ApplicationException;
import com.workspace.urlshortener.model.Url;

@Repository
public class RedisRepositoryImpl {

  @Autowired
  RedissonClient redissonClient;

  public Url get(String shortUrl) {
    return bindFromMap(redissonClient.getMap(shortUrl));
  }


  public Url save(Url url) {
    RMap<String, Object> map = redissonClient.getMap(url.getShortUrl());
    map.putAll(bindToMap(url));
    map.expire(2, TimeUnit.DAYS);
    if (checkIfExists(url.getShortUrl())) {
      return url;
    } else {
      throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while saving in redis");
    }
  }


  public void delete(Url url) {
    if (!redissonClient.getMap(url.getShortUrl()).delete()) {
      throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while deleting from redis");
    }
  }

  public boolean checkIfExists(String shortUrl) {
    return redissonClient.getKeys().countExists(shortUrl) > 0;
  }


  private Map<String, Object> bindToMap(Url urlObject) {
    Map<String, Object> urlMap = new HashMap<>();
    urlMap.put("shortUrl", urlObject.getShortUrl());
    urlMap.put("originalUrl", urlObject.getOriginalUrl());
    urlMap.put("creationDate", urlObject.getCreationDate());
    return urlMap;
  }

  private Url bindFromMap(Map<String, Object> urlMap) {
    var url = new Url();
    url.setShortUrl(urlMap.get("shortUrl").toString());
    url.setOriginalUrl(urlMap.get("originalUrl").toString());
    url.setCreationDate(LocalDateTime.parse(urlMap.get("creationDate").toString()));
    return url;
  }
}
