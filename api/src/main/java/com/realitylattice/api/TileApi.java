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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.realitylattice.model.Tile;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author jwood
 */
public class TileApi {

    private final Datastore datastore;
    private final ObjectMapper mapper = new ObjectMapper();

    @Inject
    public TileApi(Datastore datastore) {
        this.datastore = datastore;
    }

    public Route list = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            final Query<Tile> query = datastore.createQuery(Tile.class);
            return query.asList();
        }
    };

    public Route add = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            return datastore.save(mapper.readValue(request.body(), Tile.class));
        }
    };

    public Route delete = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            return datastore.delete(mapper.readValue(request.body(), Tile.class));
        }
    };

    public Route listExits = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            final Query<Tile> query = datastore.createQuery(Tile.class).filter("id", new ObjectId(request.params(":id"))).project("id", true).project("exits", true);
            return query.asList();
        }
    };

    public Route addExit = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            final Query<Tile> query = datastore.createQuery(Tile.class).filter("id", new ObjectId(request.params(":id"))).project("id", true).project("exits", true);
            return query.asList();
        }
    };

}
