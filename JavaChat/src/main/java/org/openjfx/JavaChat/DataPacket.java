package org.openjfx.JavaChat;

import java.io.Serializable;

public class DataPacket implements Serializable {

    private byte[] rawBytes;

    public DataPacket(byte[] rawBytes) {
        this.rawBytes = rawBytes;
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }
}
