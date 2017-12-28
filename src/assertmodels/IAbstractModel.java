package assertmodels;

import java.util.Map;

public interface IAbstractModel {
    public Map<String, Object> getValuesOfProperties() throws IllegalArgumentException, IllegalAccessException;
}
