//도무지 모르겠어서 이건 답 봤어여..

import java.util.*;
import java.math.*;

class Solution {
    long solution(int w, int h) {
        int gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();
        return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
    }
}
