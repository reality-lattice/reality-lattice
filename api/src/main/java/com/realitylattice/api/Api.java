/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.api;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.realitylattice.module.ApiModule;
import lombok.extern.java.Log;
import static spark.Spark.*;

/**
 *
 * @author jwood
 */
@Log
public class Api {

    private final TileApi tileApi;

    @Inject
    public Api(TileApi tileApi) {
        this.tileApi = tileApi;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApiModule());
        Api api = injector.getInstance(Api.class);
        api.init();
        api.run();
    }

    private void init() {
        initExceptionHandler((e) -> {
            System.out.println("Shut her down, Martha, she's sucking mud");
            System.exit(1);
        });

        after((request, response) -> {
            response.header("Content-Encoding", "gzip");
        });
    }

    private void run() {

        path("/api", () -> {

            before("/*", (q, a) -> log.info("Received api call"));

            path("/tiles", () -> {
                get("/list", tileApi.list);
                post("/add", tileApi.add);
            });

        });
    }

}
