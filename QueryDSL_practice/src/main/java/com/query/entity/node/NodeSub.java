package com.query.entity.node;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class NodeSub extends Node {

    private final Long id;

    @QueryProjection
    public NodeSub(Long id, String name, Long weight) {
        super(name, weight);
        this.id = id;
    }
}
