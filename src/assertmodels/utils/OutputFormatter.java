package assertmodels.utils;


import java.util.ArrayList;
import java.util.List;

public class OutputFormatter implements IOutputFormatter {
    private List<OutputRow> outputRows = new ArrayList<>();
    private int fieldnameSize = 10;
    private int expectedSize = 10;
    private int receivedSize = 10;
    public void addField(String fieldname, Object expected, Object received) {
        String expectedString = makeObjectString(expected);
        String receivedString = makeObjectString(received);
        if (fieldnameSize < fieldname.length()) {
            fieldnameSize = fieldname.length() + 2;
        }
        if (expectedSize < expectedString.length()) {
            expectedSize = expectedString.length() + 2;
        }
        if (receivedSize < receivedString.length()) {
            receivedSize = receivedString.length() + 2;
        }
        outputRows.add(
                new OutputRow(fieldname, expectedString, receivedString)
        );
    }

    public String makeOutput() {
        String table = "";

        String separator = "+" + mulStr("-", fieldnameSize);
        separator += "+" + mulStr("-", expectedSize);
        separator += "+" + mulStr("-", receivedSize) + "+\n";
        table += separator;
        table += String.format(
                "|%" + fieldnameSize +"s|%" + expectedSize + "s|%" + receivedSize + "s|\n",
                "Fieldname", "Expected", "Received"
        );
        table += separator;
        table += separator;
        for (OutputRow outputRow : outputRows) {
            table += makeRow(outputRow, fieldnameSize, expectedSize, receivedSize);
            table += separator;
        }

        return table;
    }



    private String mulStr(String str, int num) {
        String res = "";
        for (int i = 0; i < num; ++i) {
            res += str;
        }
        return res;
    }

    private String makeObjectString(Object o) {
        String res = o != null? o.toString(): "Null";
        if (res.length() > 30 && o != null) {
            res = "Not null object";
        }
        return res;
    }


    private String makeRow(OutputRow row, int sizeOfFieldname, int sizeOfExpected, int sizeOfReceived) {
        return String.format(
            "|%" + sizeOfFieldname +"s|%" + sizeOfExpected + "s|%" + sizeOfReceived + "s|\n",
            row.getFieldname(), row.getExpected(), row.getReceived()
        );
    }


}
