/**
 * Description: URL Shortener Controller.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;

import com.workspace.urlshortener.dto.ShortenRequest;
import com.workspace.urlshortener.dto.ShortenResponse;
import com.workspace.urlshortener.exception.ApplicationException;
import com.workspace.urlshortener.model.Url;
import com.workspace.urlshortener.service.ShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shorten-service")
public class ShortenController {

  @Autowired
  private ShortenService shortenService;


  @PostMapping("/generate")
  public ResponseEntity<ShortenResponse> generateShortLink(@RequestBody ShortenRequest request) {
    Url url = shortenService.generateShortLink(request);

    ShortenResponse response = new ShortenResponse();
    response.setShortLink(url.getShortLink());
    response.setOriginalLink(url.getOriginalLink());

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/{shortLink}")
  public void redirect(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
    Url url = shortenService.getShortLink(shortLink);
    if (url.getExpirationDate().isBefore(LocalDateTime.now())) {
      shortenService.deleteShortLink(url);
      throw new ApplicationException("URL Expired. Please generate a new one");
    } else {
      response.sendRedirect(url.getOriginalLink());
    }
  }
}
