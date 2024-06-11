package com.mailsender.service;

import com.mailsender.domain.Letter;
import com.mailsender.domain.Status;
import com.mailsender.repository.LetterRepository;
import com.mailsender.web.dto.LetterDto;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

  private final JavaMailSender mailSender;
  private final LetterRepository letterRepository;
  @Value("${spring.mail.username}")
  private String messageFrom;

  @Override
  public void sendMail(Letter letter) {
    SimpleMailMessage message = createSimpleMailMessage(letter);
    try {
      mailSender.send(message);
      letter.setStatus(Status.SENT);
    } catch (MailException e) {
      letter.setStatus(Status.ERROR);
      letter.setLastTriedToSend(Instant.now());
      letter.setAttemptsToSend(letter.getAttemptsToSend() + 1);
      letter.setErrorMessage(e.getClass().getName() + ": " + e.getMessage());
    }

    letterRepository.save(letter);
  }

  @Override
  public Letter createLetter(Letter letter) {
    letter.setStatus(Status.PENDING);
    return letterRepository.save(letter);
  }

  @Override
  public List<Letter> getAllByStatus(Status status) {
    return letterRepository.findAllByStatus(status);
  }

  private SimpleMailMessage createSimpleMailMessage(Letter letter) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(messageFrom);
    message.setTo(letter.getRecipient());
    message.setSubject(letter.getSubject());
    message.setText(letter.getContent());
    return message;
  }
}