/**
 * Description: URL Shortener Controller.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.workspace.urlshortener.dto.ShortenRequest;
import com.workspace.urlshortener.dto.ShortenResponse;
import com.workspace.urlshortener.model.Url;
import com.workspace.urlshortener.service.ShortenService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ShortenController {

  @Autowired
  private ShortenService shortenService;


  @PostMapping("/generate")
  public ResponseEntity<ShortenResponse> generateShortUrl(@Valid @RequestBody ShortenRequest request) {
    Url url = shortenService.generateAndPersistShortUrl(request);

    ShortenResponse response = new ShortenResponse();
    response.setShortUrl(url.getShortUrl());
    response.setOriginalUrl(url.getOriginalUrl());

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/{shortUrl}")
  public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
    Url url = shortenService.getUrl(shortUrl);
    response.sendRedirect(url.getOriginalUrl());
  }
}
