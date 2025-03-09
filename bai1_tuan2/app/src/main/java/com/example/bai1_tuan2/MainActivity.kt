package com.example.bai1_tuan2

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai1_tuan2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button2.setOnClickListener {
            val dialog = AlertDialog.Builder(this)

            try {
                val a = binding.edtAge.text.toString().toInt()
                if (a < 0) {
                    dialog.apply {
                        setTitle("Lỗi")
                        setMessage("Tuổi không được âm")
                        setNegativeButton("Sưa"){
                            dialogInterface: DialogInterface, i: Int ->
                            dialogInterface.dismiss()
                        }
                    }
                } else {
                    when{
                        a > 65 -> dialog.apply {
                            setTitle("${binding.edtName.text}")
                            setMessage("Người già")
                            setNegativeButton("Oke"){
                                    dialogInterface: DialogInterface, i: Int ->
                                dialogInterface.dismiss()
                            }
                        }
                        a in 6..65 ->{
                            dialog.apply {
                                setTitle("${binding.edtName.text}")
                                setMessage("Người lớn ")
                                setNegativeButton("Oke") { dialogInterface: DialogInterface, i: Int ->
                                    dialogInterface.dismiss()
                                }
                            }
                        }
                        a in 2..6 ->{
                            dialog.apply {
                                setTitle("${binding.edtName.text}")
                                setMessage("Trẻ em")
                                setNegativeButton("Oke") { dialogInterface: DialogInterface, i: Int ->
                                    dialogInterface.dismiss()
                                }
                            }
                        }
                        else ->{
                            dialog.apply {
                                setTitle("${binding.edtName.text}")
                                setMessage("Em bé")
                                setNegativeButton("Oke") { dialogInterface: DialogInterface, i: Int ->
                                    dialogInterface.dismiss()
                                }
                            }
                        }
                    }

                }
            } catch (e: NumberFormatException) {
                dialog.apply {
                    setTitle("Lỗi")
                    setMessage("Vui lòng nhập một số hợp lệ")
                }
            } finally {
                val alertDialog = dialog.create()
                alertDialog.show()
            }
        }
    }
}