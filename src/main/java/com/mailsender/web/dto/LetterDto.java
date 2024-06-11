package com.mailsender.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LetterDto {
  @NotBlank(message = "Subject cannot be blank")
  @Size(max = 100, message = "Subject cannot exceed 100 characters")
  private String subject;

  @NotBlank(message = "Content cannot be blank")
  private String content;

  @NotBlank(message = "Recipient cannot be blank")
  @Email(message = "Recipient must be a valid email address")
  private String recipient;
}