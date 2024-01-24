package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ScrapSurvey {
	@Column(name = "scrap_survey_id", nullable = false)
	private String scrapSurveyId;

	@Column(name = "scrip_survey_title", nullable = false)
	private String scrapSurveyTitle;

	@Column(name = "scrip_survey_description", nullable = false)
	private String scripSurveyDescription;

	protected ScrapSurvey() {
	}

	public ScrapSurvey(String scrapSurveyId, String scrapSurveyTitle, String scripSurveyDescription) {
		this.scrapSurveyId = scrapSurveyId;
		this.scrapSurveyTitle = scrapSurveyTitle;
		this.scripSurveyDescription = scripSurveyDescription;
	}
}
