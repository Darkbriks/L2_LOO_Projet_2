package com.scramble_like.game.essential.utils;

import com.scramble_like.game.GameConstant;
import com.scramble_like.game.map.Level_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Writer {

    public Writer(String valeur, String option, String fileName) {
        File file = new File("D:\\libGDX\\L2_LOO_Projet_2\\", fileName);
        System.out.println(file.getAbsolutePath());

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created successfully!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists! Overwriting content.");
        }

        boolean isFind = false;
        try (PrintWriter writer = new PrintWriter(file)) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                for (String line : lines) {
                    for (int i = 0; i < GameConstant.HIGHSCORE_LIST.size(); i++) {
                        String firstWord = line.split("\\s+")[0];
                        System.out.println(firstWord);
                        if (Objects.equals(option, firstWord)) {
                            writer.println(option + " : " + valeur);
                            isFind = true;
                            System.out.println("File rewritten successfully!");
                        } else {
                            writer.println(line);
                        }
                    }
                }
                if (!isFind) {
                    writer.println(option + " : " + valeur);
                    System.out.println("File written successfully!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
