package com.juwoong.opiniontrade.survey.application;

import static com.juwoong.opiniontrade.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.juwoong.opiniontrade.global.exception.OpinionTradeException;
import com.juwoong.opiniontrade.survey.api.request.ResultRequest;
import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.Respondent;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyResultService {
	private final SurveyRepository surveyRepository;

	public void createSurveyResult(Long surveyId, Long respondentId, List<ResultRequest.Answer> answerInfo) {
		Survey survey = findSurveyById(surveyId);

		Respondent respondent = Respondent.init(respondentId);
		List<Answer> answers = answerInfo.stream()
			.map(answer -> Answer.init(answer.questionId(), answer.content()))
			.toList();

		SurveyResult surveyResult = SurveyResult.init(respondent, answers);
		survey.receiveSurveyResult(surveyResult);
	}

	private Survey findSurveyById(Long id) {
		return surveyRepository.findById(id)
			.orElseThrow(() -> new OpinionTradeException(NOT_FOUND_SURVEY));
	}
}
