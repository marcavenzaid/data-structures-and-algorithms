package marcavenzaid.lib.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class CSV {

    public static List<String[]> read(String file) {
        List<String[]> data = new LinkedList<String[]>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String dataRow;
            while ((dataRow = reader.readLine()) != null) {
                String[] dataRecords = dataRow.split(",");
                data.add(dataRecords);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public static void main(String[] args) {
        List<String[]> data = CSV.read("data.csv");
        for (String[] row : data) {
            for (String field : row) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
}
