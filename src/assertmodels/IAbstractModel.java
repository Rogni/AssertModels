package assertmodels;

import java.util.Map;

/**
 * Base interface for custom models.
 *
 * For usage need implemented in custom class method getValuesForComparison.
 *
 * Example:
 * file: CustomModel.java
 *
 * public class CustomModel implement IAbstractModel {
 *     private String title;
 *     private int number;
 *     private CustomModel child;
 *
 *     public String getTitle() {
 *         return this.title;
 *     }
 *
 *     public Map<String, Object> getValuesForComparison() throws IllegalArgumentException, IllegalAccessException {
 *         Map<String, Object> valuesForComparison = new HashMap<>();
 *
 *         valuesForComparison.put("title", title);
 *         valuesForComparison.put("number", number);
 *         valuesForComparison.put("childTitle", child.getTitle());
 *
 *         return valuesForComparison;
 *     }
 * }
 */
public interface IAbstractModel {
    /**
     * Get a Map of values for comparison
     * @return values for comparison
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public Map<String, Object> getValuesForComparison() throws IllegalArgumentException, IllegalAccessException;
}
