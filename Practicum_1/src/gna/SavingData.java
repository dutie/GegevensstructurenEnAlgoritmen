package gna;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SavingData {
    List<Long>     amount_of_compares;
    List<Integer>  size_of_array;
    String         name_of_sort;


    public SavingData(String name_of_sort){
        amount_of_compares = new ArrayList<>();
        size_of_array      = new ArrayList<>();
        this.name_of_sort  = name_of_sort + ".csv";
    }

    public void add_to_data(long compares, int size){
        this.amount_of_compares.add(compares);
        this.size_of_array.add(size);
    }

    public void add_to_data_and_write(SortingAlgorithm.TimeRatio tr) throws IOException {
        List<Double> times = tr.times;
        List<Double> ratios = tr.ratios;
        List<Long>   compares = tr.compares;
        List<Long> elements = tr.elements;

        File file = new File("./src/gna/csvs/"+name_of_sort);
        file.createNewFile();
        FileWriter csv_writer = new FileWriter(file);
        csv_writer.append("index");
        csv_writer.append(",");
        csv_writer.append("amount of elements");
        csv_writer.append(",");
        csv_writer.append("time");
        csv_writer.append(",");
        csv_writer.append("dbr");
        csv_writer.append("\n");

        for(Integer i = 0; i<= times.size()-1; i++){
            csv_writer.append(i.toString());
            csv_writer.append(",");
            csv_writer.append(elements.get(i).toString());
            csv_writer.append(",");
            csv_writer.append(times.get(i).toString());
            csv_writer.append(",");
            csv_writer.append(ratios.get(i).toString());
            csv_writer.append("\n");


        }
        csv_writer.close();
    }

    public void write_to_csv() throws IOException {
        File file = new File("./src/gna/csvs/"+name_of_sort);
        file.createNewFile();
        FileWriter csv_writer = new FileWriter(file);
        csv_writer.append("index");
        csv_writer.append(",");
        csv_writer.append("amount of compares");
        csv_writer.append(",");
        csv_writer.append("size of array");
        csv_writer.append("\n");

        for(Integer i = 0; i<= size_of_array.size()-1; i++){
            csv_writer.append(i.toString());
            csv_writer.append(",");
            csv_writer.append(amount_of_compares.get(i).toString());
            csv_writer.append(",");
            csv_writer.append(size_of_array.get(i).toString());
            csv_writer.append("\n");


        }
        csv_writer.close();
    }
}
