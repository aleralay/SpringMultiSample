package springsample.subsys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value="classpath:message.yaml",ignoreResourceNotFound=true)
@ConditionalOnResource(resources = "classpath:message.yaml")
public class Messages {
    @Autowired
    Environment env;

    public String getMessage(String key) {
        return env.getProperty(key); // プロパティファイルから値を取得
    }
}
