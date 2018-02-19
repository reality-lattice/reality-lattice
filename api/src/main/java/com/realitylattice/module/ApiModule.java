/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jwood
 */
public class ApiModule extends AbstractModule {

    private final Morphia morphia = new Morphia();
    private final Datastore datastore;
    private static final String dbName = "reality-lattice";
    private static final String modelPackage = "com.realitylattice.model";

    public ApiModule() {
        morphia.mapPackage(modelPackage);
        datastore = morphia.createDatastore(new MongoClient(), dbName);
        datastore.ensureIndexes();
    }

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    Datastore provideDatastore() {
        return datastore;
    }

}
