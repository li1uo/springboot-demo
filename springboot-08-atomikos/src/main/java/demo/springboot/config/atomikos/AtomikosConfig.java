package demo.springboot.config.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * 分布式事务atomikos配置类
 *
 * @author LILUO
 * @date 2019/08/28
 */
@EnableTransactionManagement
@Configuration
public class AtomikosConfig {

    @Primary
    @Bean("datasource1")
    public DataSource db1DataSourceBean() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("db1");
        atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        Properties properties = new Properties();
        properties.put("URL", "jdbc:mysql://localhost:3306/ssmshiro?characterEncoding=utf8&useSSL=false");
        properties.put("user", "root");
        properties.put("password", "liluo");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    @Primary
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean SqlSessionFactory(@Qualifier("datasource1") DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

    @Bean("datasource2")
    public DataSource db2DataSourceBean() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("db2");
        atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        Properties properties = new Properties();
        properties.put("URL", "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false");
        properties.put("user", "root");
        properties.put("password", "liluo");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    @Bean("sqlSessionFactory2")
    public SqlSessionFactoryBean SqlSessionFactory2(@Qualifier("datasource2") DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

    @Bean(name = "xatx")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
