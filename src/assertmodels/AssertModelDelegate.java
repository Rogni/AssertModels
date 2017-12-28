package assertmodels;

import assertmodels.utils.IOutputFormatter;
import assertmodels.utils.OutputFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Asserted two IAbstractModel objects.
 */
public class AssertModelDelegate {

    /**
     *
     * @return
     */
    public String getFormatter() {
        return formatterName;
    }

    /**
     *
     * @param formatterName
     * @throws ClassNotFoundException
     */
    public void setFormatter(String formatterName) throws ClassNotFoundException {
        this.formatterClass = Class.forName(formatterName);
        this.formatterName = formatterName;
    }

    private String formatterName = "assertmodels.utils.OutputFormatter";

    private Class formatterClass = OutputFormatter.class;

    /**
     * @param expected
     * @param received
     * @throws Exception
     */
    public void assertModels(IAbstractModel expected, IAbstractModel received) throws Exception {
        
        if (expected == received) {
            return;
        }
        Map<String, Object> expectedMap = expected.getValuesOfProperties();
        Map<String, Object> receivedMap = received.getValuesOfProperties();
        assertMaps(expectedMap, receivedMap);
    }

    /**
     *
     * @param expectedMap
     * @param receivedMap
     * @throws Exception
     */
    private void assertMaps(Map expectedMap, Map receivedMap) throws Exception {

        IOutputFormatter formatter = (IOutputFormatter) formatterClass.getConstructor().newInstance();
        Boolean throwEx = assertMap("", expectedMap, receivedMap, formatter);

        if (throwEx) {
            System.out.print(formatter.makeOutput());
            throw new java.lang.AssertionError();
        }
    }

    /**
     *
     * @param path
     * @param expected
     * @param received
     * @param formatter
     * @return
     * @throws Exception
     */
    private boolean assertMap(String path, Map expected, Map received, IOutputFormatter formatter) throws Exception {
        Boolean throwEx = false;
        Map<String, Object> expectedMap = new HashMap<>(expected);
        Map<String, Object> receivedMap = new HashMap<>(received);
        for (Object key : expectedMap.keySet()) {
            Object expectedValue = expectedMap.get(key);
            Object receivedValue = receivedMap.remove(key);
            throwEx |= assertObjects(
                path + key.toString(),
                expectedValue,
                receivedValue,
                formatter
            );
        }
        if (!receivedMap.isEmpty()) {
            throwEx = true;
            receivedMap.forEach((Object key, Object value) -> {
                if (value != null) {
                    formatter.addField(
                        path + key.toString(),
                        null,
                        receivedMap.get(key)
                    );
                }
            });
        }
        return throwEx;
    }

    /**
     *
     * @param path
     * @param expected
     * @param received
     * @param formatter
     * @return
     * @throws Exception
     */
    private boolean assertList(String path, List expected, List received, IOutputFormatter formatter) throws Exception {
        Boolean throwEx = false;
        if (expected == null) expected = new ArrayList();
        if (received == null) received = new ArrayList();

        for (int i = 0; i < Math.max(expected.size(), received.size()); i++) {
            Object expectedValue = i < expected.size() ? expected.get(i) : null;
            Object receivedValue = i < received.size() ? received.get(i) : null;
            throwEx |= assertObjects(
                path + "[" + i + "]",
                expectedValue,
                receivedValue,
                formatter
            );
        }

        return throwEx;
    }

    /**
     *
     * @param path
     * @param expectedValue
     * @param receivedValue
     * @param formatter
     * @return
     * @throws Exception
     */
    private boolean assertObjects(String path, Object expectedValue, Object receivedValue, IOutputFormatter formatter) throws Exception {
        Boolean throwEx = false;
        try {
            if (expectedValue instanceof List || receivedValue instanceof List) {
                List expectedList = ((List) expectedValue);
                List receivedList = ((List) receivedValue);
                throwEx |= assertList(
                    path,
                    expectedList,
                    receivedList,
                    formatter
                );
            } else if (expectedValue == null || receivedValue == null) {
                assert receivedValue == expectedValue;
            } else if (expectedValue instanceof Map && receivedValue instanceof Map) {
                throwEx |= assertMap(
                    path + ".",
                    (Map) expectedValue,
                    (Map) receivedValue,
                    formatter
                );
            } else if (expectedValue instanceof IAbstractModel && receivedValue instanceof IAbstractModel) {
                throwEx |= assertMap(
                    path + ".",
                    ((IAbstractModel) expectedValue).getValuesOfProperties(),
                    ((IAbstractModel) receivedValue).getValuesOfProperties(),
                    formatter
                );
            } else {
                assert expectedValue.equals(receivedValue);
            }

        } catch (java.lang.AssertionError ex) {
            throwEx = true;
            formatter.addField(
                path,
                expectedValue,
                receivedValue
            );
        }
        return throwEx;
    }

}
