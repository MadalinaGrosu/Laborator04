package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BasicDetailsFragment extends Fragment {
private ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();
	
	private class ButtonOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int buttonId = v.getId();
			if (buttonId == R.id.button1) { // show/hide additional details
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.frame2);
				if (additionalDetailsFragment == null) {
				  fragmentTransaction.add(R.id.frame2, new AdditionalDetailsFragment());
				  ((Button)v).setText(getResources().getString(R.string.hide_additional_fields));
				  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
				} else {
				  fragmentTransaction.remove(additionalDetailsFragment);
				  ((Button)v).setText(getResources().getString(R.string.show_additional_fields));
				  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
				}
				fragmentTransaction.commit();
			} else if (buttonId == R.id.button2) { // save button
				String name = ((EditText) getActivity().findViewById(R.id.name)).getText().toString();
				String phone = ((EditText) getActivity().findViewById(R.id.phone)).getText().toString();
				String email = ((EditText) getActivity().findViewById(R.id.email)).getText().toString();
				String address = ((EditText) getActivity().findViewById(R.id.address)).getText().toString();
				String jobTitle = ((EditText) getActivity().findViewById(R.id.jobTitle)).getText().toString();
				String company = ((EditText) getActivity().findViewById(R.id.company)).getText().toString();
				String website = ((EditText) getActivity().findViewById(R.id.website)).getText().toString();
				String im = ((EditText) getActivity().findViewById(R.id.im)).getText().toString();
				
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				if (name != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
				}
				if (phone != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
				}
				if (email != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
				}
				if (address != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
				}
				if (jobTitle != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
				}
				if (company != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
				}
				ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
				if (website != null) {
				  ContentValues websiteRow = new ContentValues();
				  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
				  contactData.add(websiteRow);
				}
				if (im != null) {
				  ContentValues imRow = new ContentValues();
				  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
				  contactData.add(imRow);
				}
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
				getActivity().startActivity(intent);
			} else if (buttonId == R.id.button3) { //cancel button
				getActivity().finish();
			}
		}
		
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fragment1, new BasicDetailsFragment());
		fragmentTransaction.commit();
		
		Button saveButton = (Button) getActivity().findViewById(R.id.button2);
		saveButton.setOnClickListener(buttonOnClickListener);
		Button showHideAdditonalDetails = (Button) getActivity().findViewById(R.id.button1);
		showHideAdditonalDetails.setOnClickListener(buttonOnClickListener);
		Button cancelButton = (Button) getActivity().findViewById(R.id.button3);
		cancelButton.setOnClickListener(buttonOnClickListener);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_basic_details, container, false);
	}
}
