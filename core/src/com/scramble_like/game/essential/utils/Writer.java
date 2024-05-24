package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


public class Writer
{
    private Writer() {}

    public static void writeSetting(String valeur, String option, String fileName, List<String> list, boolean highscore)
    {
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, fileName);
        System.out.println(file.getAbsolutePath());

        // Crée le fichier s'il n'existe pas
        if (!file.exists())
        {
            try { file.createNewFile(); }
            catch (IOException e) { Gdx.app.error("Writer", "Error creating file: " + e.getMessage()); }
        }

        int oldHighscore= Writer.getSetting(option, fileName);
        if(highscore && oldHighscore!=-1) { if(oldHighscore>Integer.parseInt(valeur)) { return; } }
        boolean isFind = false;
        StringBuilder contentBuilder = new StringBuilder();

        try
        {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (String line : lines)
            {
                String firstWord = line.split("\\s+")[0];
                if (Objects.equals(option, firstWord))
                {
                    contentBuilder.append(option).append(" : ").append(valeur).append("\n");
                    isFind = true;
                }
                else { contentBuilder.append(line).append("\n"); }
            }

            // Si l'option n'a pas été trouvée, l'ajouter à la fin
            if (!isFind) { contentBuilder.append(option).append(" : ").append(valeur).append("\n"); }

            // Écrire tout le contenu mis à jour dans le fichier
            try (FileWriter writer = new FileWriter(file)) { writer.write(contentBuilder.toString()); }

        }
        catch (IOException e) { Gdx.app.error("Writer", "Error writing to file: " + e.getMessage()); }
    }

    public static int getSetting( String option, String fileName)
    {
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, fileName);

        // Crée le fichier s'il n'existe pas
        if (!file.exists())
        {
            if(Objects.equals(option, "Volume")) { return 75; }
            if(Objects.equals(option, "SoundVolume")) { return 75; }
            if(Objects.equals(option, "ScrollSpeedMultiplier")) { return 100; }
            return 0;
        }
        else
        {
            try
            {
                List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                for (String line : lines)
                {
                    String firstWord = line.split("\\s+")[0];
                    if (Objects.equals(option, firstWord)) { return Integer.parseInt(findLastWord(line)); }
                }
            }
            catch (IOException e) { Gdx.app.error("Writer", "Error reading file: " + e.getMessage()); }
        }
        if(Objects.equals(option, "Volume")) { return 75; }
        if(Objects.equals(option, "SoundVolume")) { return 75; }
        if(Objects.equals(option, "ScrollSpeedMultiplier")) { return 100; }
        return 0;
    }

    public static String findLastWord(String line)
    {
        if (line == null || line.trim().isEmpty()) { return null; } // Return null if the line is null or empty
        String[] words = line.trim().split("\\s+"); // Split the line into words using whitespace as the delimiter
        return words[words.length - 1]; // Return the last word
    }

    // Golden Strawberry
    public static boolean getLevelInfo(int level, String fileName)
    {
        // Check if the file exists
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, fileName);

        if (!file.exists()) { CreateLevelInfoFile(fileName); }

        try
        {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (String line : lines)
            {
                String firstWord = line.split("\\s+")[0];
                if (Objects.equals("Level_" + level, firstWord)) { return Boolean.parseBoolean(findLastWord(line)); }
            }
        }
        catch (IOException e) { Gdx.app.error("Writer", "Error reading file: " + e.getMessage()); }
        return false;
    }

    public static void writeLevelInfo(int level, boolean value, String fileName)
    {
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, fileName);

        if (!file.exists()) { CreateLevelInfoFile(fileName); }

        boolean isFind = false;
        StringBuilder contentBuilder = new StringBuilder();

        try
        {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (String line : lines)
            {
                String firstWord = line.split("\\s+")[0];
                if (Objects.equals("Level_" + level, firstWord))
                {
                    contentBuilder.append("Level_").append(level).append(" : ").append(value).append("\n");
                    isFind = true;
                }
                else { contentBuilder.append(line).append("\n"); }
            }

            // If the option was not found, add it to the end
            if (!isFind) { contentBuilder.append("Level_").append(level).append(" : ").append(value).append("\n"); }

            // Write all the updated content to the file
            try (FileWriter writer = new FileWriter(file)) { writer.write(contentBuilder.toString()); }
        }
        catch (IOException e) { Gdx.app.error("Writer", "Error writing to file: " + e.getMessage()); }
    }


    private static boolean CreateLevelInfoFile(String filname)
    {
        String currentDir = System.getProperty("user.dir");
        File file = new File(currentDir, filname);

        if (!file.exists())
        {
            try
            {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("Level_1 : false\n");
                writer.write("Level_2 : false\n");
                writer.write("Level_3 : false\n");
                writer.close();
                return true;
            }
            catch (IOException e) { Gdx.app.error("Writer", "Error creating file: " + e.getMessage()); return false; }
        }
        return true;
    }
}
