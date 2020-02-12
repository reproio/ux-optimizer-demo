package com.example.remote_config_sample

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import io.repro.android.Repro

class Config {
    constructor() {
        var map = mutableMapOf<String, Any?>()
        map["title"] = "Tシャツ"
        map["message"] = "夏に着るおしゃれアイテム！"
        map["title_color"] = "333333"
        map["switch_button_position"] = "false"
        map["spring_mode"] = "false"
        map["image_url"] = null
        map["button_position"] = "B"

        Repro.getRemoteConfig().setDefaultsFromMap(map as Map<String, Any?>?)
    }

    fun getTitle(): String {
        return Repro.getRemoteConfig().get("title").asString() ?: ""
    }

    fun getTitleColor(): Int {
        return Color.parseColor("#" + (Repro.getRemoteConfig().get("title_color").asString() ?: "FFFFFF"))
    }

    fun getMessage(): String {
        return Repro.getRemoteConfig().get("message").asString() ?: ""
    }

    fun isSpringMode(): Boolean {
        Log.i("Spring Mode", "spring_mode: " + Repro.getRemoteConfig().get("spring_mode").asString())
        return (Repro.getRemoteConfig().get("spring_mode").asString() ?: "false") == "true"
    }

    fun getImageUrl(): String? {
        return Repro.getRemoteConfig().get("image_url").asString()
    }

    fun isChangedButtonPosition(): Boolean {
        var position = Repro.getRemoteConfig().get("button_position").asString()?: "B"
        return position != "B"
    }

    fun setup(callback: () -> Unit) {
        Repro.getRemoteConfig().fetch(5000) {
            Repro.getRemoteConfig().activateFetched()
            callback()
        }
    }
}

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    private var cherryColorDark = Color.parseColor("#FF8298")
    private var cherryColor = Color.parseColor("#FF9BAD")
    private var cherryColorLight = Color.parseColor("#fffafa")
    private var whiteColor = Color.parseColor("#FFFFFF")

    private var config: Config = Config()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        linearLayout1.visibility = LinearLayout.INVISIBLE
        linearLayout2.visibility = LinearLayout.INVISIBLE

        updateValues()
        setupRepro()
    }

    override fun onStart() {
        super.onStart()

        config.setup {
            updateValues()
        }
    }

    override fun onResume() {
        super.onResume()

        button2.setOnClickListener {
            val view = this.getLayoutInflater().inflate(R.layout.content_main, null);
            AlertDialog.Builder(this)
                .setView(view)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun updateValues() {
        titleView.text = title
        if (config.isSpringMode()) {
            setupSpringUI()
        } else {
            setupUI()
        }

        titleView.text = config.getTitle()
        messageView.text = config.getMessage()
    }

    private fun setupRepro() {
        Repro.setLogLevel(Log.DEBUG)
        Repro.setup("052efef0-ad3f-4709-8b75-5175ec446258")

    }

    private fun setupUI() {
        linearLayout1.visibility = LinearLayout.INVISIBLE
        linearLayout2.visibility = LinearLayout.INVISIBLE

        var outValue = TypedValue()
        theme.resolveAttribute(android.R.attr.statusBarColor, outValue, true)
        window.statusBarColor = outValue.data;

        var toolbarColor = TypedValue();
        theme.resolveAttribute(android.R.attr.colorPrimary, toolbarColor, true)
        toolbar.setBackgroundColor(toolbarColor.data)
        toolbar.setTitleTextColor(this.whiteColor)

        button1.setBackgroundColor(toolbarColor.data)
        button1.setTextColor(this.whiteColor)
        button1.isVisible = config.isChangedButtonPosition()

        button2.setBackgroundColor(toolbarColor.data)
        button2.setTextColor(this.whiteColor)
        button2.isVisible = !config.isChangedButtonPosition()

        titleView.setTextColor(this.cherryColorDark)
        titleView.setTextColor(config.getTitleColor())

        content_main.setBackgroundColor(this.cherryColorLight)

        if (config.getImageUrl() != null) {
            Glide.with(this).load(config.getImageUrl()).into(productView)
        }
    }

    private fun setupSpringUI() {
        linearLayout1.visibility = LinearLayout.VISIBLE
        linearLayout2.visibility = LinearLayout.VISIBLE

        window.statusBarColor = this.cherryColorDark
        toolbar.setBackgroundColor(this.cherryColor)
        toolbar.setTitleTextColor(this.whiteColor)

        button1.setBackgroundColor(this.cherryColor)
        button1.setTextColor(this.whiteColor)
        button1.isVisible = config.isChangedButtonPosition()

        button2.setBackgroundColor(this.cherryColor)
        button2.setTextColor(this.whiteColor)
        button2.isVisible = !config.isChangedButtonPosition()

        titleView.setTextColor(this.cherryColorDark)
        content_main.setBackgroundColor(this.cherryColorLight)

        if (config.getImageUrl() != null) {
            Glide.with(this).load(config.getImageUrl()).into(productView)
        }
    }

}
