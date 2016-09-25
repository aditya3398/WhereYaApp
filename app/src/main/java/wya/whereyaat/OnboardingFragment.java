package wya.whereyaat;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardingFragment extends Fragment {

    int position;
    RelativeLayout relativeLayout;
    Button continueButton;

    public OnboardingFragment() {}

    public void newInstance(int position){
        this.position = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_onboarding, container, false);

        switch (position) {
            case 0:
                relativeLayout = (RelativeLayout) v.findViewById(R.id.screen1);
                break;
            case 1:
                relativeLayout = (RelativeLayout) v.findViewById(R.id.screen2);
                break;
            case 2:
                relativeLayout = (RelativeLayout) v.findViewById(R.id.screen3);
                break;
            case 3:
                relativeLayout = (RelativeLayout) v.findViewById(R.id.screen4);
                break;
            case 4:
                relativeLayout = (RelativeLayout) v.findViewById(R.id.screen5);
                break;
            default:
                relativeLayout = (RelativeLayout) v.findViewById(R.id.screen1);
                break;
        }
        relativeLayout.setVisibility(View.VISIBLE);

        if(position == 4){
            continueButton = (Button) v.findViewById(R.id.continueButton);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        return v;
    }

}
