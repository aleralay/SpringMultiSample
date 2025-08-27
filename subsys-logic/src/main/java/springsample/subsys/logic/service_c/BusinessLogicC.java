package springsample.subsys.logic.service_c;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springsample.subsys.common.SubSysFunctionA;
import springsample.subsys.common.SubSysFunctionB;
import springsample.subsys.config.Messages;

@Component
public class BusinessLogicC {
    private static final Logger log = LoggerFactory.getLogger(BusinessLogicC.class);
    @Autowired
    private SubSysFunctionA funcA;
    @Autowired
    private SubSysFunctionB funcB;
    @Autowired
    private Messages message;

    public void execute() {
        log.info("Start execute.");
        log.info(message.getMessage("MI00003"),"BusinessLogicC");
        funcA.functionA();
        funcB.functionB();
        log.info("End execute.");
    }

}
