package com.mailsender.scheduler;

import com.mailsender.domain.Status;
import com.mailsender.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableAsync
public class EmailScheduler {

  private final MailSenderService mailSenderService;

  @Scheduled(fixedRate = 300000)
  @Async
  public void resendLetters(){
    mailSenderService.getAllByStatus(Status.ERROR)
        .forEach(mailSenderService::sendMail);
  }
}