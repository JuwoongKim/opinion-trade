package com.juwoong.opiniontrade.survey.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.juwoong.opiniontrade.survey.domain.QSurvey;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SurveyRepositoryCustomImpl implements SurveyRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	private QSurvey qSurvey = QSurvey.survey;

	@Override
	public Slice<Survey> getSurveysByCursor(Long userId, Long cursorId, LocalDateTime time, Pageable pageable) {
		List<Survey> surveys = queryFactory.selectFrom(qSurvey)
			.where(
				qSurvey.creator.creatorId.eq(userId)
					.and(cursor(cursorId, time))
			)
			.orderBy(qSurvey.updatedAt.desc(), qSurvey.id.asc())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		boolean hasNext = surveys.size() == pageable.getPageSize() + 1;
		if (hasNext)
			surveys.remove(pageable.getPageSize());

		return new SliceImpl<Survey>(surveys, pageable, hasNext);
	}

	private BooleanExpression cursor(Long cursorId, LocalDateTime time) {
		return qSurvey.updatedAt.lt(time)
			.or(qSurvey.updatedAt.eq(time).and(qSurvey.id.gt(cursorId)));
	}
}
