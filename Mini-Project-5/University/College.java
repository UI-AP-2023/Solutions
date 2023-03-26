package University;

public enum College {
    CS(36), ECONOMIC(19), CHEMISTRY(32);
    final private int code;
    private College(int code)
    {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
