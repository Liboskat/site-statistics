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
public class DailyStatisticsDto {
    private Integer totalVisits;
    private Integer uniqueUsers;

    public static final RowMapper<DailyStatisticsDto> DEF_ROW_MAPPER =
            (resultSet, i) -> DailyStatisticsDto.builder()
                    .totalVisits(resultSet.getInt("total_visits"))
                    .uniqueUsers(resultSet.getInt("unique_users"))
                    .build();
}
