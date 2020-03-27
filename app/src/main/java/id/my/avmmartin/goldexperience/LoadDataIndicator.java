package id.my.avmmartin.goldexperience;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

class LoadDataIndicator extends AsyncTask<Void, String, Void> {
    public interface Listener {
        void load_data();
    }

    private LoadDataIndicator.Listener listener;
    private ProgressDialog dialogue;

    LoadDataIndicator(Context context) {
        super();

        listener = (LoadDataIndicator.Listener)context;
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();

        dialogue = new ProgressDialog((Context)listener);
        dialogue.setTitle(R.string.load_data);
        dialogue.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialogue.setIndeterminate(true);
        dialogue.show();
    }

    @Override protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        dialogue.dismiss();
    }

    @Override protected Void doInBackground(Void... params) {
        listener.load_data();
        return null;
    }
}
