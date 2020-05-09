package id.my.avmmartin.goldexperience.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import id.my.avmmartin.goldexperience.R;

public class LoadDataIndicator extends AsyncTask<Void, String, Void> {
    public interface Listener {
        void loadData();
    }

    private Listener listener;
    private ProgressDialog dialogue;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    // constructor

    public LoadDataIndicator(Context context) {
        super();

        dialogue = new ProgressDialog(context);
    }

    // overridden method

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialogue.setTitle(R.string.load_data);
        dialogue.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialogue.setIndeterminate(true);
        dialogue.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        listener.loadData();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        dialogue.dismiss();
    }
}
