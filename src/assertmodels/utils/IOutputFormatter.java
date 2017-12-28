package assertmodels.utils;

public interface IOutputFormatter {
    public String makeOutput();
    public void addField(String fieldname, Object expected, Object received);
}
