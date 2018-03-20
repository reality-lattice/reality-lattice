/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realitylattice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author jwood
 */
@Data
public class BaseModel {

    @Id
    private ObjectId id;

}
