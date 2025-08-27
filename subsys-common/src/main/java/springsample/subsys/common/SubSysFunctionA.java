package springsample.subsys.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SubSysFunctionA {
    private static final Logger log = LoggerFactory.getLogger(SubSysFunctionA.class);

    public String functionA() {
        log.info("Start ... functionA");

        log.info("End ... functionA");
        return "functionA";
    }
}
