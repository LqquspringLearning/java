package com.cultivation.javaBasicExtended.reverseString;

import org.junit.platform.commons.util.StringUtils;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null) throw new IllegalArgumentException();
        if (input.trim().isEmpty()) return new String[0];
        if (StringUtils.isBlank(input)) throw new IllegalArgumentException();
        String[] splitStringArray = input.split(" ");
//        List<String> resultList = Arrays.asList(splitStringArray);
//        Collections.reverse(resultList);
//        return resultList.toArray(new String[0]);
        // use for loop to imply
        int splitStringArrayLength = splitStringArray.length;
        for (int i = 0; i < splitStringArrayLength / 2; i++) {
            String tempStr = splitStringArray[i];
            splitStringArray[i] = splitStringArray[splitStringArrayLength - 1 - i];
            splitStringArray[splitStringArrayLength - 1 - i] = tempStr;
        }
        return splitStringArray;
        // --end-->
    }
}
