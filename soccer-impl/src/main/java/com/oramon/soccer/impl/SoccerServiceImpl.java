package com.oramon.soccer.impl;

import akka.Done;
import akka.NotUsed;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.oramon.soccer.api.Competitor;
import com.oramon.soccer.api.Score;
import com.oramon.soccer.api.SoccerService;

import java.util.Optional;

/**
 * Created by sterui on 2/16/17.
 */
public class SoccerServiceImpl implements SoccerService{

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public SoccerServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(SoccerEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> getScore(String id) {
        return request -> {
            // Look up the hello world entity for the given ID.
            PersistentEntityRef<SoccerCommand> ref = persistentEntityRegistry.refFor(SoccerEntity.class, id);
            // Ask the entity the Hello command.
            return ref.ask(new SoccerCommand.Score(id, Optional.empty()));
        };
    }

    @Override
    public ServiceCall<Score, Done> updateScore(String id) {
        return request -> {
            // Look up the hello world entity for the given ID.
            PersistentEntityRef<SoccerCommand> ref = persistentEntityRegistry.refFor(SoccerEntity.class, id);
            // Tell the entity to use the greeting message specified.
            return ref.ask(new SoccerCommand.UpdateScore(request.message));
        };

    }

    @Override
    public ServiceCall<Competitor, Done> updateCompetitor(String id) {
        return request -> {
            // Look up the hello world entity for the given ID.
            PersistentEntityRef<SoccerCommand> ref = persistentEntityRegistry.refFor(SoccerEntity.class, id);
            // Tell the entity to use the greeting message specified.
            return ref.ask(new SoccerCommand.UpdateCompetitor(request.message));
        };

    }
}
