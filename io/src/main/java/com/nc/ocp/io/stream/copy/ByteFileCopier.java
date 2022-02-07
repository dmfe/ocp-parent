package com.nc.ocp.io.stream.copy;

import com.nc.ocp.io.excepions.CopyFileExceptions;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ByteFileCopier extends FileCopier {

    ByteFileCopier(String sourceName) {
        super(sourceName);
    }

    ByteFileCopier(String sourceName, String destinationName) {
        super(sourceName, destinationName);
    }

    @Override
    public void copy() {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch (IOException ex) {
            log.error("Unable to copy files: " + ex.getLocalizedMessage(), ex);
            throw new CopyFileExceptions("Unable to copy files: " + ex.getLocalizedMessage(), ex);
        }
    }
}
