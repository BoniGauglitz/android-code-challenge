package Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alemoreirac.empregoligado.R;

import java.util.ArrayList;
import java.util.List;

import Model.Interview;

public class InterViewsDialog extends android.support.v4.app.DialogFragment {

    public static void showDialog( Activity activity,List<Interview>interviews){

        final Dialog dialog = new Dialog(activity);

        View view = (activity).getLayoutInflater().inflate(R.layout.dialog_interviews, null);

        ListView lv =  view.findViewById(R.id.lstv_interviews);

        lv.setAdapter(new ArrayAdapter(activity,  android.R.layout.simple_list_item_1, interviews));


        dialog.setContentView(view);

        dialog.show();


    }
}
