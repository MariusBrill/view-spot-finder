package view.spot.finder.data.mesh;

import lombok.Getter;
import lombok.NonNull;
import view.spot.finder.data.models.Element;
import view.spot.finder.data.models.HeightValue;
import view.spot.finder.data.models.Node;

import javax.annotation.Nonnull;
import java.util.*;

@Getter
public class Mesh {

    @NonNull
    private final Map<String, Element> elementMap;
    @NonNull
    private final Map<String, Node> nodeMap;
    @NonNull
    private final List<HeightValue> heightValues;
    @NonNull
    private final Map<String, List<Element>> nodeToElementRelation;

    Mesh(@Nonnull Map<String, Element> elementMap, @Nonnull Map<String, Node> nodeMap, @Nonnull List<HeightValue> heightValues, @Nonnull Map<String, List<Element>> nodeToElementRelation) {
        this.elementMap = elementMap;
        this.nodeMap = nodeMap;
        this.heightValues = heightValues;
        this.nodeToElementRelation = nodeToElementRelation;
    }

    public static Mesh fromJsonString(String object) {
        return new JSONMeshLoaderJob(object).run();
    }

}
