package andrewnguyen.corvus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import andrewnguyen.corvus.Data.List_Item;
import andrewnguyen.corvus.Tab_Fragments.Global;


public class View_Device extends AppCompatActivity {
    private final String EXTRA_NAME = "NAME",
            EXTRA_STATUS = "STATUS",
            EXTRA_RADIO="RADIO",
            EXTRA_AUDIO = "AUDIO",
            EXTRA_VIDEO = "VIDEO",
            EXTRA_AMOUNT_AUDIO = "AMOUNT_AUDIO",
            EXTRA_AMOUNT_VIDEO = "AMOUNT_VIDEO",
            EXTRA_AMOUNT_RADIO = "AMOUNT_RADIO",
            EXTRA_PRICE_AUDIO = "PRICE_AUDIO",
            EXTRA_PRICE_VIDEO = "PRICE_VIDEO",
            EXTRA_PRICE_RADIO = "PRICE_RADIO",
            BUNDLE_EXTRAS ="DEVICE_DETAILS";
    TextView total, audio_tag, radio_tag, video_tag;
    EditText video_price, audio_price, radio_price, name;
    ImageView radio_switch, audio_switch, video_switch, radio_image, audio_image, video_image;
    String name_str, status, radio, audio, video, amount_audio, amount_video, amount_radio, price_audio, price_video, price_radio, total_str;
    double total_doub;
    Global global;
    private List<List_Item> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__device);
        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);
        //init variables
        variable_initialization(extras);
        //auto_hide_hint_setup
        hint_setup(extras);
        //sum_total
        total_summation();
        //Checking services
        service_check();
    }
    private boolean isOn(String str){
        if(str.equals("ON")){
            return true;
        }
        else{
            return false;
        }
    }
    private void service_check(){
        if(isOn(audio)){
            audio_text_on();
        }
        else{
            audio_text_off();
        }
        if(isOn(radio)){
            radio_text_on();
        }
        else{
            radio_text_off();
        }
        if(isOn(video)){
            video_text_on();
        }
        else{
            video_text_off();
        }
    }
    private void disappear_on_click(final EditText editText, final String str){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    editText.setHint("");
                else
                    editText.setHint(str);
            }
        });
    }
    private void hint_setup(Bundle extras){
        final String name_final = extras.get(EXTRA_NAME).toString();
        final String radio_price_final = extras.get(EXTRA_PRICE_RADIO).toString();
        final String audio_price_final = extras.get(EXTRA_PRICE_AUDIO).toString();
        final String video_price_final = extras.get(EXTRA_PRICE_VIDEO).toString();
        disappear_on_click(name, name_final);
        disappear_on_click(audio_price, audio_price_final);
        disappear_on_click(video_price, video_price_final);
        disappear_on_click(radio_price, radio_price_final);
    }
    private void total_summation(){
//        total_doub = Double.parseDouble(amount_audio)+Double.parseDouble(amount_video)+Double.parseDouble(amount_radio);
//        total_str = Double.toString(total_doub);

        //extra---------
        Global global = new Global();
        double audio_service = global.getTotal_service();
        final DecimalFormat df = new DecimalFormat( "0.00" );
        total_str = df.format(audio_service);
        //extra---------
        total.setText(total_str + " USD");
    }
    private void variable_initialization(Bundle extras){
        global = new Global();
        name = (EditText) findViewById(R.id.name_et);
        audio_price = (EditText) findViewById(R.id.audio_price);
        radio_price = (EditText) findViewById(R.id.radio_price);
        video_price = (EditText) findViewById(R.id.video_price);
        audio_tag = (TextView) findViewById(R.id.audio_tag);
        radio_tag = (TextView) findViewById(R.id.radio_tag);
        video_tag = (TextView) findViewById(R.id.video_tag);
        total = (TextView) findViewById(R.id.total_tv);
        radio_switch = (ImageView) findViewById(R.id.radio_switch);
        audio_switch = (ImageView) findViewById(R.id.audio_switch);
        video_switch = (ImageView) findViewById(R.id.video_switch);
        radio_image = (ImageView) findViewById(R.id.radio_image);
        audio_image = (ImageView) findViewById(R.id.audio_image);
        video_image = (ImageView) findViewById(R.id.video_image);
        //string init
        radio = extras.get(EXTRA_RADIO).toString();
        audio = extras.get(EXTRA_AUDIO).toString();
        video = extras.get(EXTRA_VIDEO).toString();
        amount_radio = extras.get(EXTRA_AMOUNT_RADIO).toString();
        amount_audio= extras.get(EXTRA_AMOUNT_AUDIO).toString();
        amount_video = extras.get(EXTRA_AMOUNT_VIDEO).toString();
        price_audio = extras.get(EXTRA_PRICE_AUDIO).toString();
        price_video = extras.get(EXTRA_PRICE_VIDEO).toString();
        price_radio = extras.get(EXTRA_PRICE_RADIO).toString();
        status = extras.get(EXTRA_STATUS).toString();
        //Edit Text Assignment
        audio_price.setHint(price_audio);
        video_price.setHint(price_video);
        radio_price.setHint(price_radio);
        name_str=extras.get(EXTRA_NAME).toString();
        name.setHint(extras.get(EXTRA_NAME).toString());
    }

    private String toggle_status(String device, String tag){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.swtch);
        if(isOn(device)){
            device = "OFF";
            toggle_image_off(tag);
            mp.start();
        }
        else{
            device= "ON";
            toggle_image_on(tag);
            mp.start();
        }
        return device;
    }

    private void toggle_image_on(String tag){
        if(tag.equals("radio")){
            radio_text_on();
        }
        if(tag.equals("audio")){
            audio_text_on();
        }
        if(tag.equals("video")){
            video_text_on();
        }
    }
    private void toggle_image_off(String tag){
        if(tag.equals("radio")){
            radio_text_off();
        }
        if(tag.equals("audio")){
            audio_text_off();
        }
        if(tag.equals("video")){
            video_text_off();
        }
    }

    private void audio_text_on(){
        audio_image.setImageResource(R.drawable.audios_on);
        audio_price.setTextColor(getResources().getColor(R.color.green_on));
        audio_price.setHintTextColor(getResources().getColor(R.color.green_on));
        audio_tag.setTextColor(getResources().getColor(R.color.green_on));
        audio_switch.setImageResource(R.drawable.on_switch);
    }
    private void radio_text_on(){
        radio_image.setImageResource(R.drawable.radio_on);
        radio_price.setHintTextColor(getResources().getColor(R.color.green_on));
        radio_price.setTextColor(getResources().getColor(R.color.green_on));
        radio_tag.setTextColor(getResources().getColor(R.color.green_on));
        radio_switch.setImageResource(R.drawable.on_switch);
    }
    private void video_text_on(){
        video_image.setImageResource(R.drawable.video_on);
        video_price.setTextColor(getResources().getColor(R.color.green_on));
        video_price.setHintTextColor(getResources().getColor(R.color.green_on));
        video_tag.setTextColor(getResources().getColor(R.color.green_on));
        video_switch.setImageResource(R.drawable.on_switch);
    }
    private void audio_text_off(){
        audio_image.setImageResource(R.drawable.audios_off);
        audio_price.setTextColor(getResources().getColor(R.color.red_off));
        audio_price.setHintTextColor(getResources().getColor(R.color.red_off));
        audio_tag.setTextColor(getResources().getColor(R.color.red_off));
        audio_switch.setImageResource(R.drawable.off_switch);
    }
    private void radio_text_off(){
        radio_image.setImageResource(R.drawable.radio_off);
        radio_price.setTextColor(getResources().getColor(R.color.red_off));
        radio_price.setHintTextColor(getResources().getColor(R.color.red_off));
        radio_tag.setTextColor(getResources().getColor(R.color.red_off));
        radio_switch.setImageResource(R.drawable.off_switch);
    }
    private void video_text_off(){
        video_image.setImageResource(R.drawable.video_off);
        video_price.setTextColor(getResources().getColor(R.color.red_off));
        video_price.setHintTextColor(getResources().getColor(R.color.red_off));
        video_tag.setTextColor(getResources().getColor(R.color.red_off));
        video_switch.setImageResource(R.drawable.off_switch);
    }
    //Public:
    public void radio_toggle(View view){
        radio = toggle_status(radio, "radio");
    }
    public void audio_toggle(View view){
        audio = toggle_status(audio, "audio");
    }
    public void video_toggle(View view){
        video = toggle_status(video, "video");
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Main_Activity.class);
        startActivity(i);
        finish();
    }
    private String set_services(String status, EditText et, String price){
        if(status.equals("ON")){
            if(et.getText().toString() == null || et.getText().toString().isEmpty()){
                return price;
            }
            else{
                return price = et.getText().toString();
            }
        }
        else{
            return "0";
        }
    }
    private void name_set(){
        if(name.getText().toString()!=null && !name.getText().toString().isEmpty()) {

            for(int i = 0; i<listData.size(); i++){
                if(name.getText().toString().equals(listData.get(i).getName())){
                    Toast.makeText(this, "Device name is a duplicate, please rename",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            }

            for(int i = 0; i<listData.size(); i++){
                if(name_str.equals(listData.get(i).getName())){
                    System.out.println("REMOVED ***** " + listData.get(i).getName());
                    listData.remove(i);
                }
            }

            name_str = name.getText().toString();
        }
    }
    private void device_set(){
        List_Item item = new List_Item();
        item.setAudio(audio);
        item.setPrice_audio(price_audio);
        item.setVideo(video);
        item.setPrice_video(price_video);
        item.setRadio(radio);
        item.setPrice_radio(price_radio);
        item.setStatus(status);
        item.setAmount_audio(amount_audio);
        item.setAmount_radio(amount_radio);
        item.setAmount_video(amount_video);
        item.setName(name_str);
        for(int i = 0; i<listData.size(); i++){
            if(name_str.equals(listData.get(i).getName())){
                listData.remove(i);
            }
        }
        listData.add(item);
        global.setDevice_list(listData);
    }
    public void submitChanges(View view){
        price_audio=set_services(audio, audio_price, price_audio);
        price_video=set_services(video, video_price, price_video);
        price_radio=set_services(radio, radio_price, price_radio);
        listData = global.getDevice_list();
        name_set();
        device_set();
        onBackPressed();
    }
}
