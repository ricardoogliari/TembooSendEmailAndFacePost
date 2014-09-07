package com.example.temboosendemail;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.temboo.Library.Facebook.Publishing.Post;
import com.temboo.Library.Facebook.Publishing.Post.PostInputSet;
import com.temboo.Library.Facebook.Publishing.Post.PostResultSet;
import com.temboo.Library.Google.Gmail.SendEmail;
import com.temboo.Library.Google.Gmail.SendEmail.SendEmailInputSet;
import com.temboo.Library.Google.Gmail.SendEmail.SendEmailResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void enviar(View v){
		new Envia().execute();
	}
	
	class Envia extends AsyncTask<Void, Void, Void>{
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(MainActivity.this, "Enviando", "Enviando email, aguarde...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			TembooSession session;
			try {
				session = new TembooSession("ricardoogliari", "myFirstApp", "password_da_minha_sessão_temboo_por_favor_replacethis_:)");
				
				SendEmail sendEmailChoreo = new SendEmail(session);

				// Get an InputSet object for the choreo
				SendEmailInputSet sendEmailInputs = sendEmailChoreo.newInputSet();

				// Set inputs
				sendEmailInputs.set_MessageBody("teste temboo");
				sendEmailInputs.set_Subject("teste temboo");
				sendEmailInputs.set_Password("minha_senha_do_email_coloque_a_sua_:):)");
				sendEmailInputs.set_Username("username_do_email.._tb_coloque_a_sua_:):)");
				sendEmailInputs.set_FromAddress("rogliariping@gmail.com");
				sendEmailInputs.set_ToAddress("rogliariping@gmail.com");

				// Execute Choreo
				SendEmailResultSet sendEmailResults = sendEmailChoreo.execute(sendEmailInputs);
				
				
				// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
				// TembooSession session = new TembooSession("ricardoogliari", "myFirstApp", "44172969-265b-46d3-8");

				Post postChoreo = new Post(session);

				// Get an InputSet object for the choreo
				PostInputSet postInputs = postChoreo.newInputSet();

				// Set inputs
				postInputs.set_AccessToken("CAAE3NXDWktMBAFSHTe99D1WVwYTYyLdKnSrjZCT5M5FDOZBmWyvJ30ZA4QCh4xZCm50ntwS7wjEgnPBj1VhdXjeoYob0kGAW3dZAwTNCY8ZBLpbYt9UGFsFiLMYL21ufJxnpKZAVtZAPxDeENhPZBZCXvAcYWiC6J33kTnhvUz1vsueb52DDgg4FDTYQOuKH5OYZC8ZD");
				postInputs.set_Message("teste temboo do ricardo ogliari");

				// Execute Choreo
				PostResultSet postResults = postChoreo.execute(postInputs);
			} catch (TembooException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			return null;
		}
		
		protected void onPostExecute(Void result) {
			dialog.dismiss();
		};
	}
}
