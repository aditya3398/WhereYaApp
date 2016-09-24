package wya.whereyaat;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by shuka on 9/24/2016.
 */

public class RankActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_view);

        //obtains string of locations from friends from database
        //calls rank algorithm method

        //Dummy values hardcoded:

        String [] locations = {"CRC", "CRC", "Hack GT", "Sweet Hut"
                , "Britain Dining Hall", "Waffle House", "Waffle House"
                , "CRC", "Hack GT", "CULC", "Hack GT", "Instructional Center"
                , "Booby Dodd Stadium", "Hack GT"};

        rankEm(locations);
    }

    public void rankEm(String [] locations){

    }
}

