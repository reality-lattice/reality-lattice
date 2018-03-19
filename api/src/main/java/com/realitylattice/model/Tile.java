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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author jwood
 */
@Entity("tiles")
@Data
public class Tile extends BaseModel {

    private String name;

    @Embedded
    private Set<Exit> exits = new HashSet<>();
    
    @JsonIgnore
    private Set<Player> players = new HashSet<>();
    
    @JsonIgnore
    private Set<NPC> npcs = new HashSet<>();
}
