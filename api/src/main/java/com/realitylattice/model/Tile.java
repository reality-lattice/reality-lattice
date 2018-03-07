/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author jwood
 */
@Entity("tiles")
@Data
public class Tile {
    @Id
    private ObjectId id;
    private String name;
    private Set<Exit> exits;
    
    @JsonIgnore
    private Set<Player> players = new HashSet<>();
    
    @JsonIgnore
    private Set<NPC> npcs = new HashSet<>();
}
