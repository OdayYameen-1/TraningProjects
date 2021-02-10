package Configration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.convert.AerospikeCustomConversions;
import org.springframework.data.aerospike.convert.AerospikeTypeAliasAccessor;
import org.springframework.data.aerospike.convert.MappingAerospikeConverter;
import org.springframework.data.aerospike.core.AerospikeExceptionTranslator;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.core.DefaultAerospikeExceptionTranslator;
import org.springframework.data.aerospike.mapping.AerospikeMappingContext;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.aerospike.query.QueryEngine;
import org.springframework.data.aerospike.query.StatementBuilder;
import org.springframework.data.aerospike.query.cache.*;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.data.annotation.Persistent;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.oday.movies.MovieRepository;
import com.oday.soapExampleProducer.producingwebservice.CountryRepository;

import java.util.Collections;


@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableAerospikeRepositories(basePackageClasses = {
		MovieRepository.class,
		CountryRepository.class,
		
		
		
})
public class AerospikeConfiguration {

    @Bean
    public AerospikeTemplate aerospikeTemplate(AerospikeClient aerospikeClient,
                                               MappingAerospikeConverter mappingAerospikeConverter,
                                               AerospikeMappingContext aerospikeMappingContext,
                                               AerospikeExceptionTranslator aerospikeExceptionTranslator,
                                               QueryEngine queryEngine, IndexRefresher indexRefresher) {

        return new AerospikeTemplate(aerospikeClient,
                "test",
                mappingAerospikeConverter,
                aerospikeMappingContext,
                aerospikeExceptionTranslator, queryEngine, indexRefresher);
    }

    @Bean
    public AerospikeClient aerospikeClient() {
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.failIfNotConnected = true;

        return new AerospikeClient(clientPolicy, "172.28.128.3", 3000);
    }


    @Bean(name = "aerospikeStatementBuilder")
    @ConditionalOnMissingBean(name = "aerospikeStatementBuilder")
    public StatementBuilder aerospikeStatementBuilder(IndexesCache indexesCache) {
        return new StatementBuilder(indexesCache);
    }

    @Bean(name = "aerospikeIndexCache")
    @ConditionalOnMissingBean(name = "aerospikeIndexCache")
    public IndexesCacheHolder aerospikeIndexCache() {
        return new IndexesCacheHolder();
    }

    @Bean(name = "mappingAerospikeConverter")
    @ConditionalOnMissingBean(name = "mappingAerospikeConverter")
    public MappingAerospikeConverter mappingAerospikeConverter(AerospikeMappingContext aerospikeMappingContext,
                                                               AerospikeTypeAliasAccessor aerospikeTypeAliasAccessor,
                                                               AerospikeCustomConversions aerospikeCustomConversions) {
        return new MappingAerospikeConverter(aerospikeMappingContext, aerospikeCustomConversions, aerospikeTypeAliasAccessor);
    }

    @Bean(name = "aerospikeTypeAliasAccessor")
    @ConditionalOnMissingBean(name = "aerospikeTypeAliasAccessor")
    public AerospikeTypeAliasAccessor aerospikeTypeAliasAccessor() {
        return new AerospikeTypeAliasAccessor(null);
    }

    @Bean(name = "aerospikeCustomConversions")
    @ConditionalOnMissingBean(name = "aerospikeCustomConversions")
    public AerospikeCustomConversions aerospikeCustomConversions() {
        return new AerospikeCustomConversions(Collections.emptyList());
    }

    @Bean(name = "aerospikeMappingContext")
    @ConditionalOnMissingBean(name = "aerospikeMappingContext")
    public AerospikeMappingContext aerospikeMappingContext(ApplicationContext applicationContext,
                                                           AerospikeCustomConversions aerospikeCustomConversions
    ) throws Exception {
        AerospikeMappingContext context = new AerospikeMappingContext();
        context.setInitialEntitySet(new EntityScanner(applicationContext).scan(Document.class, Persistent.class));
        context.setSimpleTypeHolder(aerospikeCustomConversions.getSimpleTypeHolder());

        context.setDefaultNameSpace("test");
        context.setCreateIndexesOnStartup(false);
        return context;
    }

    @Bean(name = "aerospikeExceptionTranslator")
    @ConditionalOnMissingBean(name = "aerospikeExceptionTranslator")
    public AerospikeExceptionTranslator aerospikeExceptionTranslator() {
        return new DefaultAerospikeExceptionTranslator();
    }

    @Bean(name = "aerospikeQueryEngine")
    @ConditionalOnMissingBean(name = "aerospikeQueryEngine")
    public QueryEngine aerospikeQueryEngine(AerospikeClient aerospikeClient,
                                            StatementBuilder statementBuilder) {
        QueryEngine queryEngine = new QueryEngine(aerospikeClient, statementBuilder, aerospikeClient.getQueryPolicyDefault());
        queryEngine.setScansEnabled(true);
        return queryEngine;
    }

    @Bean(name = "aerospikeIndexRefresher")
    @ConditionalOnMissingBean(name = "aerospikeIndexRefresher")
    public IndexRefresher aerospikeIndexRefresher(AerospikeClient aerospikeClient, IndexesCacheUpdater indexesCacheUpdater) {
        IndexRefresher refresher = new IndexRefresher(aerospikeClient, aerospikeClient.getInfoPolicyDefault(), new InternalIndexOperations(new IndexInfoParser()), indexesCacheUpdater);
        refresher.refreshIndexes();
        return refresher;
    }
}