package springsample.subsys.component;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.scheduling.annotation.Scheduled;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

import springsample.subsys.logic.service_a.BusinessLogicA;

class ScheduleFunctionATest {

    private ScheduleFunctionA sut;
    private BusinessLogicA mockLogic;
    private ListAppender<ILoggingEvent> listAppender;
    private Logger classLogger;

    @BeforeEach
    void setUp() throws Exception {
        sut = new ScheduleFunctionA();
        mockLogic = Mockito.mock(BusinessLogicA.class);

        Field f = ScheduleFunctionA.class.getDeclaredField("logic");
        f.setAccessible(true);
        f.set(sut, mockLogic);

        // attach an appender to capture logs for tests that assert logging
        classLogger = (Logger) LoggerFactory.getLogger(ScheduleFunctionA.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        if (classLogger != null && listAppender != null) {
            classLogger.detachAppender(listAppender);
            listAppender.stop();
        }
    }

    private void invokeFilePollingLogic() throws Exception {
        Method m = ScheduleFunctionA.class.getDeclaredMethod("filePollingLogic");
        m.setAccessible(true);
        m.invoke(sut);
    }

    @Test
    void callsLogicExecute_once() throws Exception {
        invokeFilePollingLogic();
        verify(mockLogic, times(1)).execute();
    }

    @Test
    void multipleInvocations_callsMultipleTimes() throws Exception {
        invokeFilePollingLogic();
        invokeFilePollingLogic();
        verify(mockLogic, times(2)).execute();
    }

    @Test
    void handlesLogicThrowingException_propagates() throws Exception {
        doThrow(new RuntimeException("boom")).when(mockLogic).execute();
        Method m = ScheduleFunctionA.class.getDeclaredMethod("filePollingLogic");
        m.setAccessible(true);
        InvocationTargetException ite = assertThrows(InvocationTargetException.class, () -> m.invoke(sut));
        assertTrue(ite.getTargetException() instanceof RuntimeException);
        assertEquals("boom", ite.getTargetException().getMessage());
    }

    @Test
    void nullLogic_throwsNullPointerException() throws Exception {
        // set logic to null to simulate mis-wired bean
        Field f = ScheduleFunctionA.class.getDeclaredField("logic");
        f.setAccessible(true);
        f.set(sut, null);

        Method m = ScheduleFunctionA.class.getDeclaredMethod("filePollingLogic");
        m.setAccessible(true);
        InvocationTargetException ite = assertThrows(InvocationTargetException.class, () -> m.invoke(sut));
        assertTrue(ite.getTargetException() instanceof NullPointerException);
    }

    @Test
    void scheduledAnnotation_hasFixedRate1000() throws Exception {
        Method m = ScheduleFunctionA.class.getDeclaredMethod("filePollingLogic");
        Scheduled ann = m.getAnnotation(Scheduled.class);
        assertNotNull(ann, "filePollingLogic should be annotated with @Scheduled");
        assertEquals(1000L, ann.fixedRate());
    }

    @Test
    void logsContainStartAndEndMessages() throws Exception {
        // invoke
        invokeFilePollingLogic();

        boolean foundStart = listAppender.list.stream()
                .map(ILoggingEvent::getFormattedMessage)
                .anyMatch(msg -> msg.startsWith("Start ... The time is now"));

        boolean foundEnd = listAppender.list.stream()
                .map(ILoggingEvent::getFormattedMessage)
                .anyMatch(msg -> msg.startsWith("End ... The time is now"));

        assertTrue(foundStart, "Start log message should be emitted");
        assertTrue(foundEnd, "End log message should be emitted");
    }

}
