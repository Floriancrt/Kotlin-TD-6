package com.example.tdn2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_tasks.*
import java.util.*

class TaskActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val edit_text_title = findViewById<EditText>(R.id.title_task)
        val edit_text_description = findViewById<EditText>(R.id.description_task)
        var updateTask = intent.getSerializableExtra("editTask")

        validate_button.setOnClickListener {
            val add_task = Task(id = UUID.randomUUID().toString(), title = edit_text_title.text.toString(), description = edit_text_description.text.toString())
            intent.putExtra(TASK_KEY, add_task)
            setResult(RESULT_OK, intent)
            finish()

        }

        if (updateTask !== null ) {
            updateTask = updateTask as Task
            title_task.setText(updateTask.title)
            description_task.setText(updateTask.description)

            validate_button.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("task", Task(id = UUID.randomUUID().toString(), title = edit_text_title.text.toString(), description = edit_text_description.text.toString()))
                setResult(2, intent)
                finish()
            }
        } else {
            validate_button.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("task", Task(id = UUID.randomUUID().toString(), title = edit_text_title.text.toString(), description = edit_text_description.text.toString()))
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }

    companion object {
        const val TASK_KEY = "task_key"
    }
}