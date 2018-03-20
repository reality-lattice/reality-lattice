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
