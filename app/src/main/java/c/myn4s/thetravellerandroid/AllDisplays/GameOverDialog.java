package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import c.myn4s.thetravellerandroid.R;

/**
 * Created by Myn4s on 24/04/2018.
 */

public class GameOverDialog extends DialogFragment {
    PlayActivity caller;

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.game_over_dialog, null));

        return builder.create();
    }

    public void back(View v){}
    public void playGame(View v){}

}
