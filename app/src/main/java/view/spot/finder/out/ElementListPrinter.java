package view.spot.finder.out;

import view.spot.finder.data.models.Element;

import java.util.List;

/**
 * This Utility Class contains methods to print a List of {@link Element}.
 */
public abstract class ElementListPrinter {

    public static void print(List<Element> elements) {
        System.out.println("[");
        elements.forEach(element -> System.out.println("  " + element.toString() + ","));
        System.out.println("]");
    }
}
