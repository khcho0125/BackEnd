package com.query.controller;

import com.query.Service.NodeService;
import com.query.entity.node.EntityNode;
import com.query.entity.node.NodeDto;
import com.query.entity.node.NodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NodeController {

    private final NodeService nodeService;

    @PostMapping("/node")
    public EntityNode create(@RequestBody NodeDto nodeDto) {
        return nodeService.createNode(nodeDto);
    }

    @PostMapping("/connect")
    public void connect(@RequestParam Long parent, @RequestParam Long child) {
        nodeService.connect(parent, child);
    }

    @GetMapping("/node")
    public NodeVO getNode(@RequestParam Long id) {
        return nodeService.show(id);
    }

    @GetMapping("/list/node")
    public List<NodeVO> getNode() {
        return nodeService.showList();
    }
}
