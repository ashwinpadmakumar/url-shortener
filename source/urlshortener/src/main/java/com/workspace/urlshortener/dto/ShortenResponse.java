package com.workspace.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classification: Trimble Confidential.
 * Description: Custom Banner for Startup
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortenResponse {
  private String shortLink;
  private String originalLink;
  private LocalDateTime expirationDate;
}
