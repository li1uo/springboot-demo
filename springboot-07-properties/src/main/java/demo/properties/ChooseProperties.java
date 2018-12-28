package demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 需要@EnableConfigurationProperties或者@Component生效 扫描成bean
 * @author LILUO
 * @date 2018/5/11
 */
@ConfigurationProperties(prefix = "choose")
public class ChooseProperties {

    private String name;

    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ChooseProperties{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
