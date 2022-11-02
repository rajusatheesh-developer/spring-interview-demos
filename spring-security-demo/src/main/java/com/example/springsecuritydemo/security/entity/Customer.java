package com.example.springsecuritydemo.security.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "customers")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer
{
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


}
