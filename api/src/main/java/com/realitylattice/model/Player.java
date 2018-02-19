/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.model;

import lombok.Data;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 *
 * @author jwood
 */
@Entity("players")
@Indexes(
    @Index(value = "email", fields = @Field("email"))
)
@Data
public class Player extends Person {

    public Player() {
        super();
    }
    
    private String email;
}
