package co.istad.mbanking.util;

public class MediaUtil {

    public static String extractExtention(String mediaName) {

        // extract file extension from file upload
        int lastDotIndex = mediaName.lastIndexOf(".");

        return mediaName.substring(lastDotIndex + 1);
    }
}
