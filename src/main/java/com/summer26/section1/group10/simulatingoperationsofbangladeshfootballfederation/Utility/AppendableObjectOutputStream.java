package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class AppendableObjectOutputStream extends ObjectOutputStream {

    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
