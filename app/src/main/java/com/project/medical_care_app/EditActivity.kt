package com.project.medical_care_app

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.project.medical_care_app.databinding.ActivityEditBinding
import com.project.medical_care_app.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_type,
            android.R.layout.simple_list_item_1
        )
        binding.birthdateLayer.setOnClickListener{
            val listener = OnDateSetListener{_, year, month, dayOfMonth ->
                binding.birthdateTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
            1,
            1
            ).show()
        }
        binding.warningCheckBox.setOnCheckedChangeListener{_, isChecked ->
            binding.warningEditText.isVisible =isChecked
        }
        binding.warningEditText.isVisible = binding.warningCheckBox.isChecked

    }
}