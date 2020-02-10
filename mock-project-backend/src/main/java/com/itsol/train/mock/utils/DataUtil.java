package com.itsol.train.mock.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

@Slf4j
public class DataUtil {

    public static boolean isNotNullAndEmptyString(String srcString) {
        return srcString != null && !"".equals(srcString.trim());
    }


    public static String removeWildcardCharacters(String srcString) {
        if (isNotNullAndEmptyString(srcString)) {
            return "%" + srcString
                    .trim()
                    .replaceAll("%", "\\%")
                    .replaceAll("_", "\\_") + "%";
        }
        return null;
    }

    public static String generateUUIDRandomString() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomString(int lenght) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < lenght) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
