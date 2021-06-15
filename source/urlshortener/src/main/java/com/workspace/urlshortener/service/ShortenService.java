package com.workspace.urlshortener.service;

import com.workspace.urlshortener.dto.ShortenRequest;
import com.workspace.urlshortener.model.Url;
import org.springframework.stereotype.Service;

/**
 * Classification: Trimble Confidential.
 * Description: Custom Banner for Startup
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */
@Service
public interface ShortenService {
  Url generateShortLink(ShortenRequest request);
  Url persistShortLink(Url url);
  Url getShortLink(String url);
  void deleteShortLink(Url url);
}
