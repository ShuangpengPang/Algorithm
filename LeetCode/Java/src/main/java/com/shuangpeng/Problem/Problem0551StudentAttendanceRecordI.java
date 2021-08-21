package com.shuangpeng.Problem;

public class Problem0551StudentAttendanceRecordI {

    public boolean checkRecord(String s) {
        int absentCount = 0;
        int lateCount = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                absentCount++;
                lateCount = 0;
            } else if (c == 'L') {
                lateCount++;
            } else {
                lateCount = 0;
            }
            if (absentCount >= 2 || lateCount >= 3) {
                return false;
            }
        }
        return true;
    }
}
