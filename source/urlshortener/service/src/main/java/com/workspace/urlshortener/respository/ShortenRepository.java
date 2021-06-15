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
  Url findUrl(String shortLink);

  Url saveUrl(Url url);

  void deleteUrl(Url url);
}
