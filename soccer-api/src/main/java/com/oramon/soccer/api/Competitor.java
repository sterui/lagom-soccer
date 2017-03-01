package com.oramon.soccer.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Created by sterui on 2/16/17.
 */
@Immutable
@JsonDeserialize
public class Competitor {

    public final String message;

    @JsonCreator
    public Competitor(String message) {

        this.message = Preconditions.checkNotNull(message, "competitor");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof Competitor && equalTo((Competitor) another);
    }

    private boolean equalTo(Competitor another) {
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
        return MoreObjects.toStringHelper("Competitor").add("competitor", message).toString();
    }
}
