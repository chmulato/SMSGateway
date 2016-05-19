/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package csonline.net.br.smsgateway.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Roy Clarkson
 * @author Pierre-Yves Ricau
 */
public abstract class AbstractAsyncActivity extends Activity {

  protected static final String TAG = AbstractAsyncActivity.class.getSimpleName();

  private ProgressDialog progressDialog;

  private boolean destroyed = false;

  // ***************************************
  // Activity methods
  // ***************************************
  @Override
  protected void onDestroy() {
    super.onDestroy();
    destroyed = true;
  }

  // ***************************************
  // Public methods
  // ***************************************
  public void showLoadingProgressDialog() {
    this.showProgressDialog("Processando. Por favor aguarde...");
  }

  public void showProgressDialog(CharSequence message) {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(this);
      progressDialog.setIndeterminate(true);
    }
    progressDialog.setMessage(message);
    progressDialog.show();
  }

  public void dismissProgressDialog() {
    if (progressDialog != null && !destroyed) {
      progressDialog.dismiss();
    }
  }
}