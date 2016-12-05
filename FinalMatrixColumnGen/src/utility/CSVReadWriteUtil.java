package utility;

/*
                 * To change this license header, choose License Headers in Project Properties.
                 * To change this template file, choose Tools | Templates
                 * and open the template in the editor.
 */
import data.TempEntity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ratul
 */
public class CSVReadWriteUtil {

    BufferedReader bfReader = null;
    FileWriter fileWriter = null;
    String line = "";

    public CSVReadWriteUtil() {

    }

    /**
     * this class reads content from csv file, and return a list of row
     *
     * @return list of row or entity
     */
    public List<TempEntity> getAllFromFile() {
        List<TempEntity> featchedData = new ArrayList<>();

        try {
            bfReader = new BufferedReader(new FileReader(ProjectVariable.CSV_FILE_READ_LOCATION));
            while ((line = bfReader.readLine()) != null) {
                String[] entryAry = line.split(ProjectVariable.CSV_DELIMITER);
                TempEntity singleRow = new TempEntity();

                try {

                    singleRow.setTimeStamp(entryAry[0]);
                    singleRow.setMalfunctioningForNeighbor(entryAry[1]);
                    singleRow.setFireNoFire(entryAry[2]);
                    singleRow.setFireAboutToStart(entryAry[3]);
                    singleRow.setWaterOnFire(entryAry[4]);
                    singleRow.setSensorAboutToDie(entryAry[5]);
                    singleRow.setFireAboutToDie(entryAry[6]);
                    singleRow.setmMalfunctioningForWhile(entryAry[7]);
                    singleRow.setmMalfunctioningForLong(entryAry[8]);
                    singleRow.setmFDeadNodeNotWorking(entryAry[9]);
//                    entity.setNodeNo(Integer.parseInt(entryAry[2]));
//                    entity.setLightMeasurment(Integer.parseInt(entryAry[3]));
//                    entity.setSoundMeasurment(Integer.parseInt(entryAry[4]));
//                    entity.setTemparatureMeasurment(Integer.parseInt(entryAry[5]));
                    //these fields are not featched from file; added for operation
                    //entity.setIsSynthesized(false); //by defaultl no data is synthesized
                    //entity.setIsVisited(false); //by defaultl no data is visited*/
                } catch (NumberFormatException e) {
                    System.out.println("Can't convert to int:" + entryAry[0] + " " + entryAry[1] + " " + entryAry[2]);
                }
                featchedData.add(singleRow);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bfReader != null) {
                try {
                    bfReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return featchedData;
    }

    public boolean writeToCsv(List<TempEntity> dataSet) {
        try {
            fileWriter = new FileWriter(ProjectVariable.CSV_FILE_Write_LOCATION, true);

            for (TempEntity data : dataSet) {
                fileWriter.append(String.valueOf(data.getTimeStamp()));
                fileWriter.append(ProjectVariable.CSV_DELIMITER);

                //for (NodeEntity eachNode : data.getEachNode()) {
                fileWriter.append(String.valueOf(data.getFinalDecision()));
                //fileWriter.append(ProjectVariable.CSV_DELIMITER);

                //fileWriter.append(String.valueOf(eachNode.getSumOfAllReadings()));
                //fileWriter.append(ProjectVariable.CSV_DELIMITER);

                //fileWriter.append(String.valueOf(eachNode.getIsMalfunctionWhile()));
                //fileWriter.append(ProjectVariable.CSV_DELIMITER);
                //}
                fileWriter.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean writeToCsv(String data) {

        try {
            fileWriter = new FileWriter(ProjectVariable.CSV_FILE_Write_LOCATION, true);
            fileWriter.append(data);
        } catch (IOException ex) {
            Logger.getLogger(CSVReadWriteUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
        return true;
    }
}
