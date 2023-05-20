package ai.typeface.documentShare.utils;

public enum AuthClaims {
    SUB("sub"),
    AUD("aud"),
    NAME("name"),
    EMAIL("preferred_username");

    private final String label;

    private AuthClaims(String label){
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
