package com.github.zxh.classpy.common;

public abstract class ReadableFilePart<R extends BytesReader> extends FilePart {

    /**
     * Reads content, records offset and length.
     */
    public final void read(R reader) {
        try {
            int offset = reader.getPosition();
            readContent(reader);
            int length = reader.getPosition() - offset;
            super.setOffset(offset);
            super.setLength(length);
        } catch (Exception e) {
            System.out.println("error parsing: " + getClass());
            throw e;
        }
    }

    /**
     * Reads content using reader.
     */
    protected void readContent(R reader) {
        for (FilePart fp : getParts()) {
            @SuppressWarnings("unchecked")
            var rfp = (ReadableFilePart<R>) fp;
            rfp.read(reader);
        }
    }

}
