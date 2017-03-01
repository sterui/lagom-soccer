package com.oramon.soccer.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.oramon.soccer.api.SoccerService;

/**
 * Created by sterui on 2/16/17.
 */
public class SoccerModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(SoccerService.class, SoccerServiceImpl.class));
    }
}
