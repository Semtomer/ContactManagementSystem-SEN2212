package com.example.contactmanagementsystem;

public enum Category {
    FAMILY("Family"),
    FRIEND("Friend"),
    COLLEAGUE("Colleague"),
    ACQUAINTANCE("Acquaintance");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.displayName.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public static Category fromIndex(int index) {
        if (index >= 0 && index < Category.values().length) {
            return Category.values()[index];
        }
        throw new IllegalArgumentException("Invalid index value: " + index);
    }
}
