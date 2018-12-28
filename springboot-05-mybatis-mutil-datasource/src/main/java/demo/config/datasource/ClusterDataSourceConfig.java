package demo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import demo.config.datasource.properties.ClusterDataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
@Configuration
@EnableConfigurationProperties(ClusterDataSourceProperties.class)
public class ClusterDataSourceConfig {

    /**
     * master数据源扫描mapper包
     */
    public static final String PACKAGE = "demo.mapper.cluster";

    /**
     * mater数据源扫描 mapper xml包
     */
    public static final String MAPPER_LOCATION= "classpath:mapper/cluster/*.xml";

    @Autowired
    private ClusterDataSourceProperties clusterDataSourceProperties;

    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(clusterDataSourceProperties.getDriverClassName());
        dataSource.setUrl(clusterDataSourceProperties.getUrl());
        dataSource.setUsername(clusterDataSourceProperties.getUserName());
        dataSource.setPassword(clusterDataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
