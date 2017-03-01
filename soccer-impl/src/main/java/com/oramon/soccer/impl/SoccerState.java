package com.oramon.soccer.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.annotation.Nullable;

/**
 * Created by sterui on 2/16/17.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public final class SoccerState implements CompressedJsonable {

    public final String message;
    public final String competitor;
    public final String timestamp;

    @JsonCreator
    public SoccerState(String message, String timestamp, String competitor) {
        this.message = Preconditions.checkNotNull(message, "message");
        this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
        this.competitor= Preconditions.checkNotNull(competitor, "competitor");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof SoccerState && equalTo((SoccerState) another);
    }

    private boolean equalTo(SoccerState another) {
        return message.equals(another.message) && timestamp.equals(another.timestamp) && competitor.equals(another.competitor);
    }

    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + message.hashCode();
        h = h * 17 + timestamp.hashCode();
        h = h * 17 + competitor.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("SoccerState").add("message", message).add("timestamp", timestamp).add("competitor", competitor).toString();
    }
}
