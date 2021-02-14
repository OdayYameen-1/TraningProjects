package com.oday.project.StateMachine;

import com.oday.project.model.ServerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class LoggingUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger("STATE MACHINE");

    public static String getStateInfo(State<ServerStatus, ServerEvent> state) {
        return state != null ? state.getId().name() : "EMPTY STATE";
    }

    public static String getTransitionInfo(Transition<ServerStatus, ServerEvent> t) {
        return String.format("[%s: %s]",
                t.getSource() != null ? t.getSource().getId() : "EMPTY",
                t.getTarget() != null ? t.getTarget().getId() : "EMPTY"
        );
    }
}
