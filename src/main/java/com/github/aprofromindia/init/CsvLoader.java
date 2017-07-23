package com.github.aprofromindia.init;

import com.github.aprofromindia.config.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiConsumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class CsvLoader implements CommandLineRunner {

    private static final String INSERT_PERSON = "insert into person (device_id, appears, disappears, age, gender)" +
            " values (?, ?, ?, ?, ?)";
    private static final String INSERT_EVENT = "insert into event (content_id, device_id, type, time_stamp)" +
            " values (?, ?, ?, ?)";
    private static final String INSERT_DEVICE = "merge into device (id) values (?)";
    private static final String INSERT_CONTENT = "merge into content (id) values (?)";
    private final JdbcTemplate template;
    @Value("classpath:/persons.csv")
    private Resource personRes;
    @Value("classpath:/events.csv")
    private Resource eventRes;
    @Value("${hibernate.jdbc.batch_size}")
    private int BATCH_SIZE;

    @Override
    public void run(String... args) throws Exception {
        updateDB(personRes, INSERT_DEVICE, (ps, str) -> {
            try {
                ps.setLong(1, Long.parseLong(str[0]));
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });

        updateDB(personRes, INSERT_PERSON, (ps, str) -> {
            try {
                ps.setLong(1, Long.parseLong(str[0]));
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.parse(str[1],
                        DateTimeFormatter.ofPattern(AppConstants.DATE_TIME_PATTERN))));
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse(str[2],
                        DateTimeFormatter.ofPattern(AppConstants.DATE_TIME_PATTERN))));
                ps.setInt(4, Integer.parseInt(str[3]));
                ps.setString(5, str[4].toUpperCase());
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });

        updateDB(eventRes, INSERT_CONTENT, (ps, str) -> {
            try {
                ps.setLong(1, Long.parseLong(str[0]));
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });
        updateDB(eventRes, INSERT_EVENT, (ps, str) -> {
            try {
                ps.setLong(1, Long.parseLong(str[0]));
                ps.setLong(2, Long.parseLong(str[1]));
                ps.setString(3, str[2].toUpperCase());
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.parse(str[3],
                        DateTimeFormatter.ofPattern(AppConstants.DATE_TIME_PATTERN))));
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }

    private void updateDB(@NotNull Resource resource, @NotNull String sql,
                          @NotNull BiConsumer<PreparedStatement, String[]> consumer) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(resource.getURI()));

            for (int i = 0; i < lines.size(); i += BATCH_SIZE) {
                List<String> batchList = lines.subList(i, i + BATCH_SIZE > lines.size() ? lines.size() : i + BATCH_SIZE);

                template.batchUpdate(sql, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        consumer.accept(ps, batchList.get(i).split(","));
                    }

                    @Override
                    public int getBatchSize() {
                        return batchList.size();
                    }
                });
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
