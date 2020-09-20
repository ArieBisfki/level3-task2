package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private lateinit var portalAdapter: PortalAdapter;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddReminderResult()
    }

    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        portalAdapter = PortalAdapter(portals)
        rvPortals.adapter = portalAdapter
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_portalsFragment_to_addPortalFragment)
        }
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getString(ARG_PORTAL_TITLE)?.let { title ->
                bundle.getString(ARG_PORTAL_URL)?.let { url ->
                    val portal = Portal(title, url)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}