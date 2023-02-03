package com.project.medical_care_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.project.medical_care_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
        binding.deleteButton.setOnClickListener(){
            deleteDate()
        }
        binding.emergencyContactLayer.setOnClickListener{
            val intent = with(Intent(Intent.ACTION_VIEW)){
                val phoneNumber = binding.emergencyContactValueTextView.text.toString()
                    .replace("-","")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    override fun onResume(){
        super.onResume()
        getDataUiUpdate()
    }
    private fun getDataUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "미정")
            binding.birthdateValueTextView.text = getString(BIRTHDATE, "미정")
            binding.bloodTypeValueTextView.text = getString(BLOOD_TYPE, "미정")
            binding.emergencyContactValueTextView.text = getString(EMERGENCY_CONTACT, "미정")
            val warning = getString(WARNING, "미정")
//            if(warning.isNullOrEmpty()){
//                binding.warningTextView.isVisible = false
//                binding.warningValueTextView.isVisible = false
//            }else{
//                binding.warningTextView.isVisible = true
//                binding.warningValueTextView.isVisible = true
//                binding.warningValueTextView.text = warning
//            }
            binding.warningTextView.isVisible = warning.isNullOrEmpty().not()
            binding.warningValueTextView.isVisible = warning.isNullOrEmpty().not()

            if (!warning.isNullOrEmpty()) {
                binding.warningValueTextView.text = warning
            }
        }
    }

    private fun deleteDate(){
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()){
            clear()
            apply()
            getDataUiUpdate()
        }
        Toast.makeText(this, "초기화를 완료했습니다", Toast.LENGTH_SHORT).show()
    }
}