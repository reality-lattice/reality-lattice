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
package com.realitylattice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author <a href="mailto:jonathan@woodcomputing.com">Jonathan Wood</a>
 *
 */
@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Channel.class, name = "channel"),
    @JsonSubTypes.Type(value = Item.class, name = "item"),
    @JsonSubTypes.Type(value = Location.class, name = "location"),
    @JsonSubTypes.Type(value = Person.class, name = "person"),
    @JsonSubTypes.Type(value = Tile.class, name = "tile"),
    @JsonSubTypes.Type(value = Exit.class, name = "exit")})
public class BaseModel {

    @Id
    private ObjectId id;
    private String name;
    private String description;

}
