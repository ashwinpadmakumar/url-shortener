/**
 * Description: Shorten Service Implementation.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.google.common.hash.Hashing;
import com.workspace.urlshortener.dto.ShortenRequest;
import com.workspace.urlshortener.model.Url;
import com.workspace.urlshortener.respository.JpaRepositoryImpl;
import com.workspace.urlshortener.respository.RedisRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ShortenServiceImpl implements ShortenService {

  @Autowired
  private JpaRepositoryImpl jpaRepository;

  @Autowired
  private RedisRepositoryImpl redisRepository;

  @Override
  public Url generateAndPersistShortUrl(ShortenRequest request) {
    Url urlObject = new Url();
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
  public Url getUrl(String shortUrl) {
    if (redisRepository.checkIfExists(shortUrl)) {
      log.debug("Fetching from redis");
      return redisRepository.get(shortUrl);
    } else {
      log.debug("Not found in redis. Fetching from JPA");
      Url jpaResponse = jpaRepository.findByShortUrl(shortUrl);
      return redisRepository.save(jpaResponse);
    }
  }
}
