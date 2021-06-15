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
import com.workspace.urlshortener.exception.ApplicationException;
import com.workspace.urlshortener.model.Url;
import com.workspace.urlshortener.respository.ShortenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ShortenServiceImpl implements ShortenService {

  @Autowired
  private ShortenRepository shortenRepository;

  @Override
  public Url generateShortLink(ShortenRequest request) {
    Url persistUrl = new Url();
    persistUrl.setShortLink(encodeUrl(request.getUrl()));
    persistUrl.setOriginalLink(request.getUrl());
    persistUrl.setCreationDate(LocalDateTime.now());
    persistUrl.setExpirationDate(generateExpirationDate(persistUrl.getCreationDate()));

    Url urlToReturn = persistShortLink(persistUrl);

    if (urlToReturn != null) {
      return urlToReturn;
    } else {
      throw new ApplicationException("Error caused while persisting url");
    }
  }

  private LocalDateTime generateExpirationDate(LocalDateTime creationDate) {
    return creationDate.plusDays(2);
  }

  private String encodeUrl(String url) {
    return Hashing.murmur3_32()
        .hashString(url.concat(LocalDateTime.now().toString()), StandardCharsets.UTF_8)
        .toString();
  }

  @Override
  public Url persistShortLink(Url url) {
    return shortenRepository.save(url);
  }

  @Override
  public Url getShortLink(String shortUrl) {
    return shortenRepository.findByShortLink(shortUrl);
  }

  @Override
  public void deleteShortLink(Url url) {
    shortenRepository.delete(url);
  }
}
