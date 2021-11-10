package com.example.appciudadescolombia

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment

class CityDialog : DialogFragment() {

   public fun onCreateDialog(): Dialog {
            return activity?.let {
                // Use the Builder class for convenient dialog construction
                val builder = AlertDialog.Builder(it)
                builder.setTitle("Información")
                builder.setMessage(R.string.barranquilla_text)
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
                    // negative button text and action
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
                // Create the AlertDialog object and return it
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
}