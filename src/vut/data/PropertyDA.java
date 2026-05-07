// 223515760 - CHIGBU CHIDI
// Data access class
package vut.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.io.File;

// declared as abstarct as i wouldnt use object to call it - but methods must be static
public abstract class PropertyDA {

     // must be declared as static to be used within static methods
     private static ArrayList<Property> arrPropertyList  = new ArrayList<>();

     // initialize method
     public static void initialise() throws DataStorageException {
         
         
         File file = new File("property.dat");
         
         if (!file.exists()) {
             arrPropertyList = new ArrayList<>();
             return;
         }
         
         
         
         
         
          try {

               //create object of FileInputStream to allow creation of file propert.dat
               FileInputStream objFileInputStream = new FileInputStream(file);

               //create object of ObjectInputStream to store the obj of file input 
               // converts data in file to objects and allow array read the objects??
               ObjectInputStream objObjectInputStream = new ObjectInputStream(objFileInputStream);

               // store the object into the array
               // you must cast the object to the array data type
               arrPropertyList = (ArrayList<Property>) objObjectInputStream.readObject();

               // clear memory of the file input and object input
               objFileInputStream.close();
               objObjectInputStream.close();

          } catch (ClassNotFoundException e) {

               throw new DataStorageException(e.getMessage());

          } catch (IOException e) {

               throw new DataStorageException(e.getMessage());

          }

     }
     // end of initialize

     // AddProp () method  - pass object of PD class for new entry to compare data
     public static void AddProp(Property objProperty1) throws DuplicateException {
          try {

               // use loop to go through each item of the array list and check prefno for duplicates
               for (Property objProperty2 : arrPropertyList) {

                    if (objProperty2.getPRefNo().equalsIgnoreCase(objProperty1.getPRefNo())) {
                         throw new DuplicateException("Property with this Reference Number already exits");
                    }
               }

               // add the values to array since no duplicates
               arrPropertyList.add(objProperty1);

          } catch (DuplicateException e) {

               throw new DuplicateException(e.getMessage());

          }

     }

     // FindSoldProp() - based on refNo - datatype(property)
     public static Property findSoldProp(String pRefNo) throws NotFoundException {

          Property objProperty = null;
          for (int i = 0; i < arrPropertyList.size(); i++) {
               if (arrPropertyList.get(i).getPRefNo().equalsIgnoreCase(pRefNo)) {
                    objProperty = arrPropertyList.get(i);
               }
          }

          if (objProperty == null) {
               throw new NotFoundException("No property found");
          } else {
               return objProperty;
          }

     }

     // updateagenet name
     public static void updateAgentName(Property objProperty, String newAgentName) throws NotFoundException {

          objProperty.setAgentName(newAgentName);

     }

     // count townhouse
     public static int countTownhouses() {

          int noOfTownhouses = 0;

          for (Property property : arrPropertyList) {
               if (property.getPropType().equalsIgnoreCase("Townhouse")) {
                    noOfTownhouses++;
               }
          }

          return noOfTownhouses;
     }

     // cancel
     public static void deleteSoldProp(Property objProperty) throws NotFoundException {

          arrPropertyList.remove(objProperty);

     }

     // getAll()
     public static ArrayList<Property> getAll() {
          return arrPropertyList;
     }

     // terminate methods
     public static void terminate() throws DataStorageException {

          try {

               FileOutputStream objFileOutputStream = new FileOutputStream("property.dat");
               ObjectOutputStream objObjectOutputStream = new ObjectOutputStream(objFileOutputStream);

               objObjectOutputStream.writeObject(arrPropertyList);

               objFileOutputStream.close();
               objObjectOutputStream.close();

          } catch (IOException e) {
               throw  new DataStorageException(e.getMessage());
          }

     }

}
