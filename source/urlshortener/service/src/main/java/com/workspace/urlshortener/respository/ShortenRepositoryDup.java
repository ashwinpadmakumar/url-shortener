/**
 * Description: Duplicate Repository Interface.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.respository;

import com.workspace.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenRepositoryDup extends JpaRepository<Url, String> {
  Url findByShortUrl(String shortUrl);
}
