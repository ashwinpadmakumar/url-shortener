package com.workspace.urlshortener.respository;

import com.workspace.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classification: Trimble Confidential.
 * Description: Custom Banner for Startup
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

@Repository
public interface ShortenRepository extends JpaRepository<Url, Long> {
  Url findByShortLink(String shortLink);
}
