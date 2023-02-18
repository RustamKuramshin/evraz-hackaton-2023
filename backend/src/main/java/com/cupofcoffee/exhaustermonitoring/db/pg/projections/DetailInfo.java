package com.cupofcoffee.exhaustermonitoring.db.pg.projections;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Detail;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "detailInfo", types = {Detail.class})
public interface DetailInfo {

    UUID getUuid();

    String getName();
}
