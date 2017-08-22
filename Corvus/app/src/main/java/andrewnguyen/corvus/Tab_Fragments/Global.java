package andrewnguyen.corvus.Tab_Fragments;

import java.util.ArrayList;
import java.util.List;

import andrewnguyen.corvus.Data.List_Item;

/**
 * Created by andrewnguyen on 6/16/17.
 */

public class Global {
    public static List<List_Item> device_list = new ArrayList<>();
    public List<List_Item> getDevice_list() {
        System.out.println("GLOBAL Device List size" + device_list.size());
        return device_list;
    }
    public void addDevice_toList(List_Item device){
        System.out.println("ADDED DEVICE + " + device.getName());
        device_list.add(device);
        System.out.println("Device List after Add size = " + device_list.size());
        setDevice_list(device_list);
    }
    public void setDevice_list(List<List_Item> device_list) {
        this.device_list = device_list;
    }
}
