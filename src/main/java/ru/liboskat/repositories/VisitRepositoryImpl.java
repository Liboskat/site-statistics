package ru.liboskat.repositories;

import ru.liboskat.dto.IntervalStatisticsDto;
import ru.liboskat.dto.DailyStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class VisitRepositoryImpl implements VisitRepository {
    private static final String INSERT_VISIT_QUERY =
            "INSERT INTO visits(user_id, page_id, timestamp) VALUES (:user_id, :page_id, :timestamp);";

    private static final String SELECT_DAILY_STATISTICS_QUERY =
            "SELECT count(user_id) AS total_visits, count(DISTINCT user_id) AS unique_users " +
            "FROM visits " +
            "WHERE timestamp::DATE = now()::DATE AND page_id = :page_id;";

    private static final String SELECT_INTERVAL_STATISTICS_QUERY =
            "SELECT " +
                    "  sum(statistics.total_visits) AS total_visits, " +
                    "  sum(statistics.unique_users) AS unique_users, " +
                    "  sum(CASE WHEN statistics.visited_pages > 10 THEN 1 ELSE 0 END) AS regular_users " +
                    "FROM (" +
                    "       SELECT count(user_id)          AS total_visits, " +
                    "              count(DISTINCT user_id) AS unique_users, " +
                    "              count(DISTINCT page_id) AS visited_pages " +
                    "       FROM visits " +
                    "       WHERE timestamp >= :from AND timestamp <= :to " +
                    "       GROUP BY user_id " +
                    "     ) AS statistics;";

    private NamedParameterJdbcTemplate template;

    @Autowired
    public VisitRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public DailyStatisticsDto insertVisit(Long userId, Long pageId, Timestamp timestamp) {
        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("user_id", userId);
        queryParams.put("page_id", pageId);
        queryParams.put("timestamp", timestamp);

        template.update(INSERT_VISIT_QUERY, queryParams);

        return template.queryForObject(SELECT_DAILY_STATISTICS_QUERY, queryParams, DailyStatisticsDto.DEF_ROW_MAPPER);
    }

    @Override
    public IntervalStatisticsDto getStatistics(Timestamp from, Timestamp to) {

        Map<String, Timestamp> queryParams = new HashMap<>();

        queryParams.put("from", from);
        queryParams.put("to", to);

        return template.queryForObject(SELECT_INTERVAL_STATISTICS_QUERY, queryParams, IntervalStatisticsDto.DEF_ROW_MAPPER);
    }
}
