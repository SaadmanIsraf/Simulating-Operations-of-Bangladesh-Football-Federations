package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * A normal ObjectOutputStream always writes a stream header (magic number +
 * version) as soon as it's constructed. That's fine for a brand new file,
 * but if you open an existing file in append mode and write a second
 * ObjectOutputStream header into the middle of it, ObjectInputStream will
 * throw StreamCorruptedException when reading past the first object.
 *
 * This subclass overrides writeStreamHeader() to do nothing, so it can be
 * safely used to append additional objects after the first one without
 * corrupting the file.
 */
public class AppendableObjectOutputStream1 extends ObjectOutputStream {

    public AppendableObjectOutputStream1(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Intentionally left blank: skip writing a new header when appending,
        // since the file already has one from the very first write.
        reset();
    }
}
