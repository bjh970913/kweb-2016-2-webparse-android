package com.showdownstudio.kweb_11streetapi_study.Service;

/**
 * Created by bjh970913 on 07/11/2016 - 00:29
 */

import java.io.InputStream;
import java.io.OutputStream;

class Utils {
    static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
