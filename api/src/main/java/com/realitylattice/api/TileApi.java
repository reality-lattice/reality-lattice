/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.realitylattice.model.Tile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author jwood
 */
//@UtilityClass
public class TileApi {

    private final Datastore datastore;
    private final ObjectMapper mapper = new ObjectMapper();
//            .setAnnotationIntrospector(new JacksonLombokAnnotationIntrospector());

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
//            Map<String, String> params = toMap(URLEncodedUtils.parse(request.body(), Charset.defaultCharset()));
            Tile tile = mapper.readValue(request.body(), Tile.class);
            return datastore.save(tile);
        }
    };

    private static Map<String, String> toMap(List<NameValuePair> pairs) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            NameValuePair pair = pairs.get(i);
            map.put(pair.getName(), pair.getValue());
        }
        return map;
    }

}
