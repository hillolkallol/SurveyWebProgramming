/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;


/**
 *
 * @author Ratul
 */
public class ProjectVariable {
    
    /**
     *
     */
    public static int NO_OF_ENTITY_NEEDED = 40;
    public static int NO_OF_NODE = 15;
    //public static int NO_OF_ENTITY_NEEDED = 3;
    public static String CSV_FILE_READ_LOCATION = "F:\\NMT_Course\\Sensor Networking\\Project\\Datas\\newgen\\input_temp_temporal_data.csv";
    //public static String CSV_FILE_READ_LOCATION = "F:\\NMT_Course\\Sensor Networking\\Project\\Datas\\fielddata2016\\test.csv";
    //public static String CSV_FILE_Write_LOCATION = "F:\\NMT_Course\\Sensor Networking\\Project\\Datas\\fielddata2016\\vector.csv";
    //public static String CSV_FILE_Write_LOCATION = "F:\\NMT_Course\\Sensor Networking\\Project\\Datas\\time_dimention\\opt_temporal_single_node_while.csv";
    public static String CSV_FILE_Write_LOCATION = "F:\\NMT_Course\\Sensor Networking\\Project\\Datas\\newgen\\opt_final_column_matrix.csv";
    public static String CSV_DELIMITER = ",";
    public static final String NEW_LINE_SEPARATOR = "\n";    
    
    public static int THRESHHOLD_SENSOR_DYING =  800;
    public static int THRESHHOLD_FIRE_ABOUT_TO_END =  780;
    
    
    public static String IS_WORKING_WHILE = "WORKING_WHILE"; 
    public static String IS_NOT_WORKING_WHILE = "NOT_WORKING_WHILE"; 
    
    public static String IS_WORKING_LONG = "WORKING_LONG"; 
    public static String IS_NOT_WORKING_LONG = "NOT_WORKING_LONG"; 
    
    public static String IS_POTENTIAL_THREAT_WHILE = "THREAT_WHILE"; 
    public static String IS_NOT_POTENTIAL_THREAT_WHILE = "NOT_THREAT_WHILE"; 
    
}
