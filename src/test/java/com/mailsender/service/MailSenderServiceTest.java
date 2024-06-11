package com.mailsender.service;

import com.mailsender.domain.Letter;
import com.mailsender.domain.Status;
import com.mailsender.listener.LetterListener;
import com.mailsender.scheduler.EmailScheduler;
import com.mailsender.utils.LetterUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static com.mailsender.utils.LetterUtils.getLetter;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class MailSenderServiceTest {
  @Autowired
  private MailSenderService mailSenderService;

  @MockBean
  private JavaMailSender javaMailSender;

  @MockBean
  private LetterListener listener;

  @MockBean
  private EmailScheduler emailScheduler;

  @Test
  public void testSendMailSuccess() {
    Letter letter = getLetter();

    mailSenderService.sendMail(letter);

    assertEquals(Status.SENT, letter.getStatus());
    assertNull(letter.getErrorMessage());
  }

  @Test
  public void testSendMailFailure() {
    Letter letter = getLetter();

    doThrow(MailSendException.class).when(javaMailSender).send((SimpleMailMessage) any());

    mailSenderService.sendMail(letter);
    assertEquals(Status.ERROR, letter.getStatus());
    assertNotNull(letter.getErrorMessage());
  }
}