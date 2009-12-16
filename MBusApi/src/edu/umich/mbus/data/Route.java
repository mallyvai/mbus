package edu.umich.mbus.data;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Represents a route.
 * 
 * @author gopalkri
 */
public class Route {

	/**
	 * Initializes this object with data from element. Assumes that element
	 * contains valid XML for a route.
	 * 
	 * @param element
	 *            XML element representing this route.
	 */
	public Route(Element element) {
		// Get name.
		mName = Utilities.getStringValueByTagName(element, "name");
		if (TimeFeed.PRINT_DEBUG_OUTPUT) {
			System.out.println("Name: " + mName);
		}

		// Get id.
		mId = Integer
				.parseInt(Utilities.getStringValueByTagName(element, "id"));
		if (TimeFeed.PRINT_DEBUG_OUTPUT) {
			System.out.println("Id: " + mId);
		}

		// Get stops.
		NodeList stops = element.getElementsByTagName("stop");
		if (TimeFeed.PRINT_DEBUG_OUTPUT) {
			System.out.println("Number of stops: " + stops.getLength());
		}

		for (int s = 0; s < stops.getLength(); ++s) {
			Node stopNode = stops.item(s);
			if (stopNode.getNodeType() == Node.ELEMENT_NODE) {
				Element stopElement = (Element) stopNode;
				mStops.add(new Stop(stopElement));
			}
		}
	}
	
	/**
	 * Gets name of route.
	 * @return Route name.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * Returns id of route.
	 * @return Route id.
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * Gets list of stops on route.
	 * @return List of stops on route.
	 */
	public List<Stop> getStops() {
		return mStops;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = "Name: " + mName + " | Id: " + mId + " | Stops:\n";
		for (Stop stop : mStops) {
			ret += "\t" + stop.toString().replace("\n", "\n\t");
		}
		return ret;
	}
	
	private String mName;
	private int mId;
	private ArrayList<Stop> mStops = new ArrayList<Stop>();
}