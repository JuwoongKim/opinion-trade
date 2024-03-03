package com.juwoong.opiniontrade.survey.fixture;

import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyInfo;

public enum SurveyFixture {
	SURVEY(1L, "title", "descriptions");

	private Long creatorId;
	private String title;
	private String description;

	SurveyFixture(Long creatorId, String title, String description) {
		this.creatorId = creatorId;
		this.title = title;
		this.description = description;
	}

	public Survey getInstance() {
		Creator creator = Creator.init(creatorId);
		SurveyInfo surveyInfo = SurveyInfo.init(title, description);
		return Survey.init(creator, surveyInfo);
	}
}
