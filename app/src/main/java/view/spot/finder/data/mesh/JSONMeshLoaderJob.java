package view.spot.finder.data.mesh;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import view.spot.finder.data.models.Element;
import view.spot.finder.data.models.HeightValue;
import view.spot.finder.data.models.Node;

import javax.annotation.Nonnull;
import java.util.*;

@RequiredArgsConstructor
public class JSONMeshLoaderJob {

    private final Map<String, List<Element>> nodeToElementRelation = new HashMap<>();
    private Map<String, Element> elementMap = new HashMap<>();

    @Nonnull
    private String jsonString;

    public Mesh run() {
        Gson g = new Gson();
        RawMeshDataWrapper rawMeshDataWrapper = g.fromJson(jsonString, RawMeshDataWrapper.class);
        Map<String, Node>  nodeMap = loadNodes(rawMeshDataWrapper.nodes);
        elementMap = loadElements(rawMeshDataWrapper.getElements());
        List<HeightValue> heightValues = rawMeshDataWrapper.getValues();
        setupHeightValues(heightValues);
        Collections.sort(heightValues);
        return new Mesh(elementMap, nodeMap, heightValues, nodeToElementRelation);
    }

    private Map<String, Node>  loadNodes(List<Node> nodes) {
        Map<String, Node> nodeMap = new HashMap<>();

        nodes.forEach(node -> nodeMap.put(node.getId(), node));
        return nodeMap;
    }

    private Map<String, Element>  loadElements(List<Element> elements) {
        Map<String, Element> elementMap = new HashMap<>();

        elements.forEach(element -> {
            String id = element.getId();
            List<String> nodeIds = element.getNodes();
            elementMap.put(id, element);

            nodeIds.forEach(nodeId -> {
                List<Element> nodeList = this.nodeToElementRelation.getOrDefault(nodeId, new ArrayList<>());
                nodeList.add(element);
                nodeToElementRelation.put(nodeId, nodeList);
            });
        });
        return elementMap;
    }

    private void setupHeightValues(List<HeightValue> values) {
        values.forEach(value -> elementMap.get(value.getElement_id()).setHeight(value.getValue()));
    }

    @Data
    @AllArgsConstructor
    private class RawMeshDataWrapper {
        private List<Node> nodes;
        private List<Element> elements;
        private List<HeightValue> values;

    }
}
