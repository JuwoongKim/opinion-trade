package com.juwoong.opiniontrade.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juwoong.opiniontrade.survey.domain.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long>, SurveyRepositoryCustom {
}
