
package com.example.geniusgym.member.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.geniusgym.R
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.geniusgym.member.MeNaviDrawerViewModel

class MeNaviDrawer : Fragment() {
    /*private lateinit var binding : MeNaviDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MeNaviDrawer.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initDrawer()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navigationView,navHostFragment.navController) }

    private fun setUPActionBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true) }

    private fun initDrawer(){
        with(binding){
            val  actionBarDrawerToggle =
                ActionBarDrawerToggle( this@MeNaviDrawer
                        navidrawer,
                        R.string.txtOpen,
                        R.string.txtClose)
            navidrawer.addDrawerListner(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()
        }
    }

*/
/*    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        when(item.itemId){
            android.R.id.home -> {
                with(binding) {
                    if (navidrawer.isDrawerOpen(GravityCompat.START)) {
                        navidrawer.closeDrawer(GravityCompat.START)
                    } else {
                        navidrawer.openDrawer(GravityCompat.START)
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)  //？
    }
        }*//*








// override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.menu.mem_navidrawer, container, false)
//    }*/
}