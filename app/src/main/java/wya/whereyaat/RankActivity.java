package wya.whereyaat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by shuka on 9/24/2016.
 */

public class RankActivity extends Activity {

    private ArrayList<Person> persons = new ArrayList<Person>();
    private ArrayList<Location> locations = new ArrayList<Location>();

    private TextView rank1;
    private TextView rank2;
    private TextView rank3;
    private TextView rank4;
    private TextView rank5;

    private TextView friends1;
    private TextView friends2;
    private TextView friends3;
    private TextView friends4;
    private TextView friends5;

    private boolean removedElements = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_view);

        //obtains arraylist of person objects from database
        //calls rank algorithm method

        //Dummy values hardcoded:

        Person a = new Person("Sammy", "Sweet Hut");
        Person b = new Person("Bobby", "Sweet Hut");
        Person c = new Person("Harris", "Sweet Hut");
        Person d = new Person("John", "Sweet Hut");
        Person e = new Person("Bill", "CRC");
        Person f = new Person("Omar", "Waffle House");
        Person g = new Person("Tyrone", "CRC");
        Person h = new Person("Shukie", "Waffle House");
        Person i = new Person("Vishwa", "Hack GT");
        Person j = new Person("Noel", "CULC");
        Person k = new Person("Borg", "Hack GT");
        Person l = new Person("Andrew", "Instructional Center");
        Person m = new Person("Rob", "Bobby Dodd Stadium");
        Person n = new Person("Harry", "Sweet Hut");
        Person o = new Person("Aditya", "Hack GT");
        Person p = new Person("Binit", "Sweet Hut");
        Person q = new Person("Akash", "Waffle House");
        Person r = new Person("Kunal", "Sweet Hut");



        persons.add(a);
        persons.add(b);
        persons.add(c);
        persons.add(d);
        persons.add(e);
        persons.add(f);
        persons.add(g);
        persons.add(h);
        persons.add(i);
        persons.add(j);
        persons.add(k);
        persons.add(l);
        persons.add(m);
        persons.add(n);
        persons.add(o);
        persons.add(p);
        persons.add(q);
        persons.add(r);

        rankEm(persons);
    }

    public void rankEm(ArrayList<Person> persons){

        //complex algorithm --> simplify please


        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;

        int grandCounter = 0;
        int sideCounter = 0;

        String counter1Location = "";
        String counter2Location = "";
        String counter3Location = "";
        String counter4Location = "";
        String counter5Location = "";

        for(int x = 0; x < persons.size(); x++){
            grandCounter++;

            for(int y = 0; y < persons.size(); y++){


                if(persons.get(x).getLocation().equals(persons.get(y).getLocation())){
                    sideCounter++;

                    if(grandCounter==1){
                        counter1++;
                        counter1Location = persons.get(x).getLocation();
                    }
                    if(grandCounter==2){
                        counter2++;
                        counter2Location = persons.get(x).getLocation();
                    }
                    if(grandCounter==3){
                        counter3++;
                        counter3Location = persons.get(x).getLocation();
                    }
                    if(grandCounter==4){
                        counter4++;
                        counter4Location = persons.get(x).getLocation();
                    }
                    if(grandCounter==5){
                        counter5++;
                        counter5Location = persons.get(x).getLocation();
                    }

                    if(grandCounter>=6){

                        if(grandCounter==6){
                            Location location1 = new Location(counter1, counter1Location);
                            Location location2 = new Location(counter2, counter2Location);
                            Location location3 = new Location(counter3, counter3Location);
                            Location location4 = new Location(counter4, counter4Location);
                            Location location5 = new Location(counter5, counter5Location);

                            locations.add(location1);
                            locations.add(location2);
                            locations.add(location3);
                            locations.add(location4);
                            locations.add(location5);
                        }

                        sortBasedOnFrequency(locations);

                        if(sideCounter>locations.get(0).getFrequency()){
                            locations.get(0).setFrequency(sideCounter);
                            locations.get(0).setLocation(persons.get(x).getLocation());
                        }

                    }
                }
            }

            sideCounter = 0;
            removeLocations(persons.get(x).getLocation());
            if(removedElements){
                x = -1;
                removedElements = false;
            }
        }

        sortBasedOnFrequency(locations);
        displayRanks(locations);


    }

    public void sortBasedOnFrequency(ArrayList<Location> locations){


        Collections.sort(locations);



    }

    public void removeLocations(String location){

        for(int x = 0; x < persons.size(); x++){
            if(persons.get(x).getLocation().equals(location)){
                persons.remove(persons.get(x));
                removedElements = true;
            }

        }

    }
    public void displayRanks(ArrayList<Location> locations){

        rank1 = (TextView) findViewById(R.id.rank1);
        rank2 = (TextView) findViewById(R.id.rank2);
        rank3 = (TextView) findViewById(R.id.rank3);
        rank4 = (TextView) findViewById(R.id.rank4);
        rank5 = (TextView) findViewById(R.id.rank5);

        friends1 = (TextView) findViewById(R.id.friends1);
        friends2 = (TextView) findViewById(R.id.friends2);
        friends3 = (TextView) findViewById(R.id.friends3);
        friends4 = (TextView) findViewById(R.id.friends4);
        friends5 = (TextView) findViewById(R.id.friends5);





        rank1.setText(locations.get(4).getLocation());
        friends1.setText(((Integer)locations.get(4).getFrequency()).toString() + " Friends");
        rank2.setText(locations.get(3).getLocation());
        friends2.setText(((Integer)locations.get(3).getFrequency()).toString() + " Friends");
        rank3.setText(locations.get(2).getLocation());
        friends3.setText(((Integer)locations.get(2).getFrequency()).toString() + " Friends");
        rank4.setText(locations.get(1).getLocation());
        friends4.setText(((Integer)locations.get(1).getFrequency()).toString() + " Friends");
        rank5.setText(locations.get(0).getLocation());
        friends5.setText(((Integer)locations.get(0).getFrequency()).toString() + " Friends");



    }
}

