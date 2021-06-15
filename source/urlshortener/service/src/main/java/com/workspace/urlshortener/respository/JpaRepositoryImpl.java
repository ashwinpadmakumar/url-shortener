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
public interface JpaRepositoryImpl extends ShortenRepository, JpaRepository<Url, Long> {
}
