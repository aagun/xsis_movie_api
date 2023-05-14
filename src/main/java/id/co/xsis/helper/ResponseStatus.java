package id.co.xsis.helper;

public enum ResponseStatus {
    SUCCESS(1),
    FAILED(0);

    private final int value;
    ResponseStatus(int value) { this.value = value; }
    public int getValue() { return value;}
}
