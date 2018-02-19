/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 *
 * @author jwood
 */
@Entity("users")
@Indexes(
    @Index(value = "email", fields = @Field("email"))
)
@Data
public class User {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
}
