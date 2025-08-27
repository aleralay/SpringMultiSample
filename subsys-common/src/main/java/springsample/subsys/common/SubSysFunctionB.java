package springsample.subsys.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SubSysFunctionB {
    private static final Logger log = LoggerFactory.getLogger(SubSysFunctionB.class);

    public String functionB() {
        log.info("Start ... functionB");

        log.info("End ... functionB");
        return "functionB";
    }
}
