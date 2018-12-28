package demo.config.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LILUO
 * @date 2018/12/28
 */
@ConfigurationProperties(prefix = "cluster.datasource")
public class ClusterDataSourceProperties {

    private String url;

    private String userName;

    private String password;

    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
