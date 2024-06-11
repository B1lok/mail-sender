package com.mailsender.listener;

import com.mailsender.domain.Letter;
import com.mailsender.service.MailSenderService;
import com.mailsender.web.dto.LetterDto;
import com.mailsender.web.mapper.LetterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LetterListener {

  private final MailSenderService mailSenderService;
  private final LetterMapper letterMapper;

  @KafkaListener(topics = "${kafka.topic.letterReceiver}")
  public void letterReceiver(LetterDto letterDto){
    Letter newLetter = mailSenderService.createLetter(letterMapper.toEntity(letterDto));
    mailSenderService.sendMail(newLetter);
  }
}
