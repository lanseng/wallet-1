package com.yns.wallet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.yns.wallet.fragment.MineFragment
import com.yns.wallet.fragment.WalletFragment

class MainActivity : BaseActivity() {

    private lateinit var walletCreateDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<WalletFragment>(R.id.fragment_container)
            }
        }

        findViewById<View>(R.id.iv_wallet).setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, WalletFragment())
            }
        }

        findViewById<View>(R.id.iv_mine).setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, MineFragment())
            }
        }

        val view = LayoutInflater.from(this).inflate(R.layout.dialog_one_btn, null)
        view.findViewById<View>(R.id.tv_confirm).setOnClickListener { openImportOrCreate() }
        walletCreateDialog = AlertDialog.Builder(this).create()
        walletCreateDialog.setView(view)
        walletCreateDialog.show()
        val width = resources.getDimensionPixelOffset(R.dimen.dialog_width)
        val height = walletCreateDialog.window?.attributes?.height as Int
        walletCreateDialog.window?.setLayout(width, height)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        walletCreateDialog.dismiss()
    }

    private fun openImportOrCreate() {
        startActivity(Intent(this, ImportOrCreateWallet::class.java))
    }
}