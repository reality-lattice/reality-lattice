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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author <a href="mailto:jonathan@woodcomputing.com">Jonathan Wood</a>
 *
 */
@Data
@Entity("tiles")
@EqualsAndHashCode(callSuper=true)
public class Tile extends BaseModel {

    @Reference
    private Map<String, Exit> exits = new HashMap<>();
    
    @JsonIgnore
    private Set<Player> players = new HashSet<>();
    
    @JsonIgnore
    private Set<NPC> npcs = new HashSet<>();
    
}
