package com.oramon.soccer.impl;

import akka.Done;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Created by sterui on 2/16/17.
 */
public interface SoccerCommand extends Jsonable {
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class UpdateScore implements SoccerCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
        public final String message;

        @JsonCreator
        public UpdateScore(String message) {
            this.message = Preconditions.checkNotNull(message, "message");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof UpdateScore && equalTo((UpdateScore) another);
        }

        private boolean equalTo(UpdateScore another) {
            return message.equals(another.message);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + message.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("UpdateScore").add("message", message).toString();
        }
    }

    /**
     * A command to say hello to someone using the current greeting message.
     * <p>
     * The reply type is String, and will contain the message to say to that
     * person.
     */
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class Score implements SoccerCommand, PersistentEntity.ReplyType<String> {
        public final String name;
        public final Optional<String> organization;

        @JsonCreator
        public Score(String name, Optional<String> organization) {
            this.name = Preconditions.checkNotNull(name, "name");
            this.organization = Preconditions.checkNotNull(organization, "organization");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof Score && equalTo((Score) another);
        }

        private boolean equalTo(Score another) {
            return name.equals(another.name) && organization.equals(another.organization);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + name.hashCode();
            h = h * 17 + organization.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("Score").add("name", name).add("organization", organization).toString();
        }
    }
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class UpdateCompetitor implements SoccerCommand, PersistentEntity.ReplyType<Done> {
        public final String message;

        @JsonCreator
        public UpdateCompetitor(String message) {
            this.message = Preconditions.checkNotNull(message, "competitor");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof UpdateCompetitor && equalTo((UpdateCompetitor) another);
        }

        private boolean equalTo(UpdateCompetitor another) {
            return message.equals(another.message);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + message.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("UpdateCompetitor").add("competitor", message).toString();
        }
    }
}
