package com.eventostec.api.domain.adress;


import com.eventostec.api.domain.Event;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "adress")
@Entity
public class Adress {
    @Id
    @GeneratedValue
    private UUID id;

    private String city;

    private String uf;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
