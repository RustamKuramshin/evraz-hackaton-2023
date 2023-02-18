package com.cupofcoffee.exhaustermonitoring.db.pg.projections;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Exhauster;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "exhausterInfo", types = {Exhauster.class})
public interface ExhausterInfo {

    UUID getUuid();

    String getName();
}
