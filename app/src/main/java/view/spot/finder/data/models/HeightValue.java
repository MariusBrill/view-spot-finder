package view.spot.finder.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nonnull;

@Data
@AllArgsConstructor
public class HeightValue implements Comparable<HeightValue>{

    private String element_id;
    private Double value;

    @Override
    public int compareTo(@Nonnull HeightValue o) {
        return Double.compare(o.getValue(), getValue());
    }
}
