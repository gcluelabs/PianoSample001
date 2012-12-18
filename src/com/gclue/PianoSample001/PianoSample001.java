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

	/** Button�z��̒�`�B */
	private ArrayList< Button > buttons = new ArrayList< Button >();
	/** �T�E���h�z��̒�`�B */
	private ArrayList< MediaPlayer > sounds = new ArrayList< MediaPlayer >();
	/** /res/layout/pianosample001_layout.xml�ɋL�q�����{�^���̐��B */
	private int howManyButtons = 8;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.pianosample001_layout );

		// /res/layout/pianosample_layout.xml �ɋL�q�����{�^����ǂݍ���
		addButtonAndSound( howManyButtons );
	}

	/**
	 * �{�^�����{�^���z��ɁA�T�E���h���T�E���h�z��Ɋi�[����B
	 * @param num �{�^���̐�
	 */
	private void addButtonAndSound( int num ) {
		for ( int i = 0; i < num; i++ ) {
			String n = Integer.toString( i + 1 );

			// �{�^�����{�^���z��Ɋi�[����
			int buttonId = getResources().getIdentifier( "button" + n, "id", getPackageName() );
			Button mButton = (Button) findViewById( buttonId );
			mButton.setOnClickListener( this );
			buttons.add( mButton );

			// �T�E���h���T�E���h�z��Ɋi�[����
			// �T�E���h�t�@�C����sound01�̂悤�ɁA0���t����2���\���ɂȂ��Ă��邽�߁A���l�̓���0��t������������쐬����
			if ( i < 10 ) {
				n = "0" + n;
			}
			int soundId = getResources().getIdentifier( "sound" + n, "raw", getPackageName() );

			MediaPlayer sound = MediaPlayer.create( this, soundId );
			sounds.add( sound );
		}
	}

	/**
	 * �R���|�[�l���g���N���b�N�����ƌĂяo�����B
	 */
	@Override
	public void onClick( View mView ) {
		// button�z��Ɋi�[���Ă���Button��������o���A�N���b�N���ꂽView(mView)��Button����v�����ꍇ�A�����Đ�����B
		for ( int i = 0; i < howManyButtons; i++ ) {
			if ( mView.equals( buttons.get( i ) ) ) {
				Log.i( "PianoSample002", "Button" + Integer.toString( i + 1 ) + "���N���b�N����܂����B" );
				sounds.get( i ).seekTo( 0 );
				sounds.get( i ).start();
				return;
			}
		}
	}
}