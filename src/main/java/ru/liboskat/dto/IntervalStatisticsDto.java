package ru.liboskat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class IntervalStatisticsDto {
    private Long totalVisits;
    private Long uniqueUsers;
    private Long regularUsers;

    public static final RowMapper<IntervalStatisticsDto> DEF_ROW_MAPPER =
            (resultSet, i) -> IntervalStatisticsDto.builder()
                    .totalVisits(resultSet.getLong("total_visits"))
                    .uniqueUsers(resultSet.getLong("unique_users"))
                    .regularUsers(resultSet.getLong("regular_users"))
                    .build();
}
