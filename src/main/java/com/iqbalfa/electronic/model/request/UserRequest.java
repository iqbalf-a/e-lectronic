package com.iqbalfa.electronic.model.request;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
public class UserRequest {

    private String name;

    private String address;


}