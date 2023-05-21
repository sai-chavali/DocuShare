package ai.typeface.documentShare.domain;

public enum AccessLevel {
    READ("READ"),
    WRITE("WRITE"),
    COMMENT("COMMENT"),
    OWNER("OWNER");

    private String name;

    private AccessLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
