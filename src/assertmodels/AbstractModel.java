package assertmodels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractModel implements IAbstractModel {
    public Map<String, Object> getValuesOfProperties() throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> valuesMap = new HashMap<>();
        Class modelClass = this.getClass();
        Field[] fields = modelClass.getFields();
        for (Field field : fields) {
            Object value = field.get(this);
            if (value instanceof IAbstractModel) {
                Map<String, Object> childMap = ((IAbstractModel) value).getValuesOfProperties();
                valuesMap.put(field.getName(), childMap);
            } else if (value instanceof List) {
                List<Object> values = new ArrayList<>();
                for (Object o : ((List<Object>) value)) {
                    if (o instanceof IAbstractModel) {
                        values.add(((IAbstractModel) o).getValuesOfProperties());
                    } else {
                        values.add(o);
                    }
                }
                valuesMap.put(field.getName(), values);
            } else {
                valuesMap.put(field.getName(), value);
            }
        }
        return valuesMap;
    }
}
