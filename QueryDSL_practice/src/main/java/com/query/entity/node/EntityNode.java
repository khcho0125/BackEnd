package com.query.entity.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "entity_node")
public class EntityNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_id")
    private Long id;

    private String nodeName;

    private Long weight;

    @OneToMany
    @JoinTable(
            name = "child",
            joinColumns = {@JoinColumn(name = "parent", referencedColumnName = "node_id")},
            inverseJoinColumns = {@JoinColumn(name = "child", referencedColumnName = "node_id")}
    )
    private final Set<EntityNode> child = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "child_parent_id"))
    private EntityNode parent;

    public void setParent(EntityNode entityNode) {
        this.parent = entityNode;
    }
}
