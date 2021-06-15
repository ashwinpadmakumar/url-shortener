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
import com.workspace.urlshortener.respository.ShortenRepositoryDup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShortenServiceImpl implements ShortenService {

  @Autowired
  private ShortenRepositoryDup shortenRepository;

  @Override
  public Url generateAndPersistShortUrl(ShortenRequest request) {
    Url persistUrl = new Url();
    persistUrl.setShortUrl(encodeUrl(request.getUrl()));
    persistUrl.setOriginalUrl(request.getUrl());
    persistUrl.setCreationDate(LocalDateTime.now());
    persistUrl.setExpirationDate(generateExpirationDate(persistUrl.getCreationDate()));

    Url persistedUrl = shortenRepository.save(persistUrl);

    if (persistedUrl != null) {
      return persistedUrl;
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
  public Url getUrl(String shortUrl) {
    return shortenRepository.findByShortUrl(shortUrl);
  }

  @Override
  public void deleteUrl(Url url) {
    shortenRepository.delete(url);
  }
}
