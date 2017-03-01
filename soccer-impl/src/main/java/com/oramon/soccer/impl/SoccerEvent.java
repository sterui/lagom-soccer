package com.oramon.soccer.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.annotation.Nullable;

/**
 * Created by sterui on 2/16/17.
 */
public interface SoccerEvent extends Jsonable {

    /**
     * An event that represents a change in greeting message.
     */
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class ScoreUpdated implements SoccerEvent {
        public final String message;

        @JsonCreator
        public ScoreUpdated(String message) {
            this.message = Preconditions.checkNotNull(message, "message");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof ScoreUpdated && equalTo((ScoreUpdated) another);
        }

        private boolean equalTo(ScoreUpdated another) {
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
            return MoreObjects.toStringHelper("ScoreUpdated").add("message", message).toString();
        }
    }
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class CompetitorUpdated implements SoccerEvent {
        public final String message;

        @JsonCreator
        public CompetitorUpdated(String message) {
            this.message = Preconditions.checkNotNull(message, "competitor");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof CompetitorUpdated && equalTo((CompetitorUpdated) another);
        }

        private boolean equalTo(CompetitorUpdated another) {
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
            return MoreObjects.toStringHelper("CompetitorUpdated").add("competitor", message).toString();
        }
    }
}
