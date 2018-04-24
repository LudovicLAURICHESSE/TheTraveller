package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.myn4s.thetravellerandroid.R;

/**
 * Created by argiraud on 10/04/2018.
 */

public class EndGameFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.end_game, null);
        return v;
    }
}
