package parser;
import java.util.ArrayList;

import dom2app.SimpleTableModel;

public interface ILoader<E>{
	/**
	 * it creates a load method so that the Loader can implement
	 */
	SimpleTableModel load(String fileName, String delimiter, boolean hasHeaderLine);
}
