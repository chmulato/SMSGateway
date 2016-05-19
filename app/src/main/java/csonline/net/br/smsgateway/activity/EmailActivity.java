package csonline.net.br.smsgateway.activity;
 
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import csonline.net.br.smsgateway.R;
import csonline.net.br.smsgateway.email.ReadEmails;
import csonline.net.br.smsgateway.email.ReadParameters;
import csonline.net.br.smsgateway.email.SendEmail;
import csonline.net.br.smsgateway.exception.AppException;
import csonline.net.br.smsgateway.model.EmailVO;

public class EmailActivity extends AbstractAsyncActivity  {

	private static final String LOGGER = "LogCat";

	Button buttonSend;
	EditText textTo;
	EditText textSubject;
	EditText textMessage;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
 		buttonSend = (Button) findViewById(R.id.buttonSend);
		textTo = (EditText) findViewById(R.id.editTextTo);
		textSubject = (EditText) findViewById(R.id.editTextSubject);
		textMessage = (EditText) findViewById(R.id.editTextMessage);
		final Button buttonSend = (Button) findViewById(R.id.buttonSend);
		final Button buttonReceived = (Button) findViewById(R.id.buttonReceived);
		buttonSend.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				sendEmail();
			}
		});
		buttonReceived.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				sendReceived();
			}
		});

	}

	// ***************************************
	// Send email method
	// ***************************************
	public void sendEmail() {
		new FetchSecuredSendResourceTask().execute();
	}

	// ***************************************
	// Received email method
	// ***************************************
	public void sendReceived() {
		new FetchSecuredReceiveResourceTask().execute();
	}

	// ***************************************
	// Private methods
	// ***************************************
	private void displaySendResponse(boolean sentOK) {
		if (sentOK) {
			Toast.makeText(this, getString(R.string.success_sent_msg_email), Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(EmailActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, getString(R.string.fail_msg_email), Toast.LENGTH_SHORT).show();
		}
	}

	// ***************************************
	// Private methods
	// ***************************************
	private void displayReceiveResponse(List<EmailVO> emailVOList) {
		if ((emailVOList!=null) && (emailVOList.size()>0)) {
			Toast.makeText(this, getString(R.string.success_received_msg_email), Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(EmailActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, getString(R.string.fail_msg_email), Toast.LENGTH_SHORT).show();
		}
	}

	private class FetchSecuredSendResourceTask extends AsyncTask<Void, Void, Void> {

		boolean sentOK = false;
		boolean validate = false;
		String to;
		String subject;
		String message;

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
			to = textTo.getText().toString();
			subject = textSubject.getText().toString();
			message = textMessage.getText().toString();

			if ((to!=null) && (!to.equals(""))) {
				validate = true;
			} else {
				validate = false;
			}

			if ((subject!=null) && (!subject.equals(""))) {
				validate = true;
			} else {
				validate = false;
			}

			if ((message!=null) && (!message.equals(""))) {
				validate = true;
			} else {
				validate = false;
			}
			Log.d(TAG, "onPreExecute()");
		}

		@Override
		protected Void doInBackground(Void... params) {
			Log.d(TAG, "doInBackground()");
			if (validate) {
				try {
					ReadParameters readParameters = new ReadParameters(getBaseContext(), "SMTP");
					SendEmail sendEmail = new SendEmail(new String[]{to}, subject, message);
					sentOK = true;
				} catch (AppException e) {
					Log.e(LOGGER, getClass() + " Não foi possível enviar a mensagem! " , e);
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void res) {
			displaySendResponse(sentOK);
			Log.d(TAG, "onPostExecute()");
		}
	}

	private class FetchSecuredReceiveResourceTask extends AsyncTask<Void, Void, Void> {

		List<EmailVO> emailVOList = new ArrayList<>();
		boolean receiveOK = false;
		boolean validate = false;

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
			Log.d(TAG, "onPreExecute()");
		}

		@Override
		protected Void doInBackground(Void... params) {
			Log.d(TAG, "doInBackground()");
			try {
				ReadParameters readParameters = new ReadParameters(getBaseContext(), "IMAP");
				ReadEmails.connectionAccountMail();
				emailVOList = ReadEmails.getListAllEmails();
			} catch (Exception e) {
				Log.e(LOGGER, getClass() + " Não foi possível carregar nenhuma mensagem! " , e);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void res) {
			displayReceiveResponse(emailVOList);
			Log.d(TAG, "onPostExecute()");
		}
	}

}