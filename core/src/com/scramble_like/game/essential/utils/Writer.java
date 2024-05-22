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
import java.util.Map;
import java.util.Objects;


public class Writer {
    public Writer(String valeur, String option, String fileName, Map<Integer, String> list) {
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, fileName);
        System.out.println(file.getAbsolutePath());

        // Crée le fichier s'il n'existe pas
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    //System.out.println("File created successfully!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } /*else {
            //System.out.println("File already exists! Updating content.");
        }*/

        int oldHighscore= Writer.getSetting(valeur, option, fileName, list);
        if(oldHighscore!=-1){
            if(oldHighscore>Integer.parseInt(valeur)){
                return;
            }
        }
        boolean isFind = false;
        StringBuilder contentBuilder = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (String line : lines) {
                String firstWord = line.split("\\s+")[0];
                //System.out.println("Line : "+line);
                //System.out.println("FirstWord : "+firstWord);
                if (Objects.equals(option, firstWord)) {
                    contentBuilder.append(option).append(" : ").append(valeur).append("\n");
                    isFind = true;
                    //System.out.println("File updated successfully!");
                } else {
                    contentBuilder.append(line).append("\n");
                }
            }

            // Si l'option n'a pas été trouvée, l'ajouter à la fin
            if (!isFind) {
                contentBuilder.append(option).append(" : ").append(valeur).append("\n");
                //System.out.println("New entry added successfully!");
            }

            // Écrire tout le contenu mis à jour dans le fichier
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(contentBuilder.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getSetting(String valeur, String option, String fileName, Map<Integer, String> list) {
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, fileName);
        //System.out.println(file.getAbsolutePath());

        // Crée le fichier s'il n'existe pas
        if (!file.exists()) {
            return 0;
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                for (String line : lines) {
                    String firstWord = line.split("\\s+")[0];
                    //System.out.println("Line: " + line);
                    //System.out.println("FirstWord: " + firstWord);
                    if (Objects.equals(option, firstWord)) {
                        //System.out.println("Values of " + option + " found successfully: " + findLastWord(line));
                        return Integer.parseInt(findLastWord(line));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String findLastWord(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null; // Return null if the line is null or empty
        }

        String[] words = line.trim().split("\\s+"); // Split the line into words using whitespace as the delimiter
        return words[words.length - 1]; // Return the last word
    }
}
