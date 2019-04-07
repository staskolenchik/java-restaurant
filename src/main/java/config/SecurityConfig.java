package config;

import java.util.*;

public class SecurityConfig {

    public static final String ROLE_CUSTOMER = "customer";
    public static final String ROLE_ADMIN = "admin";

    private  static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        //Configure for customer role
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/dishes");

        mapConfig.put(ROLE_CUSTOMER, urlPatterns1);

        //configure for admin role;
        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/admin");
        urlPatterns2.add("/editDish");
        urlPatterns2.add("/createDish");
        urlPatterns2.add("/deleteDish");
        urlPatterns2.add("/orders");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
