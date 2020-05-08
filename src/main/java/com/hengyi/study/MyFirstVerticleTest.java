package com.hengyi.study;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.net.NetServer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.function.Function;

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {
    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8080, "localhost", "/", response -> {
            response.handler(body -> {
                System.out.println(body.toString() + "aaa");
                context.assertTrue(body.toString().contains("Hello"));
                async.complete();
            });
        });
    }

    @Test
    public void testBuffer() {
        Buffer buff = Buffer.buffer();
//        buff.setInt(100,123) ;
        buff.setString(2, "vertx");
        System.out.println(buff.length() + "aaa");
        for (int i = 0; i < buff.length(); i++) {
            System.out.println("key" + i + "====" + buff.getInt(i));
        }
    }

    @Test
    public void testFuture() {

        Function<Function<Integer, Integer>,
                Function<Function<Integer, Integer>,
                        Function<Integer, Integer>>> compose =
                x -> y -> z -> x.apply(y.apply(z));
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> f = compose.apply(square).apply(triple);
        System.out.println(f.apply(2));
        System.out.println(triple.apply(9));
    }

    @Test
    public void testEventBus() {
        EventBus eb = vertx.eventBus();

        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });

        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            // 在这里处理传入连接
            socket.handler(buffer -> {

            });
        });
    }
}
