package andrewnguyen.corvus.Tab_Fragments;

import java.util.ArrayList;
import java.util.List;

import andrewnguyen.corvus.Data.List_Item;

/**
 * Created by andrewnguyen on 6/16/17.
 */

public class Global {
    public static double wallet_total = 37.50;

    public static double audio_usage = 0.00;

    public static double getAudio_usage() {
        return audio_usage;
    }

    public static void addAudio_usage() {
        Global.audio_usage +=4;
        //TODO Change for change in price
    }

    public static double getWallet_total() {
        return wallet_total;
    }

    public static void subtract_Wallet_total() {
        Global.wallet_total -= 4;
    }
    //TODO Change for change in price

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
