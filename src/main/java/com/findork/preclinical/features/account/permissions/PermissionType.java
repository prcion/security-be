package com.findork.preclinical.features.account.permissions;

public enum PermissionType {
    /**
     *  Action based permissions
     */
    NONE(0),
    VIEW(1),
    EDIT(2);

    private final int restrictionDegree;

    PermissionType(int restrictionDegree) {
        this.restrictionDegree = restrictionDegree;
    }

    public boolean isLessRestrictive(PermissionType permission) {
        return this.restrictionDegree <= permission.getRestrictionDegree();
    }

    public boolean isLessRestrictiveExclusive(PermissionType permission) {
        return this.restrictionDegree < permission.getRestrictionDegree();
    }

    public int getRestrictionDegree() {
        return this.restrictionDegree;
    }

    public boolean isNone() {
        return this == NONE;
    }
}
