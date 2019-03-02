package es.urjc.code.daw;

import java.util.Base64;

public class EncoderDecoderBase64
{

    public static String Encode(byte[] bytesArray)
    {
        byte[] encoded = Base64.getEncoder().encode(bytesArray);
        return new String(encoded);
    }

    public static byte[] Decode(String strBase64)
    {
        return Base64.getDecoder().decode(strBase64);
    }

}
