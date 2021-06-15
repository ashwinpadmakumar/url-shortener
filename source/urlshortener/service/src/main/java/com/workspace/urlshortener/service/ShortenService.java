/**
 * Description: Service interface.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.service;

import com.workspace.urlshortener.dto.ShortenRequest;
import com.workspace.urlshortener.model.Url;

public interface ShortenService {
  Url generateAndPersistShortUrl(ShortenRequest request);

  Url getUrl(String shortUrl);
}
