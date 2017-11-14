package com.firrael.base;

import java.util.Arrays;

public enum Group {

    FIRST("token", "test", "gateway"),
    SECOND("test2", "test3", "find"),
    NONE("n/a");

    Group(String... applications) {
        this.applications = applications;
    }

    private String[] applications;

    public String[] getApplications() {
        return applications;
    }

    public static Group findGroupOfApplication(String application) {
        for (Group group : Group.values()) {
            if (Arrays.asList(group.getApplications()).contains(application)) {
                return group;
            }
        }

        return NONE;
    }
}
