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

/**
 * This class wraps the logic for finding a certain amount of spots within a mesh.
 */
@RequiredArgsConstructor
public class MeshSpotFinder {

    /**
     * The mesh to be checked for spots.
     */
    @NonNull
    private Mesh mesh;

    /**
     * During a run for finding spots, this map is used to keep track of all visited elements since each element may
     * only be visited once.
     */
    private Map<String, Element> visitedElements = new HashMap<>();

    /**
     * Takes an integer representing the maximum amount of spots to search for. If multiple elements have the same height,
     * and form a spot as neighbours, only one of these elements is returned to represent the spot.
     * @param n The maximum amount of Spots.
     * @return A List containing instances of {@link Element}. Each instance resembles a spot.
     */
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

    private boolean isNewSpot(Element element) {
        //Check if the element was not visited yet to avoid duplicates.
        if(visitedElements.containsKey(element.getId())) {
            return false;
        }
        visitedElements.put(element.getId(), element);
        List<Element> neighbours = new ArrayList<>();
        //Retrieve all neighbouring elements.
        for (String nodeId: element.getNodes()) {
            neighbours.addAll(mesh.getNodeToElementRelation().get(nodeId));
        }

        for(Element neighbourElement : neighbours) {
            //Check if the selection of elements is a spot.
            if(neighbourElement.getHeight() > element.getHeight()) {
                return false;
            }
            //In this case, multiple elements may form a spot.
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
