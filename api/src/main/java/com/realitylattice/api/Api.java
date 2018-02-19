/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.api;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.realitylattice.model.Tile;
import com.realitylattice.module.ApiModule;
import lombok.extern.java.Log;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import static spark.Spark.*;

/**
 *
 * @author jwood
 */
@Log
public class Api {

    private final Datastore datastore;

    @Inject
    Api(Datastore datastore) {
        this.datastore = datastore;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApiModule());
        Api api = injector.getInstance(Api.class);
        api.init();
        api.run();
    }

    private void init() {
        initExceptionHandler((e) -> System.out.println("Shut her down, Martha, she's sucking mud"));

        after((request, response) -> {
            response.header("Content-Encoding", "gzip");
        });
    }

    private void run() {

        path("/api", () -> {

            before("/*", (q, a) -> log.info("Received api call"));

            path("/tiles", () -> {
                get("/list", (request, response) -> {
                    final Query<Tile> query = datastore.createQuery(Tile.class);
                    return query.asList();
                });
                post("/add", ((request, response) -> {
                    Tile tile = new Tile();
                    tile.setName("Martha");
                    return datastore.save(tile);
                }));
            });

        });

        //Tiles
        //
    }

}
