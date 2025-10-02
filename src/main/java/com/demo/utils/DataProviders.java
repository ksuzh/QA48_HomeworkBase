package com.demo.utils;

import com.demo.models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> validRegistration() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/UserPositive.csv"));
        String userLine = reader.readLine();
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        while (userLine != null) {
            String[] userSplit = userLine.split(",");
            list.add(new Object[]{new User().setFirstName(userSplit[0])
                    .setLastName(userSplit[1])
                    .setEmail(userSplit[2].replace("@", "+" + i + "@"))
                    .setPassword(userSplit[3])
                    .setConfirmPassword(userSplit[4])
            });
            userLine = reader.readLine();
        }

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> inValidRegistration() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/UserNegative1.csv"));
        String userLine = reader.readLine();
        while (userLine != null) {
            String[] userSplit = userLine.split(",", -1);
            list.add(new Object[]{new User().setFirstName(userSplit[0])
                    .setLastName(userSplit[1])
                    .setEmail(userSplit[2])
                    .setPassword(userSplit[3])
                    .setConfirmPassword(userSplit[4]),
                    userSplit[5]
            });
            userLine = reader.readLine();
        }

        return list.iterator();
    }
}
