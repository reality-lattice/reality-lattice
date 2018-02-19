/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author jwood
 */
@Entity(value="persons", noClassnameStored=true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Player.class, name = "player"),
    @JsonSubTypes.Type(value = NPC.class, name = "npc")})
public abstract class Person {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
}
