package com.oramon.soccer.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.oramon.soccer.api.Score;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by sterui on 2/16/17.
 */
public class SoccerEntity extends PersistentEntity<SoccerCommand, SoccerEvent, SoccerState>  {

    @Override
    public PersistentEntity.Behavior initialBehavior(Optional<SoccerState> snapshotState) {

    /*
     * Behaviour is defined using a behaviour builder. The behaviour builder
     * starts with a state, if this entity supports snapshotting (an
     * optimisation that allows the state itself to be persisted to combine many
     * events into one), then the passed in snapshotState may have a value that
     * can be used.
     *
     * Otherwise, the default state is to use the Hello greeting.
     */
        BehaviorBuilder b = newBehaviorBuilder(
                snapshotState.orElse(new SoccerState("Empty", LocalDateTime.now().toString(), "Empty")));

    /*
     * Command handler for the UseGreetingMessage command.
     */
        b.setCommandHandler(SoccerCommand.UpdateScore.class, (cmd, ctx) ->
                // In response to this command, we want to first persist it as a
                // GreetingMessageChanged event
                ctx.thenPersist(new SoccerEvent.ScoreUpdated(cmd.message),
                        // Then once the event is successfully persisted, we respond with done.
                        evt -> ctx.reply(Done.getInstance())));

    /*
     * Event handler for the GreetingMessageChanged event.
     */
        b.setEventHandler(SoccerEvent.ScoreUpdated.class,
                // We simply update the current state to use the greeting message from
                // the event.
                evt -> new SoccerState(evt.message, LocalDateTime.now().toString(),state().competitor));

        b.setCommandHandler(SoccerCommand.UpdateCompetitor.class, (cmd, ctx) ->
                // In response to this command, we want to first persist it as a
                // GreetingMessageChanged event
                ctx.thenPersist(new SoccerEvent.CompetitorUpdated(cmd.message),
                        // Then once the event is successfully persisted, we respond with done.
                        evt -> ctx.reply(Done.getInstance())));

    /*
     * Event handler for the GreetingMessageChanged event.
     */
        b.setEventHandler(SoccerEvent.CompetitorUpdated.class,
                // We simply update the current state to use the greeting message from
                // the event.
                evt -> new SoccerState(state().message, LocalDateTime.now().toString(), evt.message));

    /*
     * Command handler for the Hello command.
     */
        b.setReadOnlyCommandHandler(SoccerCommand.Score.class,
                // Get the greeting from the current state, and prepend it to the name
                // that we're sending
                // a greeting to, and reply with that message.
                (cmd, ctx) -> ctx.reply(state().message + " is the score against " + state().competitor + "!"));

    /*
     * We've defined all our behaviour, so build and return it.
     */
        return b.build();
    }
}
