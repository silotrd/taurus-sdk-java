/*
 * Copyright 2021 silot.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.silot.taurus.util;

public class Base64 {
    /**
     * 标准编码表
     */
    private static final byte[] ENCODE_TABLE = { //
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', //
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', //
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', //
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', //
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', //
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', //
            'w', 'x', 'y', 'z', '0', '1', '2', '3', //
            '4', '5', '6', '7', '8', '9', '+', '/' //
    };

    public static String encode(String content) {
        if (content == null) {
            return null;
        }
        byte[] encode = encode(content.getBytes(), false);
        if (encode == null) {
            return null;
        }
        return new String(encode);
    }

    public static byte[] encode(byte[] arr, boolean isMultiLine) {
        if (null == arr) {
            return null;
        }

        int len = arr.length;
        if (len == 0) {
            return new byte[0];
        }

        int evenLen = (len / 3) * 3;
        int cnt = ((len - 1) / 3 + 1) << 2;
        int destLen = cnt + (isMultiLine ? (cnt - 1) / 76 << 1 : 0);
        byte[] dest = new byte[destLen];

        byte[] encodeTable = ENCODE_TABLE;

        for (int s = 0, d = 0, cc = 0; s < evenLen; ) {
            int i = (arr[s++] & 0xff) << 16 | (arr[s++] & 0xff) << 8 | (arr[s++] & 0xff);

            dest[d++] = encodeTable[(i >>> 18) & 0x3f];
            dest[d++] = encodeTable[(i >>> 12) & 0x3f];
            dest[d++] = encodeTable[(i >>> 6) & 0x3f];
            dest[d++] = encodeTable[i & 0x3f];

            if (isMultiLine && ++cc == 19 && d < destLen - 2) {
                dest[d++] = '\r';
                dest[d++] = '\n';
                cc = 0;
            }
        }

        int left = len - evenLen;// 剩余位数
        if (left > 0) {
            int i = ((arr[evenLen] & 0xff) << 10) | (left == 2 ? ((arr[len - 1] & 0xff) << 2) : 0);

            dest[destLen - 4] = encodeTable[i >> 12];
            dest[destLen - 3] = encodeTable[(i >>> 6) & 0x3f];

            dest[destLen - 2] = (left == 2) ? encodeTable[i & 0x3f] : (byte) '=';
            dest[destLen - 1] = '=';
        }
        return dest;
    }


}
