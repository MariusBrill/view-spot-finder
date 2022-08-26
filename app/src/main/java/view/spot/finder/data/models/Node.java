package view.spot.finder.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nonnull;

@Data
@AllArgsConstructor
public class Node {
    @Nonnull
    private String id;
    private double x;
    private double y;
}
