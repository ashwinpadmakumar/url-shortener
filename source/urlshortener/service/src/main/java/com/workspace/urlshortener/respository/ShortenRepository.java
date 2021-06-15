/**
 * Description: Repository interface.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.respository;

import com.workspace.urlshortener.model.Url;

public interface ShortenRepository {
  Url findByShortUrl(String shortLink);

  Url save(Url url);

  void delete(Url url);
}
