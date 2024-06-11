package com.mailsender.domain;

import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "letters")
public class Letter {
  @Id
  private String letterId;

  @Field(type = FieldType.Text)
  private String subject;

  @Field(type = FieldType.Text)
  private String content;

  @Field(type = FieldType.Keyword)
  private Status status;

  @Field(type = FieldType.Date)
  private Instant lastTriedToSend;

  @Field(type = FieldType.Integer)
  private Integer attemptsToSend = 0;

  @Field(type = FieldType.Text)
  private String errorMessage;

  @Field(type = FieldType.Keyword)
  private String recipient;
}