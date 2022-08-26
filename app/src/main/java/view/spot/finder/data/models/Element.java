package view.spot.finder.data.models;

import lombok.Data;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

@Data
public class Element {

    private static final String stringRepresentation = "{element_id: %s, value: %s},";

    @Nonnull
    private String id;
    private double height;
    private List<String> nodes;

    public Element(@Nonnull String id, @Nonnull String ... nodeIds) {
        this.id = id;
        this.nodes = Arrays.asList(nodeIds);
    }

    @Override
    public String toString() {
        return String.format(stringRepresentation, this.id, this.height);
    }
}
