package springsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value="classpath:sysmessage.yaml",ignoreResourceNotFound=true)
@ConditionalOnResource(resources = "classpath:sysmessage.yaml")
public class SysMessages {
    @Autowired
    Environment env;

    public String getMessage(String key) {
        return env.getProperty(key); // プロパティファイルから値を取得
    }
}
