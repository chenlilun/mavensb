package com.hengyi.study;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req->{
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("hello vertx");
        }).listen(8080) ;
    }
}
