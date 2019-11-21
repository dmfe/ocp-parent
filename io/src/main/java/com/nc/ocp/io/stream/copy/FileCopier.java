package com.nc.ocp.io.stream.copy;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import util.UUIDGenerator;

import java.io.File;

public abstract class FileCopier {

    private static final String DEFAULT_COPY_PREFIX = "COPY";
    private static final String COPY_DELIMITER = ".";

    final File source;
    File destination;

    public static Builder builder(CopierType type) {
        return new Builder(type);
    }

    private static String generateDefaultDestinationName(String sourceName) {
        return sourceName + COPY_DELIMITER + DEFAULT_COPY_PREFIX + COPY_DELIMITER + UUIDGenerator.generateUUID();
    }

    FileCopier(String source) {
        this(source, generateDefaultDestinationName(source));
    }

    FileCopier(String sourceName, String destinationName) {
        this.source = new File(sourceName);
        this.destination = new File(destinationName);
    }

    public abstract void copy();

    public static class Builder {
        private String source;
        private String destination;
        private CopierType type;

        private Builder(CopierType type) {
            this.type = type;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }


        public FileCopier build() {
            Preconditions.checkArgument(source != null, "File to copy is not defined.");
            Preconditions.checkArgument(type != null, "Type of copier is not defined.");

            FileCopier copier;
            switch (type) {
                case BYTE:
                    copier = StringUtils.isEmpty(destination) ?
                            new ByteFileCopier(source) : new ByteFileCopier(source, destination);
                    return copier;
                case BUFFERED:
                    copier = StringUtils.isEmpty(destination) ?
                            new BufferedFileCopier(source) : new BufferedFileCopier(source, destination);
                    return copier;
                default:
                    throw new UnsupportedOperationException("Unsupported copier type - " + type);
            }
        }
    }
}
