package com.fanyank.streams.config;

import com.fanyank.model.AuthorizationSummary;
import com.fanyank.repository.AuthorizationSummaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(PollerProperties.class)
public class PollerJobConfig {
    private final PollerProperties properties;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final AuthorizationSummaryRepository authorizationSummaryRepository;

    private DataSource dataSource;

    @Bean
    @StepScope
    public JdbcPagingItemReader<AuthorizationSummary> authorizationSummaryReader() {
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("merchant_id", Order.ASCENDING);

        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("is_closed", 0);

        log.info("poller start retrieving data from DB");

        return new JdbcPagingItemReaderBuilder<AuthorizationSummary>()
                .dataSource(dataSource)
                .name("authorizationSummaryReader")
                .selectClause("SELECT id, merchant_id, start_read_time, end_read_time, purchase_amount, purchase_count, is_closed")
                .fromClause("FROM ent_authorization_summary")
                .whereClause("WHERE is_closed=:is_closed")
                .parameterValues(parameterValues)
                .sortKeys(sortKeys)
                .fetchSize(properties.getChunkSize())
                .pageSize(properties.getPageSize())
                .build();

    }
}
