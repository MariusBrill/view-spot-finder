package view.spot.finder.spotfinder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import view.spot.finder.data.mesh.Mesh;
import view.spot.finder.data.models.Element;
import view.spot.finder.data.models.HeightValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MeshSpotFinder {

    @NonNull
    private Mesh mesh;

    private Map<String, Element> visitedElements = new HashMap<>();

    public List<Element> findSpots(int n) {
        visitedElements = new HashMap<>();
        List<Element> spots = new ArrayList<>();
        for (HeightValue heightValue : mesh.getHeightValues()) {
            Element currentElement = mesh.getElementMap().get(heightValue.getElement_id());

            if (isNewSpot(currentElement)) {
                spots.add(currentElement);

                if (spots.size() == n) {
                    return  spots;
                }
            }
        }
        return spots;
    }

    public boolean isNewSpot(Element element) {
        //Check if the element was not visited yet to avoid duplicates
        if(visitedElements.containsKey(element.getId())) {
            return false;
        }
        visitedElements.put(element.getId(), element);
        List<Element> neighbours = new ArrayList<>();
        //Retrieve all neighbouring elements
        for (String nodeId: element.getNodes()) {
            neighbours.addAll(mesh.getNodeToElementRelation().get(nodeId));
        }
        //Check if the selection of elements is a spot
        for(Element neighbourElement : neighbours) {
            if(neighbourElement.getHeight() > element.getHeight()) {
                return false;
            }
            if(neighbourElement.getHeight() == element.getHeight()) {
                if(!visitedElements.containsKey(neighbourElement.getId())) {
                    if (!isNewSpot(neighbourElement)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
