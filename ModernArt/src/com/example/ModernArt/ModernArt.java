package com.example.ModernArt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;


public class ModernArt extends Activity {
    /**
     * Called when the activity is first created.
     */


    private SeekBar seekBar;
    private ImageView rectangleOne;
    private ImageView rectangleTwo;
    private ImageView rectangleThree;
    private ImageView rectangleFour;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Initiate the ImageView rectangles and seekbar
        rectangleOne= (ImageView) findViewById(R.id.imageView);
        rectangleTwo= (ImageView) findViewById(R.id.imageView2);
        rectangleThree = (ImageView) findViewById(R.id.imageView3);
        rectangleFour = (ImageView) findViewById(R.id.imageView4);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        // Set up the on change listener for the seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

               // call the colour calculation function
               writeColour(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                //  Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // Auto-generated method stub
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                //Show the Dialog
                DialogFragment thing = new ModernArtDialogFrag();
                thing.show(getFragmentManager(), "moma");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class ModernArtDialogFrag extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_string)
                    .setPositiveButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Visit MOMA
                            LoadWebsite();
                        }
                    })
                    .setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }

        public void LoadWebsite() {

            Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));

            startActivity(baseIntent);
        }
    }

    public void writeColour(int progress){

        // write the colours to the image views
        rectangleOne.setBackgroundColor(getScale(0x23AF01, 0xFFD95E, progress));
        rectangleTwo.setBackgroundColor(getScale(0xACFF22, 0xFF7CE7, progress));
        rectangleThree.setBackgroundColor(getScale(0xB9FFF3, 0xFF3419, progress));
        rectangleFour.setBackgroundColor(getScale(0x1DCCFF, 0x1C7F1F, progress));

    }

    public int getScale(int startColor, int endColor, int progress ){

        //Scale the seekbar (0 - 100) against the start and end colours

        int deltaRed = Color.red(endColor) - Color.red(startColor);
        int deltaGreen = Color.green(endColor) - Color.green(startColor);
        int deltaBlue = Color.blue(endColor) - Color.blue(startColor);

        int rd = Color.red(startColor) + (deltaRed * progress / 100);
        int gr = Color.green(startColor) + (deltaGreen * progress / 100);
        int bl = Color.blue(startColor) + (deltaBlue * progress / 100);

        return Color.rgb(rd, gr, bl);
    }

}

