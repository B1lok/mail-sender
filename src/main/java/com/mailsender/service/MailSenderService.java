package com.mailsender.service;

import com.mailsender.domain.Letter;
import com.mailsender.domain.Status;
import java.util.List;

public interface MailSenderService {

  void sendMail(Letter letter);

  Letter createLetter(Letter letter);

  List<Letter> getAllByStatus(Status status);

}
