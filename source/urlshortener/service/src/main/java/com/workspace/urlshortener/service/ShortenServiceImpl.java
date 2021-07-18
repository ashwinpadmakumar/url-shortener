/**
 * Description: Custom Banner for Startup.
 *
 * @author: Ashwin Padmakumar
 * @since: 12/07/21
 * @version: 0.1
 */

package com.workspace.urlshortener.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.workspace.urlshortener.dto.ShortenRequest;
import com.workspace.urlshortener.model.Url;
import com.workspace.urlshortener.respository.JpaRepositoryImpl;
import com.workspace.urlshortener.respository.RedisRepositoryImpl;


@Service
@Slf4j
@AllArgsConstructor
public class ShortenServiceImpl implements ShortenService {

  private final JpaRepositoryImpl jpaRepository;
  private final RedisRepositoryImpl redisRepository;

  @Override
  public Url generateShortUrl(ShortenRequest request) {
    var urlObject = new Url();
    urlObject.setShortUrl(encodeUrl(request.getUrl()));
    urlObject.setOriginalUrl(request.getUrl());
    urlObject.setCreationDate(LocalDateTime.now());

    return jpaRepository.save(urlObject);
  }

  private String encodeUrl(String url) {
    return Hashing
        .murmur3_32()
        .hashString(url.concat(LocalDateTime.now().toString()), StandardCharsets.UTF_8)
        .toString();
  }

  @Override
  public Url getShortUrl(String shortUrl) {
    if (redisRepository.checkIfExists(shortUrl)) {
      log.debug("Fetching from redis");
      return redisRepository.get(shortUrl);
    } else {
      log.debug("Not found in redis. Fetching from JPA");
      var jpaResponse = jpaRepository.findByShortUrl(shortUrl);
      return redisRepository.save(jpaResponse);
    }
  }
}
