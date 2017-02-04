package co.yellowbricks.boxed.domain;

public enum AuthenticationMethod {
    DEFAULT, FACEBOOK;

    public static AuthenticationMethod of(String authenticationMethodName) {
        if (authenticationMethodName.equalsIgnoreCase("facebook")) {
            return FACEBOOK;
        }
        return DEFAULT;
    }
}
