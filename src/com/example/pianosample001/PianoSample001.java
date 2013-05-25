package com.example.pianosample001;

import java.util.ArrayList;

import com.gclue.PianoSample001.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class PianoSample001 extends Activity implements OnClickListener {

	private ArrayList< Button > buttons = new ArrayList< Button >();
	private ArrayList< MediaPlayer > sounds = new ArrayList< MediaPlayer >();
	private int howManyButtons = 8;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.pianosample001_layout );

		addButtonAndSound( howManyButtons );
	}

	private void addButtonAndSound( int num ) {
		for ( int i = 0; i < num; i++ ) {
			String n = Integer.toString( i + 1 );

			int buttonId = getResources().getIdentifier( "button" + n, "id", getPackageName() );
			Button mButton = (Button) findViewById( buttonId );
			mButton.setOnClickListener( this );
			buttons.add( mButton );

			if ( i < 10 ) {
				n = "0" + n;
			}
			int soundId = getResources().getIdentifier( "sound" + n, "raw", getPackageName() );

			MediaPlayer sound = MediaPlayer.create( this, soundId );
			sounds.add( sound );
		}
	}

	@Override
	public void onClick( View mView ) {
		for ( int i = 0; i < howManyButtons; i++ ) {
			if ( mView.equals( buttons.get( i ) ) ) {
				sounds.get( i ).seekTo( 0 );
				sounds.get( i ).start();
				return;
			}
		}
	}
}