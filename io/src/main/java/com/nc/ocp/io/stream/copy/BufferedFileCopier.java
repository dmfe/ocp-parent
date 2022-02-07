package com.nc.ocp.io.stream.copy;

import com.nc.ocp.io.excepions.CopyFileExceptions;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BufferedFileCopier extends FileCopier {

    BufferedFileCopier(String sourceName) {
        super(sourceName);
    }

    BufferedFileCopier(String sourceName, String destinationName) {
        super(sourceName, destinationName);
    }

    @Override
    public void copy() {
        try (InputStream in = new BufferedInputStream(new FileInputStream(source));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = in.read(buffer)) > 0) {
                out.write(buffer, 0, readLength);
                out.flush();
            }
        } catch (IOException ex) {
            log.error("Unable to copy files: " + ex.getLocalizedMessage(), ex);
            throw new CopyFileExceptions("Unable to copy files: " + ex.getLocalizedMessage(), ex);
        }
    }
}
