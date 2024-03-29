/**
 * Description: JPA Repository Implementation.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */


package com.workspace.urlshortener.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workspace.urlshortener.model.Url;

@Repository
public interface JpaRepositoryImpl extends JpaRepository<Url, Long> {
  Url findByShortUrl(String shortUrl);
}
