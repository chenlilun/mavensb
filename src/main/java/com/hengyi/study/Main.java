package com.hengyi.study;

import com.hengyi.study.stream.RestServer;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import sun.security.provider.certpath.Vertex;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx() ;
        vertx.deployVerticle(RestServer.class.getName());

    }
}
