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

    public ApiModule() {
        morphia.mapPackage("com.realitylattice.model");
        datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
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
