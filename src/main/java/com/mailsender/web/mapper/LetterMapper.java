package com.mailsender.web.mapper;

import com.mailsender.domain.Letter;
import com.mailsender.web.dto.LetterDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LetterMapper {
  Letter toEntity(LetterDto letterDto);
}
