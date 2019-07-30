package com.example.remote_config_sample

import android.graphics.Color
import android.media.tv.TvContract
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
//import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.google.firebase.remoteconfig.*
import io.repro.android.Repro

class Config {
    private var remoteConfig: FirebaseRemoteConfig
    private var isUsingRepro: Boolean = false

    constructor(isUsingRepro: Boolean) {
        this.isUsingRepro = isUsingRepro

        var map = mutableMapOf<String, Any>()
        map["title"] = "Tシャツ"
        map["message"] = "夏に着るおしゃれアイテム！"
        map["title_color"] = "333333"
        map["switch_button_position"] = "false"
        map["spring_mode"] = "false"

        // Setup Firebase
        var settings = FirebaseRemoteConfigSettings
            .Builder()
            .setMinimumFetchIntervalInSeconds(1).build()

        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaults(map as Map<String, Any>?)

        // Setup Repro
        Repro.getRemoteConfig().setDefault(map as Map<String, Any>?)
    }

    fun getTitle(): String {
        if (this.isUsingRepro) {
            return Repro.getRemoteConfig().get("title") as String
        }

        return remoteConfig.getString("title")
    }

    fun getTitleColor(): Int {
        if (this.isUsingRepro) {
            return Color.parseColor("#" + Repro.getRemoteConfig().get("title_color") as String)
        }

        return Color.parseColor("#" + remoteConfig.getString("title_color"))
    }

    fun getMessage(): String {
        if (this.isUsingRepro) {
            return Repro.getRemoteConfig().get("message") as String
        }

        return remoteConfig.getString("message")
    }

    fun isSpringMode(): Boolean {
        if (this.isUsingRepro) {
            return (Repro.getRemoteConfig().get("spring_mode") as String) == "true"
        }

        return remoteConfig.getString("spring_mode") == "true"
    }

    fun setup(callback: () -> Unit) {
        remoteConfig.fetch().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                remoteConfig.activate()
            }

            callback()
        }
    }

    fun setupRepro(callback: () -> Unit) {
        callback()
    }
}

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    private var cherryColorDark = Color.parseColor("#FF8298")
    private var cherryColor = Color.parseColor("#FF9BAD")
    private var cherryColorLight = Color.parseColor("#fffafa")
    private var whiteColor = Color.parseColor("#FFFFFF")

    private var config: Config = Config(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        linearLayout1.visibility = LinearLayout.INVISIBLE
        linearLayout2.visibility = LinearLayout.INVISIBLE

        updateValues()

        Repro.setLogLevel(android.util.Log.DEBUG)
//        Repro.setup("c486b90a-030f-417b-8838-cb041ecc7a7f")
        Repro.setup("234eb3e6-85ab-4978-a359-108908136ab2")
    }

    override fun onResume() {
        super.onResume()

        button2.setOnClickListener {
            val view = this.getLayoutInflater().inflate(R.layout.content_main, null);
            AlertDialog.Builder(this)
                .setView(view)
                .show();
        }

        config.setupRepro {
            updateValues()
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
            setupSprintUI()
        } else {
            setupUI()
        }

        titleView.text = config.getTitle()
        messageView.text = config.getMessage()
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
        button2.setBackgroundColor(toolbarColor.data)

        button2.setTextColor(this.whiteColor)
        titleView.setTextColor(this.cherryColorDark)
        content_main.setBackgroundColor(this.cherryColorLight)
        titleView.setTextColor(config.getTitleColor())
    }

    private fun setupSprintUI() {
        linearLayout1.visibility = LinearLayout.VISIBLE
        linearLayout2.visibility = LinearLayout.VISIBLE

        window.statusBarColor = this.cherryColorDark
        toolbar.setBackgroundColor(this.cherryColor)
        toolbar.setTitleTextColor(this.whiteColor)
        button2.setBackgroundColor(this.cherryColor)
        button2.setTextColor(this.whiteColor)
        titleView.setTextColor(this.cherryColorDark)
        content_main.setBackgroundColor(this.cherryColorLight)
//        productView.setBackgroundColor(this.cherryColor)
    }

}
