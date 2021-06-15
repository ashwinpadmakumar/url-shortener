/**
 * Description: Repository interface.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */


package com.workspace.urlshortener.respository;

import com.workspace.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public abstract class JpaRepositoryImpl implements ShortenRepository, JpaRepository<Url, Long> {

  abstract Url findByShortUrl(String shortUrl);

  @Override
  public Url findUrl(String shortUrl) {
    return findByShortUrl(shortUrl);
  }

  @Override
  public Url saveUrl(Url url) {
    return this.save(url);
  }

  @Override
  public void deleteUrl(Url url) {
    this.delete(url);
  }
}
