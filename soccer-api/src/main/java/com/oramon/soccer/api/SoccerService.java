package com.oramon.soccer.api;

import com.lightbend.lagom.javadsl.api.Service;
import static com.lightbend.lagom.javadsl.api.Service.*;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.ServiceCall;

/**
 * Created by Stefano on 2/16/17.
 */
public interface SoccerService extends Service {

    ServiceCall<Score, Done> updateScore(String id);

    ServiceCall<Competitor, Done> updateCompetitor(String id);

    ServiceCall<NotUsed, String> getScore(String id);

    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("soccer").withCalls(
                pathCall("/api/score/:id",  this::getScore),
                pathCall("/api/score/:id", this::updateScore),
                pathCall("/api/comp/:id", this::updateCompetitor)
        ).withAutoAcl(true);
        // @formatter:on
    }




}
