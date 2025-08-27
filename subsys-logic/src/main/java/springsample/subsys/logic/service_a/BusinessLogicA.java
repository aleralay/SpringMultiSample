package springsample.subsys.logic.service_a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springsample.common.SQSLogicInterface;
import springsample.subsys.common.SubSysFunctionA;
import springsample.subsys.common.SubSysFunctionB;
import springsample.subsys.config.Messages;

@Component("BusinessLogicA")
public class BusinessLogicA implements SQSLogicInterface{
    private static final Logger log = LoggerFactory.getLogger(BusinessLogicA.class);
    @Autowired
    private SubSysFunctionA funcA;
    @Autowired
    private SubSysFunctionB funcB;
    @Autowired
    private Messages message;
    public void execute() {
        log.info("Start execute.");
        log.info(message.getMessage("MI00001"),"BusinessLogicA");
        funcA.functionA();
        funcB.functionB();
        log.info("End execute.");

    }

}
