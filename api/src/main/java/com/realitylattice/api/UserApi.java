/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.realitylattice.model.Tile;
import com.realitylattice.model.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author jwood
 */
public class UserApi {

    private final Datastore datastore;
    private final ObjectMapper mapper = new ObjectMapper();

    @Inject
    public UserApi(Datastore datastore) {
        this.datastore = datastore;
    }

    public Route list = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            final Query<User> query = datastore.createQuery(User.class);
            return query.asList();
        }
    };

    public Route add = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            return datastore.save(mapper.readValue(request.body(), User.class));
        }
    };

    public Route delete = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            return datastore.delete(mapper.readValue(request.body(), User.class));
        }
    };
    
}
