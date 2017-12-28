package assertmodels;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * Base interface for models.
 *
 * Default implemented getValuesForComparison return HashMap of child public fields.
 *
 * Example:
 * File: Model.java
 *
 * public class Model extends AbstractModel {
 *     public Model(Integer id, String name) {
 *         this.name = name;
 *         this.id = id;
 *     }
 *
 *     public String name;
 *     public Integer id;
 *     public List<Model> childs = new ArrayList<>();
 *
 *     @Override
 *     public String toString() {
 *         return String.format("%d: %s (%s)", id, name, getClass().toString());
 *     }
 * }
 *
 * ==========
 * Model father = new Model(0, "Oberon");
 * father.childs.add(new Model(1, "Corwin"));
 * father.childs.add(new Model(2, "Eric"));
 * System.out.print(father.getValuesForComparison());
 * // Out: {name=Oberon, id=0, childs=[1: Corwin (class tests.models.Model), 2: Eric (class tests.models.Model)]}
 *
 */
public class AbstractModel implements IAbstractModel {
    /**
     * Get Map of public fields.
     * Keys - fieldnames
     * Values - values of fields reduced to Object
     *
     * @return Map<Keys, Values>
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Override
    public Map<String, Object> getValuesForComparison() throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> valuesMap = new HashMap<>();
        Class modelClass = this.getClass();
        Field[] fields = modelClass.getFields();
        for (Field field : fields) {
            Object value = field.get(this);
            valuesMap.put(field.getName(), value);
        }
        return valuesMap;
    }
}
