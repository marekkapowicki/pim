package com.marekk.pim.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Uuids {
    private static final Pattern pattern = Pattern.compile("-");

    static String uuid() {
        return pattern.matcher(UUID.randomUUID().toString()).replaceAll("");
    }
}
