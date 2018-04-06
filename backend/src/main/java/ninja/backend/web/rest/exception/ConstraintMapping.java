package ninja.backend.web.rest.exception;

import java.util.HashMap;
import java.util.Map;


public final class ConstraintMapping {

    private static final Map<String, String> mappings = new HashMap<>();

    static {
        mappings.put("UNQ_USE_SPC_93CDAD", "User.setPasswordCode.unique");
        mappings.put("UNQ_USE_U_6DC017", "User.username.unique");
    }

    private ConstraintMapping() {
    };

    public static String getErrorCodeForConstraint(String constraint) {
        return mappings.get(constraint);
    }

}
