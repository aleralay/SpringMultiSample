package springsample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import springsample.common.SQSLogicInterface;
import springsample.config.SysMessages;

@Service
public class SQSPollingService {
    private static final Logger log = LoggerFactory.getLogger(SQSPollingService.class);
    @Autowired
    private SysMessages message;
    @Autowired
    ApplicationContext context;

    /**
     * 1秒間隔で呼び出される定期処理
     */
    @Scheduled(fixedRate = 1000 * 1)
    private void polling() {
        log.info(message.getMessage("SI00001"), "polling");
        SQSLogicInterface bean = (SQSLogicInterface) context.getBean("BusinessLogicA");
        bean.execute();
        SQSLogicInterface bean2 = (SQSLogicInterface) context.getBean("BusinessLogicB");
        bean2.execute();
        log.info(message.getMessage("SI00002"), "polling");
    }

}
