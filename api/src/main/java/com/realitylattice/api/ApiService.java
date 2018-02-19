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
public class ApiService {

    private final TileApi tileApi;
    private final UserApi userApi;

    @Inject
    public ApiService(
            TileApi tileApi,
            UserApi userApi) {
        this.tileApi = tileApi;
        this.userApi = userApi;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApiModule());
        ApiService api = injector.getInstance(ApiService.class);
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

            path("/users", () -> {
                get("/list", userApi.list);
                post("/add", userApi.add);
                delete("/add", userApi.delete);
            });

            path("/tiles", () -> {
                get("/list", tileApi.list);
                post("/add", tileApi.add);
                delete("/add", tileApi.delete);
            });

        });
    }

}
