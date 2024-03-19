package com.juwoong.opiniontrade.survey.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.juwoong.opiniontrade.survey.domain.Survey;

public interface SurveyRepositoryCustom {

	Slice<Survey> getSurveysByCursor(Long userId, Long cursorId, LocalDateTime time, Pageable pageable);
}
