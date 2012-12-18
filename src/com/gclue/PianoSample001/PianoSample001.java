package com.gclue.PianoSample001;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class PianoSample001 extends Activity implements OnClickListener {

	/** Button配列の定義。 */
	private ArrayList< Button > buttons = new ArrayList< Button >();
	/** サウンド配列の定義。 */
	private ArrayList< MediaPlayer > sounds = new ArrayList< MediaPlayer >();
	/** /res/layout/pianosample001_layout.xmlに記述したボタンの数。 */
	private int howManyButtons = 8;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.pianosample001_layout );

		// /res/layout/pianosample_layout.xml に記述したボタンを読み込む
		addButtonAndSound( howManyButtons );
	}

	/**
	 * ボタンをボタン配列に、サウンドをサウンド配列に格納する。
	 * @param num ボタンの数
	 */
	private void addButtonAndSound( int num ) {
		for ( int i = 0; i < num; i++ ) {
			String n = Integer.toString( i + 1 );

			// ボタンをボタン配列に格納する
			int buttonId = getResources().getIdentifier( "button" + n, "id", getPackageName() );
			Button mButton = (Button) findViewById( buttonId );
			mButton.setOnClickListener( this );
			buttons.add( mButton );

			// サウンドをサウンド配列に格納する
			// サウンドファイルがsound01のように、0が付いた2桁表示になっているため、数値の頭に0を付けた文字列を作成する
			if ( i < 10 ) {
				n = "0" + n;
			}
			int soundId = getResources().getIdentifier( "sound" + n, "raw", getPackageName() );

			MediaPlayer sound = MediaPlayer.create( this, soundId );
			sounds.add( sound );
		}
	}

	/**
	 * コンポーネントがクリックされると呼び出される。
	 */
	@Override
	public void onClick( View mView ) {
		// button配列に格納してあるButtonを一つずつ取り出し、クリックされたView(mView)とButtonが一致した場合、音を再生する。
		for ( int i = 0; i < howManyButtons; i++ ) {
			if ( mView.equals( buttons.get( i ) ) ) {
				Log.i( "PianoSample002", "Button" + Integer.toString( i + 1 ) + "がクリックされました。" );
				sounds.get( i ).seekTo( 0 );
				sounds.get( i ).start();
				return;
			}
		}
	}
}