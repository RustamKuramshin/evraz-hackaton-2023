package com.cupofcoffee.exhaustermonitoring.db.pg.entities;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@QueryEntity
@Entity(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "exhauster_id")
    private Exhauster exhauster;

    @Column(name = "name")
    private String name;

}
