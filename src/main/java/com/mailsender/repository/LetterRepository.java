package com.mailsender.repository;

import com.mailsender.domain.Letter;
import com.mailsender.domain.Status;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LetterRepository extends ElasticsearchRepository<Letter, String> {
  List<Letter> findAllByStatus(Status status);
}
