package com.mailsender.utils;

import com.mailsender.domain.Letter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LetterUtils {

  public static Letter getLetter() {
    Letter letter = new Letter();
    letter.setRecipient("recipient@gmail.com");
    letter.setSubject("Test subject");
    letter.setContent("Test content");
    return letter;
  }
}
