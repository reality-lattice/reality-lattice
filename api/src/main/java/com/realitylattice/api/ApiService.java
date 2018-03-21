/*
 * Copyright 2018 Jonathan Shaw Wood.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @author <a href="mailto:jonathan@woodcomputing.com">Jonathan Wood</a>
 *
 */
@Log
public class ApiService {

    private final TileApi tileApi;
    private final PlayerApi userApi;

    @Inject
    public ApiService(
            TileApi tileApi,
            PlayerApi userApi) {
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
            response.type("application/json");
        });
    }

    private void run() {

        path("/api", () -> {

            before("/*", (q, a) -> log.info("Received api call"));

            path("/users", () -> {
                get("", userApi.list);
                post("", userApi.add);
                delete("", userApi.delete);
            });

            path("/tiles", () -> {
                get("", tileApi.list);
                post("", tileApi.add);
                delete("", tileApi.delete);
                path("/:id/exits", () -> {
                    get("", tileApi.listExits);
                    post("", tileApi.addExit);
//                    delete("", tileApi.delete);
                });
            });

        });
    }

}
