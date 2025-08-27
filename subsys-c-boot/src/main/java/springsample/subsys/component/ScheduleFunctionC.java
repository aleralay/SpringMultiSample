package springsample.subsys.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import springsample.subsys.logic.service_c.BusinessLogicC;

@Component
public class ScheduleFunctionC {
    private static final Logger log = LoggerFactory.getLogger(ScheduleFunctionC.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    BusinessLogicC logic;

    /**
     * 1分間隔で呼び出される定期処理
     */
    @Scheduled(fixedRate = 1000 * 60)
    private void filePollingLogic() {
        log.info("Start ... The time is now {}", dateFormat.format(new Date()));
        // Logicの呼び出し
        logic.execute();
        log.info("End ... The time is now {}", dateFormat.format(new Date()));
    }

}
