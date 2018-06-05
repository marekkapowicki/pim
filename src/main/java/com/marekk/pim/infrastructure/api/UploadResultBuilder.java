package com.marekk.pim.infrastructure.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.atomic.LongAdder;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(staticName = "builder")
public class UploadResultBuilder {
    LongAdder created = new LongAdder();
    LongAdder updated = new LongAdder();
    LongAdder errors = new LongAdder();

    public UploadResultBuilder incrementCreated() {
        created.increment();
        return this;
    }

    public UploadResultBuilder incrementUpdated() {
        updated.increment();
        return this;
    }

    public UploadResultBuilder incrementErrors() {
        errors.increment();
        return this;
    }
    public UploadResult build() {
        return new UploadResult(created.intValue(), updated.intValue(), errors.intValue());
    }

}
