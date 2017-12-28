package assertmodels.utils;

public class OutputRow {

    public OutputRow() {}
    public OutputRow(String fieldname, String expected, String received) {
        setFieldname(fieldname);
        setExpected(expected);
        setReceived(received);
    }

    private String fieldname;
    private String expected;
    private String received;

    public String getFieldname() {
        return fieldname;
    }

    public String getExpected() {
        return expected;
    }

    public String getReceived() {
        return received;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public void setReceived(String received) {
        this.received = received;
    }
}
