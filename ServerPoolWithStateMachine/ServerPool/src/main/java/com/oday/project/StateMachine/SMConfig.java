package com.oday.project.StateMachine;


import com.oday.project.controller.ServerController;
import com.oday.project.model.Server;
import com.oday.project.model.ServerStatus;
import com.oday.project.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import static com.oday.project.StateMachine.LoggingUtils.LOGGER;
import static com.oday.project.StateMachine.LoggingUtils.getStateInfo;

@Configuration
@EnableStateMachine
public class SMConfig extends StateMachineConfigurerAdapter<ServerStatus, ServerEvent> {
@Autowired
ServerRepository serverRepository;

    @Override
    public void configure(StateMachineStateConfigurer<ServerStatus, ServerEvent> states) throws Exception {
        states.withStates().initial(ServerStatus.Createing,entryAction())
        .state(ServerStatus.Active,entryAction())
                .end(ServerStatus.Active);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ServerStatus, ServerEvent> transitions) throws Exception {

        transitions.withInternal()

            .source(ServerStatus.Createing)


                .action(myAction())
                .timerOnce(15000);




    }


    private Action<ServerStatus, ServerEvent> myAction() {
        return ctx -> {
            Server value = ctx.getExtendedState().get("Server", Server.class);
            value.setStatus(ServerStatus.Active);
            System.out.println("the server with id= " +value.getId()+" now is in = "+value.getStatus());
            serverRepository.save(value);
        };
    }


    @Bean
    public Action<ServerStatus, ServerEvent> entryAction() {
        return ctx -> LOGGER.info("Entry action {} to get from {} to {}",
                ctx.getEvent(),
                getStateInfo(ctx.getSource()),
                getStateInfo(ctx.getTarget()));
    }

}
